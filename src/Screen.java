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
	Timer timer = new Timer(1, listener);
	int i = 0;
	protected Point playerMovement = new Point(0,0);
	protected PlayerShip player = new PlayerShip(new Point(500, 500), this);
	private BufferedImage background = Utils.loadImage("space.png");

	public Screen() {
		addKeyListener(listener);
		addMouseListener(listener);
		addMouseMotionListener(listener);
		setFocusable(true);
		setPreferredSize(new Dimension(1000, 1000));
		entities.add(player);
		timer.setActionCommand("timer");
		timer.start();
		setVisible(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Point corner = new Point(getBounds().x, getBounds().y);
		for (int i = 0; i < getWidth(); i += background.getWidth()) {
			for (int j = 0; j < getHeight(); j += background.getHeight()) {
				g.drawImage(background, i, j, null);
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.update();
			purgeEntities();
/*			if (e instanceof Projectile)
				e.drawProjectile(g2, corner, player);
			else*/
				e.draw(g2, corner);
		}
	}
	
	public void purgeEntities()
	{
		for(int i = 0; i < entities.size(); i++)
		{
			Entity e = entities.get(i);
			if(!(e instanceof PlayerShip))
			if(!getBounds().contains(e.getPosition().x, e.getPosition().y))
				entities.remove(i);	
		}
	}
	
	public void tick()
	{
		Double r = Math.random() * 100;
		if(r / 10 == 10)
			entities.add(new EnemyShip(pointOnScreen(),this));
	}
	
	public Point pointOnScreen()
	{
		return new Point(Math.random() * getWidth(), Math.random() * getHeight());
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.add(new Screen());
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);

	}

}
