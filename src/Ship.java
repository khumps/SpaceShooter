import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;


public abstract class Ship extends Entity {
	private int health;
	protected Turret turret;
	private Screen screen;
	
	

	public Ship(BufferedImage img, Point location, int health, Turret turret,Rectangle colision, Screen screen) {
		super(img,Math.toRadians((Math.random() * 360)),location, colision);
		this.turret = turret;
		this.health = health;
		this.screen = screen;
		turret.setPosition(super.getPosition());
		screen.entities.add(this);
	}
	
	public void update()
	{
		turret.setPosition(this.getPosition());
	}
	
	public void moveTurret(Point mouse)
	{
		turret.setOrientation(Utils.getAngle(getPosition(),
				mouse));
	}
	
	public void draw(Graphics2D g, Point corner)
	{
		super.draw(g, corner);
		turret.draw(g, corner);
	}
	
	public void fire(int tickNum)
	{
		if(tickNum % turret.fireRate == 0)
		screen.entities.add(new Projectile(turret.projectile.img, getPosition(),turret.projectile.getOrientation(), turret.projectile.damage, Projectile.TORPEDO_VELOCITY));
	}

}
