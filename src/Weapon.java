import java.awt.image.BufferedImage;

public abstract class Weapon extends Entity {
	protected final Projectile projectile;
	protected Point source;

	public Weapon(BufferedImage img, Point source, Projectile projectile) {
		super(source,img);
		this.source = source;
		this.projectile = projectile;
	}

	public abstract Projectile fire(Point source, int direction);

	public void tick(int tickNum)
	{
		
	}
	
	public void setOrientation(int direction)
	{
		
	}
}
