import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bullet extends Projectile {
	protected static final int VELOCITY = 13;
	protected static final int DAMAGE = 20;

	public Bullet(PointDouble location, double orientation, Ship source,
			Screen screen) {
		super(Utils.loadImage("bullet.png"), location, orientation, DAMAGE,
				VELOCITY, source, new Bounds(new Rectangle(4, 4, 1, 1)), screen);
	}

	public Bullet(PointDouble location, double orientation, Ship source,
			Screen screen, int blah) {
		super(Utils.loadImage("player_bullet.png"), location, orientation,
				DAMAGE, VELOCITY, source,
				new Bounds(new Rectangle(4, 4, 1, 1)), screen);
	}

	@Override
	public void collides(Entity e) {
		if (e != source && !(e instanceof PlayerShip))
			if (e instanceof Ship) {
				if (source.isEnemy((Ship) e)) {
					((Ship) e).takeDamage(damage);
					collided = true;
					// effect.drawEffect(screen.g2, getPosition());
					screen.entities.remove(this);
				}
			}

	}

}
