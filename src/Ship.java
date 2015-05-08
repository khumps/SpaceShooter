import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public abstract class Ship extends Entity {
	private int health;
	private final int maxHealth;
	protected Turret turret;
	private boolean isAlive = true;

	public Ship(BufferedImage img, PointDouble location, int health, Turret turret,
			Bounds b, Screen screen) {
		super(img, Math.toRadians((Math.random() * 360)), location, b, screen);
		this.turret = turret;
		this.health = health;
		maxHealth = health;
		turret.setPosition(super.getPosition());
		screen.entities.add(this);
	}

	public void update() {
		turret.setPosition(this.getPosition());
	}

	public void moveTurret(PointDouble mouse) {
		turret.setOrientation(Utils.getAngle(getPosition(), mouse));
	}

	public void moveTurret(double orientation) {
		turret.setOrientation(orientation);
	}

	public void draw(Graphics2D g, Point corner) {
		super.draw(g, corner);
		turret.draw(g, corner);
	}

	public void remove() {
		screen.entities.remove(turret);
		screen.entities.remove(this);
	}

	public void fire(int tickNum) {
		if (isAlive)
			turret.fire(tickNum);
	}

	public void takeDamage(int damage) {
		health -= damage;
		if (health <= 0) {
			isAlive = false;
			remove();
		}
		screen.repaint();
	}

	public abstract boolean isEnemy(Ship s);

	public int getHealth() {
		return health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	@Override
	public boolean doesCollide(Entity e) {
		{
			if (e.collisionArea != null)
				if (this.collisionArea.intersects(e.collisionArea)) {
					collides(e);
					e.collides(this);
					return true;
				}
			return false;
		}
	}

}
