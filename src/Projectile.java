import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

public class Projectile extends Entity {
	public static final Rectangle TORPEDO_COLISION = new Rectangle(31, 99, 169,
			58);
	protected final int damage;
	protected final int velocity;
	private Ship source;

	public Projectile(BufferedImage img, Point location, double orientation,
			int damage, int velocity, Ship source, Bounds bounds, Screen screen) {
		super(Utils.rotate(img, Math.toRadians(0)), orientation, location,
				bounds, screen);
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
					System.out.println("Collided with" + e.getClass());
					((Ship) e).takeDamage(damage);
					screen.entities.remove(this);
				}
				System.out.println(source + " " + e);
			}


	}

	@Override
	public boolean doesCollide(Entity e)  {
		if (this.collisionArea.intersects(e.collisionArea)) {
			collides(e);
			e.collides(this);
			return true;
		}
		return false;
	}

}