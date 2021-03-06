
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
	PointDouble mousePosition = new PointDouble(1, 1);
	private boolean mousePressed = false;
	Screen screen;

	public Listener(Screen s) {
		screen = s;

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		/* Draw hitboxes */
		if (key == KeyEvent.VK_F3) {
			if (screen.debug)
				screen.debug = false;
			else
				screen.debug = true;

		}

		/* Radar zoom in */
		if (key == KeyEvent.VK_MINUS && screen.zoom >= 2)
			screen.zoom -= 5;
		if (key == KeyEvent.VK_EQUALS)
			screen.zoom += 5;

		/* Toggle fullscreen */
		else if (key == KeyEvent.VK_F11) {
			if (screen.isFullscreen)
				screen.disableFullscreen();
			else
				screen.enableFullscreen();
		}

		else if (key == KeyEvent.VK_ESCAPE) {
			if (screen.paused)
				screen.unPause();
			else {
				screen.pause();
			}
		}
		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT
				|| key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
			keys.add(key);
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

	private void act() {
		if (keys.contains(KeyEvent.VK_LEFT)) {

			if (keys.contains(KeyEvent.VK_UP))
				screen.player.setOrientation(Entity.UP_LEFT);
			else if (keys.contains(KeyEvent.VK_DOWN))
				screen.player.setOrientation(Entity.DOWN_LEFT);
			else
				screen.player.setOrientation(Entity.LEFT);

		} else if (keys.contains(KeyEvent.VK_RIGHT)) {

			// System.out.println("Right");
			if (keys.contains(KeyEvent.VK_DOWN)) {
				screen.player.setOrientation(Entity.DOWN_RIGHT);
				// System.out.println("Right-Down");
			} else if (keys.contains(KeyEvent.VK_UP)) {
				screen.player.setOrientation(Entity.UP_RIGHT);
				// System.out.println("Right-Up");
			} else
				screen.player.setOrientation(Entity.RIGHT);
		}

		else if (keys.contains(KeyEvent.VK_UP)) {
			screen.player.setOrientation(Entity.UP);
			// System.out.println("RAN");
		} else if (keys.contains(KeyEvent.VK_DOWN)) {
			// System.out.println("RAN");
			screen.player.setOrientation(Entity.DOWN);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (command.equals("timer")) {
			act();
			if (keys.size() > 0) {
				screen.player.move(PlayerShip.PLAYER_VELOCITY);

			}
			screen.player.update();
			screen.player.moveTurret(mousePosition);

			screen.tick(screen.tickNum++);
			if (mousePressed) {

				screen.player.fire(screen.tickNum);
			}
			Long start = System.nanoTime();
			screen.repaint();
			Long end = System.nanoTime();
			// System.out.println(end - start);
		}

	}

	public void mouseMoved(MouseEvent e) {
		// PointDouble offset = screen.cam.tick();
		mousePosition.moveTo(e.getX(), e.getY());
		screen.player.moveTurret(mousePosition);
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		mousePressed = true;
		System.out.println(screen.player.getPosition() + "PLAYER");
	}

	public void mouseReleased(MouseEvent e) {
		mousePressed = false;
	}

	public void mouseDragged(MouseEvent e) {
		mousePosition.moveTo(e.getX(), e.getY());
		screen.player.moveTurret(mousePosition);
		screen.player.fire(screen.tickNum);
	}

}
