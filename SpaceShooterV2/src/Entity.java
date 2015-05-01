import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public abstract class Entity {
	private final BufferedImage origImg;
	protected BufferedImage img;
	private double orientation;
	private Point location;

	public Entity(BufferedImage img, double orientation, Point location) {
		if (location == null)
			this.location = new Point(300, 300);
		else
			this.location = location;

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
		double x = location.x + dx;
		double y = location.y + dy;
		location = new Point(x,y);
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}


	public double getOrientation() {
		return orientation;
	}
	
	public void draw(Graphics2D g, Point corner)
	{
		int x = (int)Math.round(location.x - corner.x - img.getWidth() / 2);
		int y = (int)Math.round(location.y - corner.y - img.getHeight() / 2);
		AffineTransform tx = AffineTransform.getRotateInstance(orientation, img.getWidth() / 2, img.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_BILINEAR);
		g.drawImage(op.filter(img, null), x, y, null);
	}

	public void setOrientation(double increase) {
		orientation += increase;
		System.out.println(orientation);
	}

}
