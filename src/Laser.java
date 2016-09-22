
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Laser extends Projectile {
	private static final int VELOCITY = 50;
	private static final int DAMAGE = 2000;
	private int PENATRATION = 5;

	public Laser(PointDouble pointDouble, double orientation, Ship source, Screen screen) {
		super(Utils.loadImage("laser.png"), pointDouble, orientation, DAMAGE, VELOCITY, source,
				new Bounds(new Rectangle(4, 4, 1, 1)), screen);

	}

	@Override
	public void collides(Entity e) {
		if (e != source)
			if (e instanceof Ship) {
				if (source.isEnemy((Ship) e)) {
					((Ship) e).takeDamage(DAMAGE);
					collided = true;
					if (PENATRATION <= 0)
						screen.getEntities().remove(this);
					else
						PENATRATION--;
				}
			}
	}

	@Override
	public void update(int tickNum) {
		for (int i = 0; i < VELOCITY; i++) {
			move(1);
			screen.repaint();
		}
	}

}
