import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.util.HashMap;

public abstract class Entity implements Moveable {
	protected Rectangle bounds;
	protected Point center;
	protected BufferedImage img;
	private final BufferedImage origImage;
	private int orientation = 1; // 1 - 8 N,NE,E,SE,S etc

	public Entity(Point corner, BufferedImage img) {
		origImage = img;
		bounds = new Rectangle();
		bounds.setBounds(corner.x, corner.y, 5, 5);
		center = new Point(bounds.getCenterX(), bounds.getCenterY());
		this.img = img;
	}

	public Entity(BufferedImage img) {
		this.img = img;
		origImage = img;
	}

	@Override
	public Point move(int distX, int distY) {
		Rectangle old = bounds;
		bounds.setLocation(bounds.x + distX, old.y + distY);
		center = new Point(bounds.getCenterX(), bounds.getCenterY());
		return new Point(bounds.x, bounds.y);
	}

	protected abstract void tick(int tickNum);

	public boolean doesColide(Entity other) {
		if (bounds.intersects(other.bounds))
			return true;
		return false;
	}

	/**
	 * 
	 * @param direction
	 *            Number of 45 degree rotations from North
	 */
	private void orient(int direction) {
		int rotations = direction - getOrientation();
		AffineTransform tx = new AffineTransform();
		tx.rotate(rotations * Math.toRadians(45), img.getWidth() / 2,
				img.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_BILINEAR);
		img = op.filter(img, null);
	}

	public static HashMap<String, String> loadSprites() {
		HashMap<String, String> sprites = new HashMap<String, String>();
		sprites.put("BACKGROUND", "resources//space.png");
		sprites.put("SHIP_PLAYER", "resources//starship.png");
		sprites.put("SHIP_BOMBER", "resources//bomber.png");
		sprites.put("WEAPON_TORPEDO", "resources//torpedo.png");
		sprites.put("TURRET", "resources//turret.png");
		sprites.put("WEAPON_LASER", "resources//projectile2.svg");
		// Thanks to Ben Hetherington for bomber and starship png's
		return sprites;
	}

	public void setOrientation(int direction) {
		orientation = (direction % 8) + 1;
		orient(orientation);
	}

	public int getOrientation() {
		return orientation;
	}

	public Entity close() {
		return this;
	}

}
