import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Effect {
	Particle[] particles;
	public static final int EFFECT_SIZE = 200;

	public Effect(Particle... particles) {
		this.particles = particles;
	}

	public void drawEffect(Graphics2D g, Point center) {
		System.out.println("called");
		for (Particle p : particles)
			p.drawEffect(g, center, EFFECT_SIZE);
	}
}
