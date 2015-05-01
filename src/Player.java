import java.awt.image.BufferedImage;

public class Player extends Ship {
	protected boolean isFiring;
	int i = 1;

	public Player(Point corner, BufferedImage img, int health, Weapon weapon,
			Screen screen) {
		super(corner, img, health, weapon, screen);
	}

	@Override
	protected void tick(int tickNum) {
		if (isFiring)
		{
			
			screen.entities.add(fire(center,getOrientation()));
			setOrientation(getOrientation() +  1);
		}

	}
	
	public void setOrientation(int direction)
	{
		super.setOrientation(direction);
		super.weapon.projectile.setOrientation(direction);
	}
	
	public Projectile fire(Point source, int direction)
	{
		return weapon.fire(source,direction);
	}

}
