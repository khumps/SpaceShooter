import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Projectile extends Entity {
	public final int damage;
	public final int velocity = 10;
	public final int fireRate;
	public static final int PROJECTILE_SIZE = 45;

	public Projectile(Point source, BufferedImage img,int damage, int direction, int fireRate) {
		super(source, img);
		this.damage = damage;
		this.fireRate = fireRate;
		setOrientation(direction);
		System.out.println(getOrientation());
	}
	public Projectile(Projectile p)
	{
		super(new Point(p.bounds.x,p.bounds.y),p.img);
		this.damage = p.damage;
		setOrientation(p.getOrientation());
		this.fireRate = p.fireRate;
	}
	protected void tick(int tickNum)
	{
		if(getOrientation() == 1)
		super.move(0, velocity * - 1);
		if(getOrientation() == 2)
		super.move(velocity, velocity * - 1);
		if(getOrientation() == 3)
		super.move(velocity, 0);
		if(getOrientation() == 4)
		super.move(velocity * - 1, velocity);
		if(getOrientation() == 5)
		super.move(0, velocity);
		if(getOrientation() == 6)
		super.move(velocity * - 1, velocity);
		if(getOrientation() == 7)
			super.move(velocity * - 1, 0);
		if(getOrientation() == 8)
		super.move(velocity * - 1, velocity * - 1);
		
	}
	
	public void collidedWith(Entity other)
	{
		if(other instanceof Ship)
			((Ship) other).takeDamage(damage);
	}

}
