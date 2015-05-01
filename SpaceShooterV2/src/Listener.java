import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

public class Listener implements KeyListener, ActionListener {
	private HashSet<Integer> keys = new HashSet<Integer>();
	Screen screen;
	public Listener(Screen s) {
		screen = s;

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		keys.add(c);
		if (c == (KeyEvent.VK_LEFT)) {
			System.out.println("Left");
			screen.player.setOrientation(Math.toRadians(-10));
		}

		if (c == (KeyEvent.VK_RIGHT)) {
			System.out.println("Left");
			screen.player.setOrientation(Math.toRadians(10));
		}
		if (c == (KeyEvent.VK_UP)) {
			System.out.println("Left");
			screen.player.move(10);
		}
		if (c == (KeyEvent.VK_DOWN)) {
			System.out.println("Left");
			screen.player.move(-10);
		}


		screen.repaint();

	}
	@Override
	public void keyReleased(KeyEvent e) {
		int c = e.getKeyCode();
		keys.remove(c);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
	private void act()
	{
		if (keys.contains(KeyEvent.VK_LEFT)) {
			System.out.println("Left");
			screen.player.setOrientation(Math.toRadians(-10));
		}

		if (keys.contains(KeyEvent.VK_RIGHT)) {
			System.out.println("Left");
			screen.player.setOrientation(Math.toRadians(10));
		}
		if (keys.contains(KeyEvent.VK_UP)) {
			System.out.println("Left");
			screen.player.move(10);
		}
		if (keys.contains(KeyEvent.VK_DOWN)) {
			System.out.println("Left");
			screen.player.move(-10);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if(command.equals("timer"))
			act();
		
	}

}
