import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Screen extends JPanel{
	private HashMap<String, String> sprites = Entity.loadSprites();
	private BufferedImage background;
	private BufferedImage bomb;
	private BufferedImage playerPic;
	private Player player;
	protected ArrayList<Entity> entities = new ArrayList<Entity>();


	public Screen() {
		setPreferredSize(new Dimension(500,500));
		try{
		background = ImageIO.read(getClass().getResourceAsStream(sprites.get("BACKGROUND")));
		playerPic = ImageIO.read(getClass().getResourceAsStream(sprites.get("SHIP_PLAYER")));
		bomb =  ImageIO.read(getClass().getResourceAsStream(sprites.get("WEAPON_TORPEDO")));
		if(playerPic == null) System.out.println("DGFSAF");
		}
		catch(IOException e){System.out.println("error");}
		player = new Player(new Point(300,300),playerPic, 1000, new Bomb(bomb,100,10,2),this);
		entities.add(player);
		
		
		setVisible(true);
	}
	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		player.isFiring = true;
		for(int i = 0; i < getWidth(); i+= background.getWidth())
		{
			for(int j = 0; j < getHeight(); j+= background.getHeight())
			{
				g.drawImage(background, i, j, null);
			}
		}
		for(Entity e: entities)
		{
			g.drawImage(e.img, e.bounds.x, e.bounds.y, null);
		}
	}
	
	public static void main(String[] args)
	{
		JFrame f = new JFrame();
		f.add(new Screen());
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
	}
}
