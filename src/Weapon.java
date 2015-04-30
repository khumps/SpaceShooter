public abstract class Weapon {
	protected Projectile projectile;
	protected Point source;

	public Weapon(Point source, Projectile projectile) {
		this.source = source;
		this.projectile = projectile;
	}
	
	public abstract Projectile fire(int direction);

}
