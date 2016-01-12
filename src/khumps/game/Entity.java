package khumps.game;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import khumps.utils.Bounds;
import khumps.utils.PointDouble;
import khumps.utils.Utils;
import sprite.Sprite;

public abstract class Entity {
	protected Sprite sprite;
	public double orientation;
	public PointDouble position;
	public Bounds bounds;

	public Entity(Sprite sprite, PointDouble position, double orientation, Bounds bounds) {
		this.sprite = sprite;
		this.position = position;
		this.orientation = orientation;
		if (bounds == null)
			bounds = new Bounds(new Rectangle());
		this.bounds = bounds;
	}

	public void move(double theta, double distance) {
		PointDouble polar = Utils.toCartesian(theta, distance);
		position.translate(polar.x, polar.y);
		bounds.setCenter(position);
	}

	public BufferedImage getFrame() {
		return sprite.getCurrentFrame();
	}

	public final void doesCollide(Entity e) {
		if (bounds.intersects(e.bounds)) {
			collides(e);
			e.collides(this);
		}
	}

	public abstract void collides(Entity e);

}
