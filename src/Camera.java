public class Camera {
	protected double x;
	protected double y;
	protected PlayerShip player;

	public Camera(PlayerShip p) {
		this.player = p;

	}

	public PointDouble tick() {
		return player.getPosition();
	}

}
