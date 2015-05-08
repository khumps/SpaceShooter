import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

public class Turret extends Entity {
	protected Projectile projectile;
	protected final int fireRate;
	public static final BufferedImage TURRET_IMAGE = Utils
			.loadImage("turret.png");
	protected Ship ship;

	public Turret(BufferedImage img, double orientation, int fireRate,
			Projectile projectile, Ship ship, Screen screen) {
		super(img, orientation, new PointDouble(100, 100), new Bounds(new Area(
				Projectile.TORPEDO_COLISION)), screen);
		this.projectile = projectile;
		this.fireRate = fireRate;
		this.ship = ship;

	}

	@Override
	public void setPosition(PointDouble p) {
		super.setPosition(p);
		projectile.setPosition(p);
	}

	@Override
	public void setOrientation(double o) {
		super.setOrientation(o);
		projectile.setOrientation(o);
	}

	@Override
	public void update(int tickNum) {
		// TODO Auto-generated method stub

	}

	@Override
	public void collides(Entity e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean doesCollide(Entity e) {
		return false;
	}

	public void fire(int tickNum) {
	}

}
