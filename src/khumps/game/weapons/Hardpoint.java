package khumps.game.weapons;

import khumps.utils.PointDouble;

public class Hardpoint {

	private Weapon weapon;
	private PointDouble position;
	private boolean hasWeapon;

	public Hardpoint(PointDouble position, Weapon weapon) {
		this.position = position;
		this.weapon = weapon;
		if (weapon != null)
			hasWeapon = true;
	}

	public Hardpoint(PointDouble position) {
		this(position, null);

	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
		hasWeapon = weapon != null;
		if (hasWeapon) {
			weapon.position = position;
		}
	}
}
