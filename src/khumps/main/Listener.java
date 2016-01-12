package khumps.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.util.HashSet;

public class Listener extends MouseAdapter implements ActionListener, KeyListener {

	private HashSet<Integer> keys = new HashSet<Integer>();

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
		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
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
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
