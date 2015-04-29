public abstract class Weapon {
	private Projectile projectile;
	public Weapon(Projectile projectile) {
		this.projectile = projectile;
	}
	
	public abstract void fire();

}
