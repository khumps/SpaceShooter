
import java.awt.Rectangle;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

public class PlayerShip extends Ship {
	private static final int INITIAL_HEALTH = 1000;
	private static final int INITIAL_SHIELD = 100;
	private static final BufferedImage PLAYER_SHIP_IMAGE = Utils.loadImage("player-ship.png");
	private static final int FIRE_RATE = 3;
	public static final int PLAYER_VELOCITY = 10;
	private static final Bounds PLAYER_BOUNDS = new Bounds(new Rectangle(104, 64, 28, 16), new Rectangle(96, 60, 8, 24),
			new Rectangle(72, 36, 24, 72), new Rectangle(56, 32, 16, 80), new Rectangle(52, 28, 4, 88),
			new Rectangle(48, 24, 4, 96), new Rectangle(44, 16, 4, 112), new Rectangle(28, 12, 16, 120),
			new Rectangle(24, 16, 4, 112), new Rectangle(12, 28, 14, 88));

	public PlayerShip(PointDouble location, Screen screen) {
		super(PLAYER_SHIP_IMAGE, location, INITIAL_HEALTH, INITIAL_SHIELD, new GunTurret(Utils.loadImage("turret.png"),
				10, FIRE_RATE, new Laser(new PointDouble(1, 1), 10, null, screen), null, screen), PLAYER_BOUNDS,
				screen);
		hardPointMainTurret.ship = this;
	}

	public void moveTurret(PointDouble mouse) {
		hardPointMainTurret
				.setOrientation(Utils.getAngle(new PointDouble(screen.getWidth() / 2, screen.getHeight() / 2), mouse));
	}

	public void update(int tickNum) {
	}

	@Override
	public void collides(Entity e) {
	}

	@Override
	public boolean isEnemy(Ship s) {
		if (s instanceof PlayerShip)
			return false;
		return true;
	}

	public void addWingmen() {
		/*
		 * screen.entities.add(new Wingmen(getPosition(), 200, new
		 * LaserTurret(Utils.loadImage("player_bullet"), 0, health, null, null,
		 * screen), collisionArea, screen))
		 */
	}

	/*
	 * public void makeBox() { path.moveTo(170, 100); path.lineTo(191, 110);
	 * path.lineTo(191, 142); path.lineTo(171, 153); path.lineTo(80, 142);
	 * path.lineTo(31, 138); path.lineTo(x, y); }
	 */
}
