import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public abstract class Entity {
	public static final double UP = Math.toRadians(-90);
	public static final double UP_RIGHT = Math.toRadians(-45);
	public static final double RIGHT = Math.toRadians(0);
	public static final double DOWN_RIGHT = Math.toRadians(45);
	public static final double DOWN = Math.toRadians(90);
	public static final double DOWN_LEFT = Math.toRadians(135);
	public static final double LEFT = Math.toRadians(-180);
	public static final double UP_LEFT = Math.toRadians(-135);
	private BufferedImage origImg;
	protected BufferedImage img;
	private double orientation;
	private Point position;
	private Point corner;
	private boolean hasMoved;
	protected Bounds collisionArea;
	public final int BULLET_SIZE = 35;
	protected Screen screen;

	public Entity(BufferedImage img, double orientation, Point location,
			Bounds b, Screen screen) {
		if (location == null)
			position = new Point(100, 10);
		this.position = location;
		this.screen = screen;
		this.img = img;
		this.origImg = img;
		this.orientation = orientation;
		collisionArea = b;
		collisionArea.setCenter(location);
	}

	public void move(double distance) {
		double cos = Math.cos(orientation);
		double sin = Math.sin(orientation);
		double dx = distance * cos;
		double dy = distance * sin;
		double x = position.x + dx + .5;
		double y = position.y + dy + .5;
		position = new Point(x, y);
		hasMoved = true;
		collisionArea.setCenter(position);

	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point location) {
		this.position = location;

	}

	public double getOrientation() {
		return orientation;
	}

	public abstract void update(int tickNum);

	public boolean hasMoved() {
		boolean old = hasMoved;
		if (hasMoved)
			hasMoved = false;
		else
			hasMoved = true;
		return old;
	}

	public void draw(Graphics2D g, Point corner) {
		int x = (int) (position.x - corner.x - img.getWidth() / 2);
		int y = (int) (position.y - corner.y - img.getHeight() / 2);
		AffineTransform tx = AffineTransform.getRotateInstance(orientation,
				img.getWidth() / 2, img.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_BILINEAR);
		g.drawImage(op.filter(origImg, null), x, y, null);

	}

	public void drawProjectile(Graphics2D g, Point corner) {
		int x = (int) (position.x - corner.x - img.getWidth() / 2);
		int y = (int) (position.y - corner.y - img.getHeight() / 2);
		
		AffineTransform tx = AffineTransform.getRotateInstance(orientation,
				img.getWidth() / 2, img.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_BILINEAR);
		/*
		 * g.drawImage(op.filter(origImg, null), x, y, origImg.getWidth(),
		 * origImg.getHeight(), 0, 0, BULLET_SIZE, BULLET_SIZE, null);
		 */
		g.drawImage(op.filter(origImg, null), position.x - (BULLET_SIZE / 2),
				position.y - (BULLET_SIZE / 2), BULLET_SIZE, BULLET_SIZE, null);

		// RESIZE ONCE
	}

	public abstract boolean doesCollide(Entity e);

	public abstract void collides(Entity e);

	public void setOrientation(double orientation) {
		this.orientation = orientation;
		collisionArea.setAngle(orientation);
	}

}
