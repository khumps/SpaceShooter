import java.awt.image.BufferedImage;

public class PlayerShip extends Ship {
	private static final int INITIAL_HEALTH = 1000;
	private static final BufferedImage PLAYER_SHIP_IMAGE = Utils.loadImage("player-ship.png");


	public PlayerShip(Point location, Screen screen) {
		super(PLAYER_SHIP_IMAGE, location, INITIAL_HEALTH, new Turret(
				Utils.loadImage("turret.png"), 10, 20, Projectile.TORPEDO_PROJECTILE), screen);
	}

}
