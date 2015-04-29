import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Entity implements Moveable {

	protected final Rectangle bounds;
	protected Point center;
	protected final BufferedImage img;
	protected int orientation = 0; // 1 - 8 N,NE,E,SE,S etc

	public Entity(Point corner, BufferedImage img) {
		bounds = new Rectangle();
		bounds.setBounds(corner.x, corner.y, img.getWidth(), img.getHeight());
		center = new Point(bounds.getCenterX(), bounds.getCenterY());
		this.img = img;
	}

	@Override
	public Point move(int distX, int distY) {
		bounds.setLocation(bounds.x + distX, bounds.y + distY);
		center = new Point(bounds.getCenterX(), bounds.getCenterY());
		return new Point(bounds.x, bounds.y);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	protected abstract void tick(int tickNum);

	public static boolean doesColide(Entity e1, Entity e2) {
		if (e1.bounds.contains(e2.bounds))
			return true;
		return false;
	}

}
