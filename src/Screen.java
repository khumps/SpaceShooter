import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
	protected PlayerShip player = new PlayerShip(new PointDouble(500, 500),
			this);
	private BufferedImage background = Utils.loadImage("space.png");
	protected Graphics2D g2;
	protected boolean debug = false;
	protected boolean isFullscreen = false;
	private JFrame frame = new JFrame();
	protected int score = 0;
	private Rectangle oldBounds;
	private int oldState;
	protected boolean paused = false;
	protected Camera cam = new Camera(player);

	public Screen() {
		frame.add(this);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		addKeyListener(listener);
		addMouseListener(listener);
		addMouseMotionListener(listener);
		setFocusable(true);
		setPreferredSize(new Dimension(750, 750));
		entities.add(player);
		timer.setActionCommand("timer");
		frame.pack();
		frame.setVisible(true);
		if (!paused)
			timer.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;

		PointDouble corner = new PointDouble(getBounds().x, getBounds().y);
		for (int i = 0; i < getWidth(); i += background.getWidth()) {
			for (int j = 0; j < getHeight(); j += background.getHeight()) {
				g.drawImage(background, i, j, null);
			}
		}
		/* Draw Entities */
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.update(tickNum);
			e.draw(g2, corner, cam.tick());
			if (e instanceof EnemyShip)
				drawHealth(g2, (Ship) e);

			if (debug) {
				drawHitBox(g2, e);
				timer.setDelay(timerSpeed * 4);
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
		if (tickNum % 30 == 0 && getNumShips() < 7)
			entities.add(new EnemyShip(pointOnScreen(), 1, this));
	}

	public void checkCollision() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e1 = entities.get(i);
			if (e1 instanceof Ship)
				for (int j = 1; j < entities.size(); j++) {
					e1.doesCollide(entities.get(j));
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

	public PointDouble pointOnScreen() {
		return new PointDouble(Math.random() * getWidth(), Math.random()
				* getHeight());
	}

	public synchronized void drawPlayerHealth(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.drawRect(20, getHeight() - getHeight() / 15,
				player.getMaxHealth() / 2, 15);
		g.fillRect(20, getHeight() - getHeight() / 15, player.getHealth() / 2,
				15);
	}

	public synchronized void drawHealth(Graphics2D g, Ship s) {
		if (s.getHealth() < s.getMaxHealth()) {
			g.setColor(Color.RED);
			g.drawRect(s.getPosition().getXInt(), s.getPosition().getYInt()
					- s.img.getHeight() / 2, s.getMaxHealth() / 3, 10);
			g.fillRect(s.getPosition().getXInt(), s.getPosition().getYInt()
					- s.img.getHeight() / 2, s.getHealth() / 3, 10);
		}
	}

	public void drawHitBox(Graphics2D g, Entity e) {
		if (debug) {
			g.setColor(Color.GREEN);
			g2.draw(e.collisionArea.getArea());
		}
	}

	public void drawHud(Graphics2D g) {
		drawPlayerHealth(g);
		g.drawRect(50, 50, 100, 20);
		g.drawString("Score: " + score, 55, 65);
	}

	protected void enableFullscreen() {
		oldBounds = frame.getBounds();
		oldState = frame.getExtendedState();
		frame.dispose();
		frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		isFullscreen = true;
	}

	protected void disableFullscreen() {
		frame.dispose();
		frame.setUndecorated(false);
		frame.setPreferredSize(new Dimension(oldBounds.width, oldBounds.height));
		frame.pack();
		frame.setVisible(true);
		isFullscreen = false;
	}

	protected void pause() {
		paused = true;
		timer.stop();
	}

	protected void unPause() {
		paused = false;
		timer.start();
	}

	public static void main(String[] args) {
		new Screen();

	}

}
