package khumps.game;

import khumps.game.weapons.Hardpoint;
import khumps.game.weapons.Weapon;
import khumps.game.weapons.WeaponConfig.WeaponType;
import khumps.utils.Bounds;
import khumps.utils.PointDouble;
import sprite.Sprite;

public class Ship extends Entity {
	public Hardpoint[] weapons;

	public Ship(Sprite sprite, PointDouble position, double orientation, Bounds bounds, WeaponType type,
			int numWeapons) {
		super(sprite, position, orientation, bounds);
		weapons = new Hardpoint[numWeapons];

	}

	@Override
	public void collides(Entity e) {
		// TODO Auto-generated method stub

	}

	public void addWeapon(Weapon weapon, int num) {
		if (num >= 0 && num < weapons.length && weapons[num] == null) {
			weapons[num].setWeapon(weapon);
		}
	}

}
