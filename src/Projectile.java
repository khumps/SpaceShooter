import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Projectile extends Entity {

	public static final int TORPEDO_VELOCITY = 10;
	public static final int TORPEDO_DAMAGE = 100;
	private static final Rectangle TORPEDO_COLISION = new Rectangle(31,99,169,58);
	protected final int damage;
	protected final int velocity;

	public Projectile(BufferedImage img, Point location, double orientation,
			int damage, int velocity, Screen screen) {
		super(Utils.rotate(img, Math.toRadians(90)), orientation, location, TORPEDO_COLISION, screen);

		this.damage = damage;
		this.velocity = velocity;
	}

	public void update(int tickNum) {
		move(velocity);
	}
	
	public void collides(Entity e)
	{
		if(e instanceof Ship)
			((Ship) e).takeDamage(damage);
		screen.entities.remove(this);
	}

}