import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Laser extends Projectile {
	private static final int VELOCITY = 50;
	private static final int DAMAGE = 20;
	private static final int PENATRATION = 5;
	private int penatration = 5;

	public Laser(PointDouble pointDouble, double orientation, Ship source,
			Screen screen) {
		super(Utils.loadImage("laser.png"), pointDouble, orientation, DAMAGE,
				VELOCITY, source, new Bounds(new Rectangle(4, 4, 1, 1)), screen);

	}

	@Override
	public void collides(Entity e) {
		if (e != source)
			if (e instanceof Ship) {
				if (source.isEnemy((Ship) e)) {
					((Ship) e).takeDamage(DAMAGE);
					collided = true;
					if (penatration <= 0)
						screen.entities.remove(this);
					else
						penatration--;
				}
			}
		System.out.println("override");
	}

	@Override
	public void update(int tickNum) {
		for (int i = 0; i < VELOCITY; i++) {
			move(100);
		}
		System.out.println("ran");
	}

}
