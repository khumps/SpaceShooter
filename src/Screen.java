import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Screen extends JPanel {
	private HashMap<String, String> sprites = Entity.loadSprites();
	private BufferedImage background;
	private BufferedImage bomb;
	private BufferedImage playerPic;
	private BufferedImage turret;
	private Player player;
	protected ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<Entity> toRemove = new ArrayList<Entity>();

	public Screen() {
		setPreferredSize(new Dimension(500, 500));
		try {
			setPreferredSize(new Dimension(600, 600));
			background = ImageIO.read(getClass().getResourceAsStream(
					sprites.get("BACKGROUND")));
			playerPic = ImageIO.read(getClass().getResourceAsStream(
					sprites.get("SHIP_PLAYER")));
			bomb = ImageIO.read(getClass().getResourceAsStream(
					sprites.get("WEAPON_TORPEDO")));
			turret = ImageIO.read(getClass().getResourceAsStream(sprites.get("TURRET")));
			if (playerPic == null)
				System.out.println("DGFSAF");
		} catch (IOException e) {
			System.out.println("error");
		}

		player = new Player(new Point(getWidth() / 2, getHeight() / 2),
				playerPic, 1000, new Bomb(turret,new Point(getWidth() / 2,
						getHeight() / 2), bomb, 100, 10, 2), this);
		entities.add(player);
		setVisible(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		player.isFiring = true;
		player.bounds.setLocation(getWidth() / 2, getHeight() / 2);
		for (int i = 0; i < getWidth(); i += background.getWidth()) {
			for (int j = 0; j < getHeight(); j += background.getHeight()) {
				g.drawImage(background, i, j, null);
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			System.out.println("test");
			Entity e = entities.get(i);
			System.out.println(entities.size());
			//if(!this.getBounds().contains(e.bounds)) entities.remove(i);
			//else
			{
				if(e instanceof Projectile)
					g.drawImage(e.img, e.center.x, e.center.y, Projectile.PROJECTILE_SIZE, Projectile.PROJECTILE_SIZE, this);

				else
					g.drawImage(e.img, e.bounds.x, e.bounds.y, this);
			}

		}
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.add(new Screen());
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
	}
}
