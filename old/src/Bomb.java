import java.awt.image.BufferedImage;

public class Bomb extends Weapon {
	protected Point firePoint;

	public Bomb(BufferedImage img, Point source, BufferedImage imgP, int damage, int velocity,
			int fireRate) {
		super(img, source, new Projectile(source, imgP, 100, 0, 300));
	}

	public Projectile fire(Point source,int direction) {
		setOrientation(direction);
		System.out.println(projectile.getOrientation());
		return new Projectile(source, projectile.img,
				projectile.damage,getOrientation(), projectile.fireRate);
		
	}

}
