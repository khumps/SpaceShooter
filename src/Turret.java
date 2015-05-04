import java.awt.image.BufferedImage;


public class Turret extends Entity {
	protected Projectile projectile;
	public static final BufferedImage TURRET_IMAGE = Utils.loadImage("turret.png");

	public Turret(BufferedImage img, double orientation, int fireRate, Projectile projectile) {
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
