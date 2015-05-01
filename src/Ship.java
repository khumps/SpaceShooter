import java.awt.image.BufferedImage;

public abstract class Ship extends Entity {
	private int health;
	private boolean isAlive;
	protected Weapon weapon;
	protected Screen screen;

	public Ship(Point corner, BufferedImage img, int health, Weapon weapon,
			Screen screen) {
		super(corner, img);
		this.health = health;
		isAlive = true;
		this.weapon = weapon;
		this.screen = screen;
	}


	public void takeDamage(int damage) {
		this.health -= damage;
		if (health <= 0)
			isAlive = false;
	}
}
