package sprite;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

public class Sprite {
	public static final int
		STAND = 0,
		MOVE = 1,
		ATTACK = 2;

	private Animation currentAnimation;
	private Animation[] animations;
	private SpriteSheet spriteSheet;

	/**
	 * Constructs a sprite sheet with the specified image, frame width 
	 * and height, and number of frames.
	 * 
	 * @param image
	 * @param width
	 * @param height
	 * @param frames
	 */
	public Sprite(SpriteSheet spriteSheet) {
		this.spriteSheet = spriteSheet;
		animations = spriteSheet.getAnimations();
		playAnimation(STAND);
	}
	
	public static Sprite fromSpriteSheet(SpriteSheet sheet) {
		if (sheet == null) {
			return null;
		} else {
			return new Sprite(sheet);
		}
	}
	
//	public Sprite(String sheetId) {
//		this(Util.loadSpriteSheet(sheetId));
//	}

	/**
	 * Returns the current frame of the sprite.
	 * 
	 * @return
	 */
	public BufferedImage getCurrentFrame() {
		if (currentAnimation == null) {
			return null;
		} else {
			return spriteSheet.getFrame(currentAnimation.getCurrent());
		}
	}
	
	/**
	 * Updates the sprite.
	 */
	public void update() {
		if (currentAnimation != null) {
			currentAnimation.update();
			if (isDone() && !currentAnimation.isRepeating()) {
				playAnimation(STAND);
			}
		}
	}

	/**
	 * Plays the specified animation.
	 * @param anim
	 */
	public void playAnimation(Animation anim) {
		if (currentAnimation != anim) {
			if (currentAnimation != null) {
				currentAnimation.restart();
			}
			currentAnimation = anim;
			anim.restart();
		}
	}
	
	public void playAnimation(int anim) {
		if (currentAnimation == null || currentAnimation.isRepeating() || (!currentAnimation.isRepeating() && currentAnimation.isDone())) {
			if (-1 < anim && anim < animations.length) {
				playAnimation(animations[anim]);
			}
		}
	}
	
	/**
	 * Returns whether or not the current animation has completed.
	 * 
	 * @return
	 */
	public boolean isDone() {
		return currentAnimation.isDone();
	}
	
	public Dimension getSize() {
		return spriteSheet.getSize();
	}
	
	public Animation getCurrentAnimation() {
		return currentAnimation;
	}
}
