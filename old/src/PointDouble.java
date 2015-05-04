
public class PointDouble {
	protected final double x;
	protected final double y;

	public PointDouble(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Point toPoint()
	{
		return new Point((int)x,(int)y);
	}

	public String toString()
	{
		return "(" + x + "," + y + ")";	
	}
}
