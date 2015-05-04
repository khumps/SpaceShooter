import java.awt.image.BufferedImage;


public class Turret extends Entity {
	private Projectile projectile;

	public Turret(BufferedImage img, double orientation, int fireRate, Projectile projectile, Screen screen) {
		super(img, orientation, null);
		this.projectile = projectile;
		
	}
	
	@Override
	public void setPosition(Point p)
	{
		super.setPosition(p);
		projectile.setPosition(p);
	}
	
	@Override
	public void setOrientation(double o)
	{
		super.setOrientation(o);
		projectile.setOrientation(o);
	}
	
	public void update()
	{
		
	}

}
