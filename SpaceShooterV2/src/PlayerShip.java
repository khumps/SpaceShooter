import java.awt.image.BufferedImage;


public class PlayerShip extends Ship {

	public PlayerShip(BufferedImage img, double orientation, Point location, int health, Turret turret, Screen screen) {
		super(img, orientation, location, health, turret, screen);
	}

}
