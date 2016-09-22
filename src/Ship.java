

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public abstract class Ship extends Entity {
	private int health;
	private int shield;
	private final int maxHealth;
	private int maxShield;
	private boolean takenDamage = false;
	private int maxRegenTime = 100;
	private int regenTime = maxRegenTime;
	protected Turret hardPointMainTurret;
	protected Turret hardPoint2Turret;
	private boolean isAlive = true;
	public final PointDouble hardPointMain;
	public final PointDouble hardPoint2;

	public Ship(BufferedImage img, PointDouble location, int health, int shield, Turret turret, Bounds b,
			Screen screen) {
		super(img, Math.toRadians((Math.random() * 360)), location, b, screen);
		this.hardPointMainTurret = turret;
		this.health = health;
		maxHealth = health;
		maxShield = shield;
		this.shield = maxShield;
		hardPointMain = getPosition();
		hardPoint2 = hardPointMain.clone();
		hardPoint2.setX(hardPointMain.y + 20);
		turret.setPosition(hardPointMain);
		addTurret(turret, hardPoint2Turret);
		screen.getEntities().add(this);
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void update() {
		regen();
		hardPointMainTurret.setPosition(this.getPosition());
	}

	public void moveTurret(double orientation) {
		hardPointMainTurret.setOrientation(orientation);
	}

	public void regen() {
		if (regenTime > 0)
			regenTime--;
		else {
			if (shield < maxShield)
				shield++;
		}
	}

	public void draw(Graphics2D g, PointDouble corner, PointDouble offset) {
		super.draw(g, corner, offset);
		hardPointMainTurret.draw(g, corner, offset);
	}

	public void remove() {
		screen.getEntities().remove(hardPointMainTurret);
		screen.getEntities().remove(this);
	}

	public void fire(int tickNum) {
		if (isAlive)
			hardPointMainTurret.fire(tickNum);
	}

	public void takeDamage(int damage) {
		if (damage > 0) {
			takenDamage = true;
			regenTime = maxRegenTime;
			if (shield >= damage)
				shield -= damage;
			else {
				int temp = damage - shield;
				shield = 0;
				health -= temp;
			}
			if (health <= 0) {
				isAlive = false;
				remove();
			}
			screen.repaint();
		}
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

	public int getShields() {
		return shield;
	}

	public int getMaxShields() {
		return maxShield;
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
