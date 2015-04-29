

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class GameController {
	BufferedImage playerPic = ImageIO.read(getClass().getResourceAsStream(
			"resources//background_tile.png"));
	BufferedImage bomberPic = ImageIO.read(getClass().getResourceAsStream(
			"resources//background_tile.png"));
	protected Player player;
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	public GameController() {
		BufferedImage pic = 
		player = new Player(playerPic,);
	}

	private void addEntity(Entity e)
	{
		entities.add(e);
		tick();
	}
	
	public void tick()
	{
		player.tick();
		for(Entity e: entities)
		{
			e.tick();
		}
	}
}
