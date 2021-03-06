
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

/**
 * 
 * @author Benjamin Hetherington
 *
 */
public class Bounds {
	private PointDouble center;
	private Area area;
	private double angle;

	public Bounds(Shape... shapes) {
		area = new Area();
		for (Shape shape : shapes) {
			area.add(new Area(shape));
		}
		Rectangle r = area.getBounds();
		center = new PointDouble(r.getCenterX(), r.getCenterY());
	}

	public Bounds(Bounds b) {
		this.center = b.center;
		this.area = b.area;
		this.angle = b.angle;
	}

	public PointDouble getCenter() {
		return center;
	}

	public double getX() {
		return center.x;
	}

	public double getY() {
		return center.y;
	}

	public boolean intersects(Bounds b) {
		Area intersection = (Area) area.clone();
		intersection.intersect(b.area);
		return !intersection.isEmpty();
	}

	public void setCenter(PointDouble location) {
		double dx = location.x - center.x, dy = location.y - center.y;
		center.translate(dx, dy);
		area.transform(AffineTransform.getTranslateInstance(dx, dy));
	}

	public void setAngle(double angle) {
		area.transform(AffineTransform.getRotateInstance(angle - this.angle,
				center.x, center.y));
		this.angle = angle;
	}

	public Area getArea() {
		return area;
	}
}
