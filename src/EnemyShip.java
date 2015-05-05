import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class EnemyShip extends Ship {
	private final int VELOCITY = 6;
	private static final int INITIAL_HEALTH = 200;
	private static final BufferedImage ENEMY_SHIP_IMAGE = Utils
			.loadImage("bomber.png");

	public EnemyShip(Point point, Screen screen) {
		super(ENEMY_SHIP_IMAGE, point, INITIAL_HEALTH, new Turret(
				Turret.TURRET_IMAGE, 10, 20, Projectile.TORPEDO_PROJECTILE),
				new Rectangle(8, 16, 112, 112), screen);
	}

	public void update(int tickNum) {
		fire(tickNum);
		if (tickNum % 10 == 0) {
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
		move(VELOCITY);
	}
}
