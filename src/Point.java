/**
 * A Point contains an X and a Y to make one spot on a Cartesian coordinate system
 * @author Kevin Humphreys
 */
public final class Point {
	/**
	 * Data:
	 * @param x The X of the coordinate
	 * @param y The Y of the coordinate
	 */
	public final int x;
	public final int y;

	/**
	 * 
	 * Constructs a new Point
	 * @param x The X of the coordinate
	 * @param y The Y of the coordinate
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(double x, double y)
	{
		this.x = (int)x;
		this.y = (int)y;
	}
	/**
	 * 
	 * @return A String representation of a Point
	 */
	@Override
	public String toString()
	{
		return "(" + x + "," + y + ")";	
	}
	
	public Point translate(double dx, double dy)
	{
		return new Point((int)(x + dx), (int)(y + dy));
	}

}
