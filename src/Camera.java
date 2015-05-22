public class Camera
	{
		protected double x;
		protected double y;
		protected PointDouble camPosition;

		public Camera(PointDouble camPosition)
			{
				this.camPosition = camPosition;

			}

		public void tick(PointDouble p) {
			this.camPosition = p;
		}

		public PointDouble toScreenCoords(double x, double y) {
			return new PointDouble(x - this.x, y - this.y);
		}

		public PointDouble toScreenCoords(PointDouble p) {
			return toScreenCoords(p.x, p.y);
		}

		public boolean onScreen(PointDouble p) {
			PointDouble translated = toScreenCoords(p);
			return (translated.x > 0);
		}
	}
