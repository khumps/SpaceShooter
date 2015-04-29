import java.awt.image.BufferedImage;


public class Bomb extends Weapon {
	private Projectile bomb;
	protected Point firePoint;

	public Bomb(BufferedImage img, int damage, int velocity,int fireRate) {
		super(new Projectile(img, 100, 1, 30));
	}

	@Override
	public Projectile fire(int direction) {
		projectile.orientation = direction;
		return new Projectile(projectile);		
	}
}
