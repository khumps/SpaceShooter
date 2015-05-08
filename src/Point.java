/**
 * A Point contains an X and a Y to make one spot on a Cartesian coordinate
 * system
 * 
 * @author Kevin Humphreys
 */
public final class Point {
	/**
	 * Data:
	 * 
	 * @param x
	 *            The X of the coordinate
	 * @param y
	 *            The Y of the coordinate
	 */
	public int x;
	public int y;

	/**
	 * 
	 * Constructs a new Point
	 * 
	 * @param x
	 *            The X of the coordinate
	 * @param y
	 *            The Y of the coordinate
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point(double x, double y) {
		this.x = (int) (x + .5);
		this.y = (int) (y + .5);
	}

	/**
	 * 
	 * @return A String representation of a Point
	 */
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	public void translate(double dx, double dy) {
		x = (int) (x + dx);
		y = (int) (y + dy);
	}

	public Point clone() {
		return new Point(x, y);
	}
}
