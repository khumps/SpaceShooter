import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Screen extends JPanel {
	protected ArrayList<Entity> entities = new ArrayList<Entity>();
	private Listener listener = new Listener(this);
	protected int tickNum = 0;
	private int timerSpeed = 16;
	Timer timer = new Timer(timerSpeed, listener);
	int i = 0;
	protected Point playerMovement = new Point(0, 0);
	protected PlayerShip player = new PlayerShip(new Point(500, 500), this);
	private BufferedImage background = Utils.loadImage("space.png");
	protected Graphics2D g2;
	protected boolean debug = false;
	private boolean isFullscreen = true;
	private JFrame frame = new JFrame();
	protected int score = 0;

	public Screen() {

		frame.add(this);
		addKeyListener(listener);
		addMouseListener(listener);
		addMouseMotionListener(listener);
		setFocusable(true);
		setPreferredSize(new Dimension(1000, 1000));
		entities.add(player);
		timer.setActionCommand("timer");
		timer.start();
		setVisible(true);
		frame.setUndecorated(isFullscreen);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// frame.pack();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		/*
		 * if (isFullscreen) {
		 * 
		 * frame.setUndecorated(isFullscreen); }
		 */
		g2 = (Graphics2D) g;
		Point corner = new Point(getBounds().x, getBounds().y);
		for (int i = 0; i < getWidth(); i += background.getWidth()) {
			for (int j = 0; j < getHeight(); j += background.getHeight()) {
				g.drawImage(background, i, j, null);
			}
		}
		/* Draw Entities */
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.update(tickNum);
			e.draw(g2, corner);
/*			if(e instanceof Projectile && ((Projectile) e).collided == true)
			{
				((Projectile) e).effect.drawEffect(g2, e.getPosition());
			}*/
			if(e instanceof EnemyShip)
				drawHealth(g2, (Ship)e);

			if (debug) {
				drawHitBox(g2, e);
				timer.setDelay(timerSpeed * 2);
			} else
				timer.setDelay(timerSpeed);
		}

		g.drawString(tickNum + "", 500, 500);

		drawHud(g2);
	}

	public void purgeEntities() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if (!(e instanceof Ship))
				if (!getBounds().contains(e.getPosition().x, e.getPosition().y)) {
					entities.remove(i);
				}
		}
	}

	public void tick(int tickNum) {
		checkCollision();
		if (tickNum % 10 == 0)
			purgeEntities();
		if (tickNum % 30 == 0 && getNumShips() < 5)
			entities.add(new EnemyShip(pointOnScreen(), 100, this));
	}

	public void checkCollision() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e1 = entities.get(i);
			if (e1 instanceof Ship)
				for (int j = 1; j < entities.size(); j++) {
					Entity e2 = entities.get(j);
					e1.doesCollide(e2);
				}
		}
	}

	public int getNumShips() {
		int numShips = 0;
		for (Entity e : entities) {
			if (e instanceof Ship)
				numShips++;
		}
		return numShips;
	}

	public Point pointOnScreen() {
		return new Point(Math.random() * getWidth(), Math.random()
				* getHeight());
	}

	public void drawPlayerHealth(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.drawRect(20, getHeight() - getHeight() / 15,
				player.getMaxHealth() / 2, 15);
		g.fillRect(20, getHeight() - getHeight() / 15, player.getHealth() / 2,
				15);
	}
	
	public void drawHealth(Graphics2D g, Ship s)
	{
		g.setColor(Color.RED);
		g.drawRect(s.getPosition().x, s.getPosition().y - s.img.getHeight() / 2, s.getMaxHealth() / 3, 10);
		g.fillRect(s.getPosition().x, s.getPosition().y - s.img.getHeight() / 2, s.getHealth() / 3, 10);
	}

	public void drawHitBox(Graphics2D g, Entity e) {
		if (debug) {
			g.setColor(Color.GREEN);
			g2.draw(e.collisionArea.getArea());
		}
	}
	
	public void drawHud(Graphics2D g)
	{
		drawPlayerHealth(g);
		g.drawRect(50, 50, 100, 20);
		g.drawString("Score: " + score, 55, 65);
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.add(new Screen());
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);

	}

}
