public abstract class Weapon {
	protected Projectile projectile;

	public Weapon(Projectile projectile) {
		this.projectile = projectile;
	}
	
	public abstract Projectile fire(int direction);

}
