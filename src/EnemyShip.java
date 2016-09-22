
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class EnemyShip extends Ship {
	private int velocity = 3;
	private static final int INITIAL_HEALTH = 200;
	private static final int INITIAL_SHIELD = 10;
	private static final BufferedImage ENEMY_SHIP_IMAGE = Utils.loadImage("bomber.png");
	private final int score = 10;
	private int scoreModifier;

	public EnemyShip(PointDouble point, int scoreModifier, Screen screen) {
		super(ENEMY_SHIP_IMAGE, point, INITIAL_HEALTH, INITIAL_SHIELD,
				new GunTurret(Turret.TURRET_IMAGE, 10, 20, new Bullet(new PointDouble(1, 1), 0, null, screen), null,
						screen),
				new Bounds(new Rectangle(16, 16, 104, 28), new Rectangle(16, 100, 104, 28),
						new Rectangle(16, 44, 64, 56)),
				screen);
		hardPointMainTurret.ship = this;
		hardPointMainTurret.projectile.setDamage(10);
		this.scoreModifier = scoreModifier;
	}

	public void update(int tickNum) {
		super.update();
		PointDouble position = screen.player.getPosition();
		double orientation = Utils.getAngle(getPosition(), screen.player.getPosition());
		moveTurret(orientation);
		fire(tickNum);
		double dist = Utils.getDistance(getPosition(), screen.player.getPosition());
		if (tickNum % 10 == 0) {
			if (dist > 550) {
				setOrientation(orientation);
				velocity = 4;
			}
			/*
			 * else if (dist > 500) {
			 * setOrientation(Utils.fuzzyAngle(getPosition(),
			 * screen.player.getPosition())); velocity = 5; }
			 */
			else if (tickNum % 25 == 0)
				setOrientation(Math.random() * Math.PI * 2);
		}
		move(velocity);
	}

	public void collides(Entity e) {
		// TODO Auto-generated method stub

	}

	public void remove() {
		super.remove();
		screen.score += score * scoreModifier;
	}

	@Override
	public boolean isEnemy(Ship s) {
		if (s instanceof EnemyShip)
			return false;
		return true;
	}
}
