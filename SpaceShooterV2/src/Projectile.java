import java.awt.image.BufferedImage;

public class Projectile extends Entity {

	public Projectile(BufferedImage img, double orientation, Point location) {
		super(img, orientation, location);
	}

	public Projectile(BufferedImage img) {
		super(img, 0, null);
	}
}