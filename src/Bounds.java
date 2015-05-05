import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public class Bounds {
	protected Area area = new Area();
	protected Entity entity;

	public Bounds(Entity entity, Shape... shapes) {
		for (Shape s : shapes)
			area.add(new Area(s));
		this.entity = entity;
	}

	public boolean intersects(Bounds b) {
		Area a = (Area) area.clone();
		a.intersect(b.area);
		return !a.isEmpty();	 
	}
	
	public void move()
	{
	AffineTransform at = new AffineTransform();
	}
}