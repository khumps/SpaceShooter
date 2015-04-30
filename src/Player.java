import java.awt.image.BufferedImage;

public class Player extends Ship {
	protected boolean isFiring;

	public Player(Point corner, BufferedImage img, int health, Weapon weapon, Screen screen) {
		super(corner, img, health, weapon,screen);
	}


	@Override
	protected void tick(int tickNum) {
		System.out.println(isFiring);
		if(isFiring)
			screen.entities.add(weapon.fire(orientation));
		
	}


}
