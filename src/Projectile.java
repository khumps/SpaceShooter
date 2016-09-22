
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

public abstract class Projectile extends Entity {
	public static final Rectangle TORPEDO_COLISION = new Rectangle(31, 99, 169,
			58);
	protected int damage;
	protected final int velocity;
	protected Ship source;
	protected boolean collided = false;

	public Projectile(BufferedImage img, PointDouble pointDouble,
			double orientation, int damage, int velocity, Ship source,
			Bounds bounds, Screen screen) {
		super(Utils.rotate(img, Math.toRadians(0)), orientation, pointDouble,
				bounds, screen);

		this.source = source;
		this.damage = damage;
		this.velocity = velocity;
		//Utils.resizeProjectile(this);
	}

	public void update(int tickNum) {
		move(velocity);
	}

	public abstract void collides(Entity e); /*{
		if (e != source  && !(e instanceof PlayerShip) )
			if (e instanceof Ship) {
				if (source.isEnemy((Ship) e)) {
					((Ship) e).takeDamage(damage);
					collided = true;
					// effect.drawEffect(screen.g2, getPosition());
					screen.entities.remove(this);
				}
			}

	}*/

	@Override
	public boolean doesCollide(Entity e) {
		if (this.collisionArea.intersects(e.collisionArea)) {
			collides(e);
			e.collides(this);
			return true;
		}
		return false;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

}