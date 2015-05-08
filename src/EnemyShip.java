import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class EnemyShip extends Ship {
	private final int VELOCITY = 3;
	private static final int INITIAL_HEALTH = 200;
	private static final BufferedImage ENEMY_SHIP_IMAGE = Utils
			.loadImage("bomber.png");
	private final int score = 10;
	private int scoreModifier;

	public EnemyShip(PointDouble point, int scoreModifier, Screen screen) {
		super(ENEMY_SHIP_IMAGE, point, INITIAL_HEALTH, new LaserTurret(
				Turret.TURRET_IMAGE, 10, 20, new Bullet(new PointDouble(1, 1),
						0, null, screen), null, screen),
				new Bounds(new Rectangle(16, 16, 104, 28), new Rectangle(16,
						100, 104, 28), new Rectangle(16, 44, 64, 56)), screen);
		turret.ship = this;
		turret.projectile.setDamage(10);
		this.scoreModifier = scoreModifier;
	}

	public void update(int tickNum) {
		super.update();
		PointDouble position = screen.player.getPosition();
		double orientation = Utils.getAngle(getPosition(),
				screen.player.getPosition());
		moveTurret(orientation);
		if (!screen.getBounds().contains(getPosition().x, getPosition().y))
			setOrientation(getOrientation() * -1);
		fire(tickNum);
		if (tickNum % 20 == 0) {
			if (tickNum % 100 == 0)
				if (Math.sqrt(((getPosition().x - screen.player.getPosition().x) * (getPosition().x - screen.player
						.getPosition().x))
						+ ((getPosition().y - screen.player.getPosition().y) * (getPosition().y - screen.player
								.getPosition().y))) > 600)
					setOrientation(orientation);

			setOrientation(Math.random() * Math.PI * 2);
		}
		//move(VELOCITY);
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
