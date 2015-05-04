import java.awt.image.BufferedImage;

public class Projectile extends Entity {
	private final int damage;
	public static final int BULLET_VELOCITY = 10;
	private final int velocity;

	public Projectile(BufferedImage img, Point location, double orientation, int damage, int velocity) {
		super(img, orientation, location);
		this.damage = damage;
		this.velocity = velocity;
	}
	
	public void update()
	{	
		
	}

}