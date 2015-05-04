import java.awt.image.BufferedImage;

public class EnemyShip extends Ship {
	private static final int INITIAL_HEALTH = 200;
	private static final BufferedImage ENEMY_SHIP_IMAGE = Utils
			.loadImage("bomber.png");

	public EnemyShip(Point point, Screen screen) {
		super(ENEMY_SHIP_IMAGE, point, INITIAL_HEALTH, new Turret(
				Turret.TURRET_IMAGE, 10, 20, Projectile.TORPEDO_PROJECTILE),
				screen);
	}

}
