import java.awt.image.BufferedImage;

public class Player extends Ship {
	protected boolean isFiring;

	public Player(Point corner, BufferedImage img, int health, Weapon weapon, Screen screen) {
		super(corner, img, health, weapon,screen);
	}

	@Override
	public void tick() {
		if(isFiring)
			screen.entities.add(weapon.fire(orientation));
	}

	@Override
	protected void tick(int tickNum) {
		// TODO Auto-generated method stub
		
	}

}
