import java.awt.image.BufferedImage;


public abstract class Ship extends Entity {
	private int health;
	private Turret turret;
	private Screen screen;

	public Ship(BufferedImage img, double orientation, Point location, int health, Turret turret, Screen screen) {
		super(img,orientation,location);
		this.turret = turret;
		this.health = health;
		this.screen = screen;
		turret.setLocation(super.getLocation());
		turret.setOrientation(getOrientation());
	}

}
