import java.awt.image.BufferedImage;

public class Projectile extends Entity {

	public static final int TORPEDO_VELOCITY = 10;
	public static final int TORPEDO_DAMAGE = 100;
	public static final Projectile TORPEDO_PROJECTILE = new Projectile(
			Utils.loadImage("torpedo.png"), new Point(1, 1), 10,
			TORPEDO_DAMAGE, TORPEDO_VELOCITY);
	protected final int damage;
	protected final int velocity;

	public Projectile(BufferedImage img, Point location, double orientation,
			int damage, int velocity) {
		super(Utils.rotate(img, Math.toRadians(90)), orientation, location);

		this.damage = damage;
		this.velocity = velocity;
	}

	public void update() {
		move(velocity);
	}

}