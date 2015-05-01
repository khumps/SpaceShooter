
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.Timer;

public class GameController extends JFrame {
	private Timer timer;
	private Screen screen;
	GameListener listener = new GameListener(this);
	private boolean pause = false;
	private int numTicks = 0;
	private int tickNum = 0;

	public GameController() {

		screen = new Screen();
		timer = new Timer(100, listener);
		timer.setActionCommand("timer");
		getContentPane().add(screen);
		pack();
		timer.start();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void addEntity(Entity e) {
		screen.entities.add(e);
		tick();
	}

	public void tick() {
		if (!pause)
			if (numTicks > 500)
				numTicks = 0;
		for (int i = 0; i < screen.entities.size(); i++) {
			if (screen.entities.get(i) != null)
				screen.entities.get(i).tick(numTicks);
		}
		numTicks++;
		screen.repaint();
	}

	public static void main(String[] args) {

		GameController g = new GameController();
		g.tick();

	}
}
