import java.awt.image.BufferedImage;

public class LaserTurret extends Turret
	{
		private int numUpgrades = 1;

		public LaserTurret(BufferedImage img, double orientation, int fireRate,
				Projectile projectile, Ship ship, Screen screen)
			{
				super(img, orientation, fireRate, projectile, ship, screen);
			}

		public void setNumUpgrades(int amt) {
			numUpgrades = amt;
		}

		public void fire(int tickNum) {
			if (tickNum % fireRate == 0)
				{
					for (int i = 1; i <= numUpgrades; i++)
						{
							if (numUpgrades % 2 == 1 && i == numUpgrades)
								screen.entities.add(addProjectile(getOrientation()));
							else
								{
									if (i % 2 == 0)
										screen.entities.add(addProjectile(getOrientation()
												+ Math.toRadians(i * .5)));
									else
										screen.entities.add(addProjectile(getOrientation()
												+ Math.toRadians(i * -.5)));
								}

						}
				}
		}

		public Projectile addProjectile(double direction) {
			return new Laser(getPosition(), direction, ship, screen);
		}
	}
