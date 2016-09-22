
import java.awt.image.BufferedImage;

public class LaserTurret extends Turret {

	public LaserTurret(BufferedImage img, double orientation, int fireRate,
			Projectile projectile, Ship ship, Screen screen) {
		super(img, orientation, fireRate, projectile, ship, screen);
	}

	public void setNumUpgrades(int amt) {
		numUpgrades = amt;
	}

	public Projectile addProjectile(double direction) {
		return new Laser(getPosition(), direction, ship, screen);
	}
}
