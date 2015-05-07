import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bullet extends Projectile {
	protected static final int VELOCITY = 20;
	protected static final int DAMAGE = 50;

	public Bullet(Point location, double orientation, Ship source, Screen screen) {
		super(Utils.loadImage("bullet.png"), location, orientation, DAMAGE,
				VELOCITY, source, new Bounds(new Rectangle(4, 4, 1, 1)), /*new Explosion(),*/ screen);
	}

	public void collides(Entity e) {
		if (e != source /* && !(e instanceof PlayerShip) */)
			if (e instanceof Ship) {
				if (source.isEnemy((Ship) e)) {
					((Ship) e).takeDamage(damage);
					//effect.drawEffect(screen.getGraphics(), this.getPosition());
					screen.entities.remove(this);
				}
			}

	}

}
