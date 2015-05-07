import java.awt.Graphics2D;

public class Explosion extends Effect {

	public Explosion() {
		super(new Particle(Utils.loadImage("explosion.png")));
	}

	public void drawEffect(Graphics2D g, Point center) {
		System.out.println("fuck");
		for (int i = 10; i < 50; i += 10)
			particles[0].drawEffect(g, center, i);
	}


}
