import java.awt.image.BufferedImage;


public class Bomb extends Weapon {
	protected Point firePoint;

	public Bomb(Point source, BufferedImage img, int damage, int velocity,int fireRate) {
		super(source, new Projectile(source, img, 100, 5, 30));
	}

	public Projectile fire(int direction) {
		projectile.orientation = direction;
		System.out.println("FIRE");
		return new Projectile(super.projectile.center, projectile.img,projectile.damage,direction,projectile.fireRate);		
	}
}
