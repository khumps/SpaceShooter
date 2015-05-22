import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JViewport;
import javax.swing.Timer;

public class Screen extends JPanel
	{
		protected ArrayList<Entity> entities = new ArrayList<Entity>();
		protected ArrayBlockingQueue<Method> methods = new ArrayBlockingQueue<Method>(100);
		private Listener listener = new Listener(this);
		protected int tickNum = 0;
		private int timerSpeed = 16;
		Timer timer = new Timer(timerSpeed, listener);
		int i = 0;
		protected Point playerMovement = new Point(0, 0);
		protected PlayerShip player = new PlayerShip(new PointDouble(500, 500), this);
		private BufferedImage background = Utils.loadImage("space.png");
		protected Graphics2D g2;
		protected boolean debug = false;
		protected boolean isFullscreen = false;
		private JFrame frame = new JFrame();
		protected int score = 0;
		private Rectangle oldBounds;
		private int oldState;
		protected boolean paused = false;
		protected double zoom = 20;

		// protected Camera cam = new Camera(player);
		// protected PointDouble offset = cam.tick();

		public Screen()
			{
				frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
				addKeyListener(listener);
				addMouseListener(listener);
				addMouseMotionListener(listener);
				setFocusable(true);
				setPreferredSize(new Dimension(750, 750));
				entities.add(player);
				frame.add(this);
				timer.setActionCommand("timer");
				frame.pack();
				frame.setVisible(true);
				if (!paused) timer.start();
			}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g2 = (Graphics2D) g;
			// offset = cam.tick();
			PointDouble corner = new PointDouble(getBounds().x, getBounds().y);
			for (int i = 0; i < getWidth(); i += background.getWidth())
				{
					for (int j = 0; j < getHeight(); j += background.getHeight())
						{
							g.drawImage(background, i, j, null);
						}
				}
			/* Draw Entities */
			for (int i = 0; i < entities.size(); i++)
				{
					Entity e = entities.get(i);
					e.update(tickNum);
					e.draw(g2, corner, player.getPosition());
					if (e instanceof EnemyShip) drawHealth(g2, (Ship) e);

					if (debug)
						{
							drawHitBox(g2, e);
							timer.setDelay(timerSpeed * 4);
						}
					else
						timer.setDelay(timerSpeed);
				}
			g.drawString(tickNum + "", 500, 500);

			drawHud(g2);
		}

		public void purgeEntities() {
			for (int i = 0; i < entities.size(); i++)
				{
					Entity e = entities.get(i);
					double dist = Utils.getDistance(e.getPosition(), player.getPosition());
					if (!(e instanceof Ship)) if (dist > 1000) entities.remove(i);
					if (e instanceof EnemyShip) if (dist > 1500) entities.remove(i);
				}
		}

		public void tick(int tickNum) {
			checkCollision();

			if (tickNum % 10 == 0) purgeEntities();

			if (tickNum % 20 == 0 && getNumShips() < 3)
				{
					entities.add(new EnemyShip(pointOnScreen(player.getPosition()), 1, this));
				}
		}

		public void checkCollision() {
			for (int i = 0; i < entities.size(); i++)
				{
					Entity e1 = entities.get(i);
					if (e1 instanceof Ship) for (int j = 1; j < entities.size(); j++)
						{
							e1.doesCollide(entities.get(j));
						}
				}
		}

		public int getNumShips() {
			int numShips = 0;
			for (Entity e : entities)
				{
					if (e instanceof Ship) numShips++;
				}
			return numShips;
		}

		public PointDouble pointOnScreen(PointDouble p/* ,double maxX,double maxY */) {
			return new PointDouble(Math.random() * (getWidth() + p.x) / 2 - p.x, Math.random()
					* (getHeight() + p.y) / 2 - p.y);
		}

		public synchronized void drawHealth(Graphics2D g, Ship s) {
			if (s.getHealth() < s.getMaxHealth())
				{
					int x = (int) (s.getPosition().x - player.getPosition().x - s.img.getWidth()
							/ 2 + this.getWidth() / 2);
					int y = (int) ((int) s.getPosition().y - player.getPosition().y
							- s.img.getHeight() / 2 + this.getHeight() / 2);
					g.setColor(Color.RED);
					g.drawRect(x, y, s.getMaxHealth() / 2, 10);
					g.fillRect(x, y, s.getHealth() / 2, 10);
				}
		}

		public void drawHitBox(Graphics2D g, Entity e) {
			if (debug)
				{
					g.setColor(Color.GREEN);
					g2.draw(e.collisionArea.getArea());
				}
		}

		public void drawHud(Graphics2D g) {
			drawPlayerHealth(g);
			g.setStroke(new BasicStroke(3));
			g.setColor(Color.GRAY);
			g.drawRect(50, 50, 100, 20);
			g.setColor(Color.GREEN);
			g.drawString("Score: " + score, 55, 65);
			drawRadar(g);
		}

		public synchronized void drawPlayerHealth(Graphics2D g) {

			g.setStroke(new BasicStroke(3));
			g.setColor(Color.GRAY);
			g.drawRect(20, getHeight() - getHeight() / 15, player.getMaxHealth() / 2, 20);
			g.setColor(Color.GREEN);
			g.fillRect(20, getHeight() - getHeight() / 15, player.getHealth() / 2, 20);
			// System.out.println(player.getPosition());
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
			for (Entity e : entities)
				{
					if (e instanceof Ship && !(e instanceof PlayerShip))
						{
							g.setColor(Color.black);
							PointDouble temp = Utils.translateToView(e.getPosition(),
									player.getPosition(), this);
							g.drawRect(
									(int) (getWidth() - size + (temp.x / size) * zoom + size / 2 + .5),
									(int) (getHeight() - size + (temp.y / size) * zoom + size / 2 + .5),
									iconSize, iconSize);
						}
				}
			g.setColor(Color.ORANGE);
			PointDouble temp = Utils.translateToView(player.getPosition(), player.getPosition(),
					this);
			g.drawRect((int) (getWidth() - size + (temp.x / size) * zoom + size / 2 + .5),
					(int) (getHeight() - size + (temp.y / size) * zoom + size / 2 + .5), iconSize,
					iconSize);
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
