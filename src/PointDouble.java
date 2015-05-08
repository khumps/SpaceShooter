public class PointDouble {
	protected double x;
	protected double y;

	public PointDouble(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	public Point toPoint() {
		return new Point(x, y);
	}

	public int getXInt() {
		return (int) x;
	}

	public int getYInt() {
		return (int) y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public void moveTo(double x, double y)
	{
		setX(x);
		setY(y);
	}
}
