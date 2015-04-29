

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class GameController {
	private Screen screen;
	private int tickNum = 0;
	public GameController() {
		
		screen = new Screen();
	}

	public void addEntity(Entity e)
	{
		screen.entities.add(e);
		tick();
	}
	
	public void tick()
	{
		for(Entity e: screen.entities)
		{
			e.tick(tickNum);
		}
		tickNum++;
	}
}
