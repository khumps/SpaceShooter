package khumps.utils;

public class PointDouble {
	public double x;
	public double y;

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

	public void moveTo(double x, double y) {
		setX(x);
		setY(y);
	}

	public void translate(double dx, double dy) {
		x += dx;
		y += dy;
	}

	public java.awt.Point toAWTPoint() {
		return new java.awt.Point((int) x, (int) y);
	}

	public PointDouble clone() {
		return new PointDouble(x, y);
	}
}
