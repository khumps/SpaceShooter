import java.awt.image.BufferedImage;


public class Turret extends Entity {
	private Projectile projectile;

	public Turret(BufferedImage img, double orientation, Projectile projectile) {
		super(img, orientation, null);
		this.projectile = projectile;
		
	}
	
	@Override
	public void setLocation(Point p)
	{
		super.setLocation(p);
		projectile.setLocation(p);
	}
	
	@Override
	public void setOrientation(double o)
	{
		super.setOrientation(o);
		projectile.setOrientation(o);
	}

}
