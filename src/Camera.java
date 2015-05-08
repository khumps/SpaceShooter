import java.awt.Dimension;

public class Camera {
	private Dimension view;
	private PointDouble topLeft;

	;;
	private Screen screen;

	public Camera(Dimension dimension, Screen screen, PointDouble topLeft) {
		this.view = view;
		this.screen = screen;
		this.topLeft = topLeft;

	}

	public PointDouble drawPoint(PointDouble position) {
		return new PointDouble(position.x - topLeft.x, position.y - topLeft.y);
	}
}
