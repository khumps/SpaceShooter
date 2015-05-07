import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bullet extends Projectile {
	protected static final int VELOCITY = 20;
	protected static final int DAMAGE = 10;

	public Bullet(Point location, double orientation, Ship source, Screen screen) {
		super(Utils.loadImage("bullet.png"), location, orientation, DAMAGE,
				VELOCITY, source, new Bounds(new Rectangle(4,4,1,1)), screen);
	}

}
