import java.awt.Rectangle;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

public class PlayerShip extends Ship {
	private static final int INITIAL_HEALTH = 1000;
	private static final BufferedImage PLAYER_SHIP_IMAGE = Utils.loadImage("player-ship.png");
	//private final Rectangle PLAYER_COLISION; 
	public static final int PLAYER_VELOCITY = 10;
	protected GeneralPath path = new GeneralPath();


	public PlayerShip(Point location, Screen screen) {
		super(PLAYER_SHIP_IMAGE, location, INITIAL_HEALTH, new Turret(
				Utils.loadImage("turret.png"), 10, 10, Projectile.TORPEDO_PROJECTILE), new Rectangle(12,12,120,120), screen);
	}

	public void update(int tickNum)
	{
	}
	
/*	public void makeBox()
	{
		path.moveTo(170, 100);
		path.lineTo(191, 110);
		path.lineTo(191, 142);
		path.lineTo(171, 153);
		path.lineTo(80, 142);
		path.lineTo(31, 138);
		path.lineTo(x, y);
	}*/
}
