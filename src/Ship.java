import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;


public abstract class Ship extends Entity {
	private int health;
	private boolean isAlive;
	protected Weapon weapon;
	Screen screen;

	public Ship(Point corner, BufferedImage img, int health, Weapon weapon,Screen screen) {
		super(corner,img);
		this.health = health;
		isAlive = true;
		this.weapon = weapon;
		this.screen = screen;
	}
	
	/**
	 * 
	 * @param direction Number of 45 degree rotations from North
	 */
	public void orient(int direction)
	{
		int rotations = direction - orientation;
		AffineTransform tx = new AffineTransform();
		tx.rotate(rotations * Math.toRadians(45), super.img.getWidth() / 2,
				super.img.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_BILINEAR);
	}
	
	public void takeDamage(int damage)
	{
		this.health -= damage;
		if(health <= 0) isAlive = false;
	}
}
