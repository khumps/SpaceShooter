import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public abstract class Entity {
	private final BufferedImage origImg;
	protected BufferedImage img;
	private double orientation;
	private Point position;
	private boolean hasMoved;

	public Entity(BufferedImage img, double orientation, Point location) {
		if (location == null)
			this.position = new Point(300, 300);
		else
			this.position = location;

		this.img = img;
		this.origImg = img;
		this.orientation = orientation;
	}
	
	public void move(double distance)
	{
		double cos = Math.cos(orientation);
		double sin = Math.sin(orientation);
		double dx = distance * cos;
		double dy = distance * sin;
		System.out.println(new Point(dx,dy));
		double x = Math.round(position.x + dx);
		double y = Math.round(position.y + dy);
		position = new Point(x,y);
		hasMoved = true;
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
	
	public abstract void update();
	
	public boolean hasMoved()
	{
		boolean old = hasMoved;
		if(hasMoved)
			hasMoved = false;
		else hasMoved = true;
		return old;
	}
	public void draw(Graphics2D g, Point corner)
	{
		int x = (int)Math.round(position.x - corner.x - img.getWidth() / 2);
		int y = (int)Math.round(position.y - corner.y - img.getHeight() / 2);
		AffineTransform tx = AffineTransform.getRotateInstance(orientation, img.getWidth() / 2, img.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_BILINEAR);
		g.drawImage(op.filter(origImg, null), x, y, null);
	}

	public void setOrientation(double orientation) {
		this.orientation = orientation;
	}

}
