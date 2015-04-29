

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class GameController {
	private Screen screen;
	public GameController() {
		screen = new Screen();
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
