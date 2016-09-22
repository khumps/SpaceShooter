
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
	private BufferedImage img;
	private double orientation;
	private PointDouble position;
	private Point corner;
	private boolean hasMoved;
	public Bounds collisionArea;
	public final int BULLET_SIZE = 10;
	protected Screen screen;

	public Entity(BufferedImage img, double orientation, PointDouble location,
			Bounds b, Screen screen) {
		if (location == null)
			position = new PointDouble(100, 10);
		this.position = location;
		this.screen = screen;
		this.setImg(img);
		this.setOrigImg(img);
		this.orientation = orientation;
		collisionArea = b;
		collisionArea.setCenter(location);
		collisionArea.setAngle(orientation);
	}

	public Point move(double distance) {
		double cos = Math.cos(orientation);
		double sin = Math.sin(orientation);
		double dx = distance * cos;
		double dy = distance * sin;
		double x = position.x + dx;
		double y = position.y + dy;
		position = new PointDouble(x, y);
		hasMoved = true;
		collisionArea.setCenter(position);
		return new Point(dx, dy);

	}

	public PointDouble getPosition() {
		return position;
	}

	public void setPosition(PointDouble location) {
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

	public void draw(Graphics2D g, PointDouble screenCorner, PointDouble offset) {
		int x = (int) (position.x  - offset.x - getImg().getWidth()
				/ 2 + screen.getWidth() / 2);
		int y = (int) (position.y - offset.y - getImg().getHeight()
				/ 2 + screen.getHeight() / 2);
/*		if(this instanceof Ship)
		System.out.println("Ship" + x + " " + y);*/
		AffineTransform tx = AffineTransform.getRotateInstance(orientation,
				getImg().getWidth() / 2, getImg().getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_BILINEAR);
		g.drawImage(op.filter(getOrigImg(), null), x, y, null);

	}

	public abstract boolean doesCollide(Entity e);

	public abstract void collides(Entity e);

	public void setOrientation(double orientation) {
		this.orientation = orientation;
		collisionArea.setAngle(orientation);
	}

	public BufferedImage getOrigImg() {
		return origImg;
	}

	public void setOrigImg(BufferedImage img) {
		origImg = img;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

}
