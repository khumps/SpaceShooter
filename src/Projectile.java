import java.awt.image.BufferedImage;

public class Projectile extends Entity {
	protected final int damage;
	protected static final int BULLET_VELOCITY = 10;
	private final int velocity;

	public Projectile(BufferedImage img, Point location, double orientation, int damage, int velocity) {
		super(Utils.rotate(img, Math.toRadians(90)), orientation, location);
		
		this.damage = damage;
		this.velocity = velocity;
	}
	
	public void update()
	{	
		move(BULLET_VELOCITY);
	}

}