import java.awt.image.BufferedImage;

public class Player extends Ship {
	protected boolean isFiring;

	public Player(Point corner, BufferedImage img, int health, Weapon weapon) {
		super(corner, img, health, weapon);
	}

	@Override
	public void tick() {
		if(isFiring)
			weapon.fire(orientation);
	}

	@Override
	protected void tick(int tickNum) {
		// TODO Auto-generated method stub
		
	}

}
