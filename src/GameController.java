

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.Timer;

public class GameController extends JFrame{
	private Timer timer;
	private Screen screen;
<<<<<<< HEAD
	GameListener listener = new GameListener(this);
	private boolean pause = false;
	private int numTicks = 0;
	public GameController() {
		timer = new Timer(30, listener);
		timer.setActionCommand("timer");
=======
	private int tickNum = 0;
	public GameController() {
		
>>>>>>> origin/master
		screen = new Screen();
		getContentPane().add(screen);
		pack();
		timer.start();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void addEntity(Entity e)
	{
		screen.entities.add(e);
		tick();
	}
	
	public void tick()
	{
<<<<<<< HEAD
		if(!pause)
		if(numTicks > 500)
			numTicks = 0;
		for(Entity e: screen.entities)
		{
			e.tick(numTicks);
		}
		
	}
	
	public static void main(String[] args)
	{
		
		GameController g = new GameController();
		g.tick();
		
=======
		for(Entity e: screen.entities)
		{
			e.tick(tickNum);
		}
		tickNum++;
>>>>>>> origin/master
	}
}
