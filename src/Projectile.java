import java.awt.image.BufferedImage;


public class Projectile extends Entity {
	private final int damage;
	private final int direction;
	private final int velocity = 10;
	private final int fireRate;
	public final int PROJECTILE_SIZE = 20;

	public Projectile(BufferedImage img,int damage, int direction, int fireRate) {
		super(img);
		this.damage = damage;
		this.direction = direction;
		this.fireRate = fireRate;
	}
	public Projectile(Projectile p)
	{
		super(new Point(p.bounds.x,p.bounds.y),p.img);
		this.damage = p.damage;
		this.direction = p.direction;
		this.fireRate = p.fireRate;
	}
	
	protected void tick(int tickNum)
	{
		if(direction == 1)
		super.move(0, velocity * - 1);
		if(direction == 2)
		super.move(velocity, velocity * - 1);
		if(direction == 3)
		super.move(velocity, 0);
		if(direction == 4)
		super.move(velocity * - 1, velocity);
		if(direction == 5)
		super.move(0, velocity);
		if(direction == 6)
		super.move(velocity * - 1, velocity);
		if(direction == 7)
			super.move(velocity * - 1, 0);
		if(direction == 8)
		super.move(velocity * - 1, velocity * - 1);
		
	}
	
	protected void doDamage(Ship s)
	{
		s.takeDamage(damage);
	}

}
