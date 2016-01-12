package khumps.game.weapons;

import khumps.game.Entity;
import khumps.game.Ship;

public class Weapon extends Entity {

	private Ship mount;
	private Projectile projectile;

	public Weapon(Ship mount, int hardPoint, Projectile projectile) {
		super(null, null, 0, null);
		this.mount = mount;
		this.projectile = projectile;
		mount.addWeapon(this, hardPoint);
	}
}
