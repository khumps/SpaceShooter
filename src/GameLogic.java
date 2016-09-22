
import java.util.ArrayList;

public class GameLogic {

	private Screen screen;
	private ArrayList<Entity> entities;

	public GameLogic(Screen screen) {
		this.screen = screen;
		entities = screen.getEntities();
	}

	public void tick(int tickNum) {
		// System.out.println(entities.size());
		addEnemies(tickNum);
		checkCollision();
		purgeEntities(tickNum);
	}

	public void checkCollision() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e1 = entities.get(i);
			if (e1 instanceof Ship)
				for (int j = 1; j < entities.size(); j++) {
					e1.doesCollide(entities.get(j));
				}
		}
	}

	public void purgeEntities(int tickNum) {
		if (tickNum % 50 == 0) {
			for (int i = 0; i < screen.getEntities().size(); i++) {
				Entity e = screen.getEntities().get(i);
				double dist = Utils.getDistance(e.getPosition(), screen.player.getPosition());
				if (!(e instanceof Ship))
					if (dist > 1000)
						screen.getEntities().remove(i);
				if (e instanceof EnemyShip)
					if (dist > 1500) {
						screen.getEntities().remove(i);
						// System.out.println("REMOVED ENTITY");
					}
			}
		}
	}

	public void addEnemies(int tickNum) {
		if (tickNum % 20 == 0 && screen.getNumShips() < 10) {
			Ship s = new EnemyShip(screen.pointOnScreen(screen.player.getPosition()), 1, screen);
			entities.add(s);
			System.out.println(s.getPosition() + "   " + screen.player.getPosition());
		}
	}
}
