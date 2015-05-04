import java.awt.image.BufferedImage;


public class PlayerShip extends Ship {
	private static final int DAMAGE = 10;
	private static final int INITIAL_HEALTH = 1000;
	private static BufferedImage img = Utils.loadImage("player-ship.png");
	private static final Projectile projectile = new Projectile(Utils.loadImage("torpedo.png"), new Point(1,1), 10, DAMAGE,Projectile.BULLET_VELOCITY);
	public PlayerShip(double orientation, Point location, Screen screen) {
		super(img, orientation, location, INITIAL_HEALTH, new Turret(Utils.loadImage("turret.png"), 10, 20, projectile,screen), screen);
	}

}
