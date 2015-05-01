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
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private  Listener listener = new Listener(this);
	Timer timer = new Timer(1, listener);
	int i = 0;
	protected PlayerShip player = new PlayerShip(Utils.loadImage(this, "starship.png"), 0.0, new Point(500,500), 1000,
			new Turret(Utils.loadImage(this, "turret.png"), 10, new Projectile(Utils.loadImage(this, "torpedo.png"))), this);
	private BufferedImage background = Utils.loadImage(this, "space.png");

	public Screen() {
		addKeyListener(listener);
		setFocusable(true);
		setPreferredSize(new Dimension(1000,1000));
		entities.add(player);
		timer.setActionCommand("timer");
		//timer.start();
		setVisible(true);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (int i = 0; i < getWidth(); i += background.getWidth()) {
			for (int j = 0; j < getHeight(); j += background.getHeight()) {
				g.drawImage(background, i, j, null);
			}
		}
		for(int i = 0; i < entities.size(); i++)
		{
			Entity e = entities.get(i);
			e.draw(g2,new Point(getBounds().x,getBounds().y));
		}
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.add(new Screen());
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);

	}

}
