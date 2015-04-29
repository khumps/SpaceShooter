import java.awt.image.BufferedImage;



public class Bomber extends Ship {
	private Bomb bomb;

	public Bomber(Point corner, BufferedImage img) {
		super(corner, img);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		shoot();
		int decision = (int)(Math.random() * 16);
		if(decision == 1 || decision == 2) //Move left
			super.move(-5, 0);
		if(decision == 3 || decision == 4) //Move right
			super.move(5, 0);
		if(decision == 5 || decision == 6) //Move up
			super.move(0, -5);
		if(decision == 7 || decision == 8) //Move down
			super.move(0, 5);
		if(decision == 9 || decision == 10) //Move right & up
			super.move(5, -5);
		if(decision == 3 || decision == 4) //Move right & down
			super.move(5, 5);
		if(decision == 3 || decision == 4) //Move left & up
			super.move(-5, -5);
		if(decision == 3 || decision == 4) //Move left & down
			super.move(-5, 5);
	}

}
