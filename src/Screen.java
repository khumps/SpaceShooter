import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Screen extends JPanel {
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	protected ArrayBlockingQueue<Method> methods = new ArrayBlockingQueue<Method>(100);
	private Listener listener = new Listener(this);
	protected int tickNum = 0;
	private int timerSpeed = 16;
	Timer timer = new Timer(timerSpeed, listener);
	int i = 0;
	protected Point playerMovement = new Point(0, 0);
	public PlayerShip player = new PlayerShip(new PointDouble(500, 500), this);
	private BufferedImage background = Utils.loadImage("space.png");
	protected Graphics2D g2;
	protected boolean debug = false;
	protected boolean isFullscreen = false;
	private JFrame frame = new JFrame();
	private GameLogic logic;
	public int score = 0;
	private Rectangle oldBounds;
	private int oldState;
	protected boolean paused = true;
	protected double zoom = 20;

	// protected Camera cam = new Camera(player);
	// protected PointDouble offset = cam.tick();

	public Screen() {
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		addKeyListener(listener);
		addMouseListener(listener);
		addMouseMotionListener(listener);
		setFocusable(true);
		getEntities().add(player);
		logic = new GameLogic(this);
		frame.add(this);
		timer.setActionCommand("timer");
		frame.pack();
		frame.setVisible(true);
		if (!paused)
			timer.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		// Draw Background
		PointDouble corner = new PointDouble(getBounds().x, getBounds().y);
		for (int i = 0; i < getWidth(); i += background.getWidth()) {
			for (int j = 0; j < getHeight(); j += background.getHeight()) {
				g.drawImage(background, i, j, null);
			}
		}
		/* Draw Entities */
		for (int i = 0; i < getEntities().size(); i++) {
			Entity e = getEntities().get(i);
			if (!paused)
				e.update(tickNum);
			e.draw(g2, corner, player.getPosition());
			if (e instanceof EnemyShip)
				drawHealth(g2, (Ship) e);

			if (debug) {
				drawHitBox(g2, e);
				timer.setDelay(timerSpeed * 4);
			} else
				timer.setDelay(timerSpeed);
		}
		drawHud(g2);
		if (paused) {
			drawPauseScreen(g2);
		}
		if (!player.isAlive())
			drawDeathScreen(g2);
	}

	public int getNumShips() {
		int numShips = 0;
		for (Entity e : getEntities()) {
			if (e instanceof Ship)
				numShips++;
		}
		return numShips;
	}

	public PointDouble pointOnScreen(PointDouble p) {
		return new PointDouble(p.x + (Math.random() * getWidth() / 2), p.y + (Math.random() * getHeight() / 2));
	}

	public synchronized void drawHealth(Graphics2D g, Ship s) {
		int x = (int) (s.getPosition().x - player.getPosition().x - s.getImg().getWidth() / 2 + this.getWidth() / 2);
		int y = (int) ((int) s.getPosition().y - player.getPosition().y - s.getImg().getHeight() / 2 + this.getHeight() / 2);
		if (s.getHealth() < s.getMaxHealth()) {
			g.setColor(Color.RED);
			g.drawRect(x, y, s.getMaxHealth() / 2, 10);
			g.fillRect(x, y, s.getHealth() / 2, 10);
		}
		if (s.getShields() < s.getMaxShields()) {
			int sx = x + 10;
			int sy = y + 10;
			g.setColor(Color.CYAN);
			if (s.getShields() > 0)
				g.drawRect(sx, sy, s.getMaxShields(), 5);
			g.fillRect(sx, sy, s.getShields(), 5);
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
		g.setStroke(new BasicStroke(3));
		g.setColor(Color.GRAY);
		g.drawRect(50, 39, 180, 30);
		g.setColor(Color.GREEN);
		g.setFont(g.getFont().deriveFont(30f));
		g.drawString("Score: " + score, 55, 65);
		drawRadar(g);
	}

	public synchronized void drawPlayerHealth(Graphics2D g) {

		g.setStroke(new BasicStroke(3));
		// Health
		g.setColor(Color.GRAY);
		g.drawRect(20, getHeight() - getHeight() / 15, player.getMaxHealth() / 2, 20);
		g.setColor(Color.GREEN);
		g.fillRect(20, getHeight() - getHeight() / 15, player.getHealth() / 2, 20);
		// Shields
		g.setColor(Color.CYAN);
		g.drawRect(20, getHeight() - getHeight() / 15 - 21, player.getMaxShields() * 5, 20);
		g.fillRect(20, getHeight() - getHeight() / 15 - 21, player.getShields() * 5, 20);
	}

	public void drawRadar(Graphics2D g) {
		g.setColor(new Color(26, 255, 0, 80));
		int size = 250;
		int offset = 10;
		int radarX = getWidth() - size;
		int radarY = getHeight() - size;
		int iconSize = 3;
		g.fillRect(radarX, radarY, size - offset, size - offset);
		g.setColor(Color.GRAY);
		g.setStroke(new BasicStroke(3));
		g.drawRect(radarX, radarY, size - offset, size - offset);
		for (Entity e : getEntities()) {
			if (e instanceof Ship && !(e instanceof PlayerShip)) {
				g.setColor(Color.black);
				PointDouble temp = Utils.translateToView(e.getPosition(), player.getPosition(), this);
				g.drawRect((int) (getWidth() - size + (temp.x / size) * zoom + size / 2 + .5),
						(int) (getHeight() - size + (temp.y / size) * zoom + size / 2 + .5), iconSize, iconSize);
			}
		}
		g.setColor(Color.ORANGE);
		PointDouble temp = Utils.translateToView(player.getPosition(), player.getPosition(), this);
		g.drawRect((int) (getWidth() - size + (temp.x / size) * zoom + size / 2 + .5),
				(int) (getHeight() - size + (temp.y / size) * zoom + size / 2 + .5), iconSize, iconSize);
	}

	public void drawPauseScreen(Graphics2D g) {
		g.setColor(new Color(255, 255, 255, 100));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.WHITE);
		g.setFont(new Font(g.getFont().getName(), Font.BOLD, 32));
		g.drawString("PAUSED hit ESCAPE to un-pause", getWidth() / 3, getHeight() / 3);
	}

	public void drawDeathScreen(Graphics2D g) {
		g.setColor(new Color(255, 0, 0, 50));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		g.drawString("YOU DIED", getWidth() / 2, getHeight() / 2);
	}

	public void tick(int tickNum) {
		logic.tick(tickNum);
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

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

}
