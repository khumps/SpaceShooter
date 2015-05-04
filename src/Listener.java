import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;

public class Listener extends MouseAdapter implements KeyListener,
		ActionListener {
	private HashSet<Integer> keys = new HashSet<Integer>();
	Point mousePosition;
	Screen screen;

	public Listener(Screen s) {
		screen = s;

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		keys.add(c);
		act();
		screen.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int c = e.getKeyCode();
		act();
		keys.remove(c);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	private void act() {
		if (keys.contains(KeyEvent.VK_LEFT)) {

			if (keys.contains(KeyEvent.VK_UP))
				screen.player.setOrientation(-135);
			else if (keys.contains(KeyEvent.VK_DOWN))
				screen.player.setOrientation(135);
			else
				screen.player.setOrientation(-180);

		} else if (keys.contains(KeyEvent.VK_RIGHT)) {

			// System.out.println("Right");
			if (keys.contains(KeyEvent.VK_DOWN)) {
				screen.player.setOrientation(45);
				// System.out.println("Right-Down");
			} else if (keys.contains(KeyEvent.VK_UP)) {
				screen.player.setOrientation(-45);
				// System.out.println("Right-Up");
			} else
				screen.player.setOrientation(0);
		}

		else if (keys.contains(KeyEvent.VK_UP)) {
			screen.player.setOrientation(-90);
			// System.out.println("RAN");
		} else if (keys.contains(KeyEvent.VK_DOWN)) {
			// System.out.println("RAN");
			screen.player.setOrientation(90);
		}
		if (keys.size() > 0)
			screen.player.move(1);
		screen.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (command.equals("timer")) {
			act();
			long start = System.nanoTime();
			screen.player.moveTurret(mousePosition);
			long stop = System.nanoTime();
			System.out.println("BLAH");

			screen.player.update();

		}

	}

	public void mouseMoved(MouseEvent e) {
		mousePosition = new Point(e.getX(), e.getY());
		screen.player.moveTurret(mousePosition);
	}

}
