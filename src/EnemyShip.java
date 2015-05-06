import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class EnemyShip extends Ship {
	private final int VELOCITY = 2;
	private static final int INITIAL_HEALTH = 200;
	private static final BufferedImage ENEMY_SHIP_IMAGE = Utils
			.loadImage("bomber.png");

	public EnemyShip(Point point, Screen screen) {
		super(ENEMY_SHIP_IMAGE, point, INITIAL_HEALTH, new Turret(
				Turret.TURRET_IMAGE, 10, 20, new Projectile(
						Utils.loadImage("torpedo.png"), new Point(1, 1), 10, 1,
						Projectile.TORPEDO_VELOCITY, null, screen), screen),
				new Bounds(new Rectangle(16, 16, 104, 28), new Rectangle(16,
						100, 104, 28), new Rectangle(16, 44, 64, 56)), screen);
	}

	public void update(int tickNum) {
		super.update();
		Point position = screen.player.getPosition();
		double orientation = Utils.getAngle(getPosition(),
				screen.player.getPosition());
		moveTurret(orientation);
		// fire(tickNum);
		if (tickNum % 20 == 0) {
			if (tickNum % 100 == 0)
				if (Math.sqrt(((getPosition().x - screen.player.getPosition().x) * (getPosition().x - screen.player
						.getPosition().x))
						+ ((getPosition().y - screen.player.getPosition().y) * (getPosition().y - screen.player
								.getPosition().y))) > 600)
					setOrientation(orientation);

				else {
					int decision = (int) (Math.random() * 16);
					if (decision == 1 || decision == 2) // Move left
						setOrientation(Entity.LEFT);
					if (decision == 3 || decision == 4) // Move right
						setOrientation(Entity.RIGHT);
					if (decision == 5 || decision == 6) // Move up
						setOrientation(Entity.UP);
					if (decision == 7 || decision == 8) // Move down
						setOrientation(Entity.DOWN);
					if (decision == 9 || decision == 10) // Move right & up
						setOrientation(Entity.UP_RIGHT);
					if (decision == 3 || decision == 4) // Move right & down
						setOrientation(Entity.DOWN_RIGHT);
					if (decision == 3 || decision == 4) // Move left & up
						setOrientation(Entity.UP_LEFT);
					if (decision == 3 || decision == 4) // Move left & down
						setOrientation(Entity.DOWN_LEFT);
				}
		}
		move(VELOCITY);
	}

	@Override
	public void collides(Entity e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isEnemy(Ship s) {
		if (s instanceof EnemyShip)
			return false;
		return true;
	}
}
