import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

public class Projectile extends Entity {

	public static final int TORPEDO_VELOCITY = 10;
	public static final int TORPEDO_DAMAGE = 100;
	public static final Rectangle TORPEDO_COLISION = new Rectangle(31, 99, 169,
			58);
	protected final int damage;
	protected final int velocity;
	private Ship source;

	public Projectile(BufferedImage img, Point location, double orientation,
			int damage, int velocity, Ship source, Screen screen) {
		super(Utils.rotate(img, Math.toRadians(0)), orientation, location,
				new Bounds(new Area(TORPEDO_COLISION)), screen);
		this.source = source;
		this.damage = damage;
		this.velocity = velocity;
	}

	public void update(int tickNum) {
		move(velocity);
	}

	public void collides(Entity e) {
		if (e != source /* && !(e instanceof PlayerShip) */)
			if (e instanceof Ship) {
				if (source.isEnemy((Ship)e)) {
					//System.out.println("Collided with" + e.getClass());
					((Ship) e).takeDamage(damage);
					screen.entities.remove(this);
				}
			}

	}

}