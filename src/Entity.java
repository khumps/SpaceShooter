import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public abstract class Entity implements Moveable {
	protected Rectangle bounds;
	protected Point center;
	protected final BufferedImage img;
	protected int orientation = 0; // 1 - 8 N,NE,E,SE,S etc

	public Entity(Point corner, BufferedImage img) {
		bounds = new Rectangle();
		bounds.setBounds(corner.x, corner.y, 5, 5);
		center = new Point(bounds.getCenterX(), bounds.getCenterY());
		this.img = img;
	}

	public Entity(BufferedImage img) {
		this.img = img;
	}

	@Override
	public Point move(int distX, int distY) {
		bounds.setLocation(bounds.x + distX, bounds.y + distY);
		center = new Point(bounds.getCenterX(), bounds.getCenterY());
		return new Point(bounds.x, bounds.y);
	}

	protected abstract void tick(int tickNum);

	public boolean doesColide(Entity other) {
		if (bounds.intersects(other.bounds))
			return true;
		return false;
	}

	public static HashMap<String,String> loadSprites()
	{
		HashMap<String,String> sprites = new HashMap<String,String>();
		sprites.put("BACKGROUND", "resources//space.png");
		sprites.put("SHIP_PLAYER", "resources//starship.png");
		sprites.put("SHIP_BOMBER", "resources//bomber.png");
		sprites.put("WEAPON_TORPEDO", "resources//torpedo.svg");
		sprites.put("WEAPON_LASER", "resources//projectile2.svg");
		// Thanks to Ben Hetherington for bomber and starship png's
		return sprites;
	}
}
