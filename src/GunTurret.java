import java.awt.image.BufferedImage;

public class GunTurret extends Turret {
	public static final int FIRERATE = 10;
	public static final int DAMAGE = 20;

	public GunTurret(BufferedImage img, double orientation, int fireRate,
			Projectile projectile, Ship ship, Screen screen) {
		super(img, orientation, FIRERATE, projectile, ship, screen);
		// TODO Auto-generated constructor stub
	}

	public Projectile addProjectile(double direction) {
		return new Bullet(getPosition(), direction, ship, screen);
	}
}
