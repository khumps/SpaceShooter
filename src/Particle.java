
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Particle extends Entity{

	public Particle(BufferedImage img, Screen screen) {
		super(img, 0, null, null, screen);
	}

	public void drawEffect(Graphics2D g, Point center, int size) {
		g.drawImage(getImg(), center.x - size / 2, center.y - size / 2, size, size,
				null);
		System.out.println("PARTICLE");
	}

	@Override
	public void update(int tickNum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean doesCollide(Entity e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void collides(Entity e) {
		// TODO Auto-generated method stub
		
	}
}
