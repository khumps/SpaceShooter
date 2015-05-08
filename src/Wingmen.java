import java.awt.image.BufferedImage;

public class Wingmen extends Ship {

	public Wingmen(BufferedImage img, PointDouble location, int health,
			Turret turret, Bounds b, Screen screen) {
		super(img, location, health, turret, b, screen);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isEnemy(Ship s) {
		if (s instanceof PlayerShip)
			return false;
		return true;
	}

	@Override
	public void update(int tickNum) {
		if (tickNum % 20 == 0) {
			if (tickNum % 100 == 0) {
				PointDouble position = screen.player.getPosition();
				double orientation = Utils.getAngle(getPosition(),
						screen.player.getPosition());
				if (Math.sqrt(((getPosition().x - screen.player.getPosition().x) * (getPosition().x - screen.player
						.getPosition().x))
						+ ((getPosition().y - screen.player.getPosition().y) * (getPosition().y - screen.player
								.getPosition().y))) > 300)
					setOrientation(orientation);
			}
			turret.setOrientation(Utils.getAngle(getPosition(), nearestEnemy()
					.getPosition()));
			fire(tickNum);
		}

	}

	@Override
	public void collides(Entity e) {
		// TODO Auto-generated method stub

	}

	public EnemyShip nearestEnemy() {
		for (Entity e : screen.entities) {
			if (e instanceof EnemyShip)
				return (EnemyShip) e;
		}
		return null;
	}

}
