import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public abstract class Ship extends Entity {
	private int health;
	private final int maxHealth;
	protected Turret hardPointMainTurret;
	protected Turret hardPoint2Turret;
	private boolean isAlive = true;
	protected final PointDouble hardPointMain;
	protected final PointDouble hardPoint2;

	public Ship(BufferedImage img, PointDouble location, int health,
			Turret turret, Bounds b, Screen screen) {
		super(img, Math.toRadians((Math.random() * 360)), location, b, screen);
		this.hardPointMainTurret = turret;
		this.health = health;
		maxHealth = health;
		hardPointMain = getPosition();
		hardPoint2 = hardPointMain.clone();
		hardPoint2.setX(hardPointMain.y + 20);
		turret.setPosition(hardPointMain);
		addTurret(turret,hardPoint2Turret);
		screen.entities.add(this);
	}

	public void update() {
		hardPointMainTurret.setPosition(this.getPosition());
	}

	public void moveTurret(PointDouble mouse) {
		hardPointMainTurret.setOrientation(Utils.getAngle(getPosition(), mouse));
	}

	public void moveTurret(double orientation) {
		hardPointMainTurret.setOrientation(orientation);
	}

	public void draw(Graphics2D g, PointDouble corner) {
		super.draw(g, corner);
		hardPointMainTurret.draw(g, corner);
	}

	public void remove() {
		screen.entities.remove(hardPointMainTurret);
		screen.entities.remove(this);
	}

	public void fire(int tickNum) {
		if (isAlive)
			hardPointMainTurret.fire(tickNum);
	}

	public void takeDamage(int damage) {
		health -= damage;
		if (health <= 0) {
			isAlive = false;
			remove();
		}
		screen.repaint();
	}

	public void addTurret(Turret turret, Turret hardPoint) {
		hardPoint = turret;
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
