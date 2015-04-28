import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Screen extends JPanel{
	private BufferedImage tile;

	public Screen() {
		try {
			tile = ImageIO.read(getClass().getResourceAsStream(
					"resources//background_tile.png"));
		} catch (IOException e) {
			System.out.println("ERROR ON TILE LOAD");
		}
		setVisible(true);
	}
	
	
	public void paintComponent(Graphics g)
	{
		for(int i = 0; i < getWidth(); i+= tile.getWidth())
		{
			for(int j = 0; j < getHeight(); j+= tile.getHeight())
			{
				g.drawImage(tile, i, j, null);
			}
		}
	}
	
	public static void main(String[] args)
	{
		JFrame f = new JFrame();
		f.add(new Screen());
		f.setVisible(true);
	}
}
