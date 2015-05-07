import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Particle {
	protected BufferedImage img;

	public Particle(BufferedImage img) {
		this.img = img;
	}

	public void drawEffect(Graphics2D g, Point center, int size) {
		g.drawImage(img, center.x - size / 2, center.y - size / 2, size, size,
				null);
	}
}
