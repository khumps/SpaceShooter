package sprite;
 
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;


public class SpriteSheet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1092567735263797168L;
	private int cols, count, rows;
	private Animation[] animations;
	private Dimension size;
	private transient BufferedImage[] frames;
	
	public SpriteSheet(BufferedImage image, int width, int height, int frames, Animation[] animations) {
		size = new Dimension(width, height);
		cols = image.getWidth() / width;
		rows = image.getHeight() / height;
		count = frames;
		this.animations = animations;
		generateFromImage(image);
	}
	
	public SpriteSheet(BufferedImage image, int width, int height, int frame) {
		this(image, width, height, frame, null);
	}
	
	public int getCols() {
		return cols;
	}
	
	public int getRows() {
		return rows;
	}
	
	private int getCol(int frame) {
		return frame % cols;
	}

	private int getRow(int frame) {
		return frame / cols;
	}
	
	private BufferedImage isolateFrame(BufferedImage base, int frame) {
		if (base == null) {
			return null;
		}
		return base.getSubimage(getCol(frame) * size.width, getRow(frame) * size.height,
			size.width, size.height);
	}
	
	public BufferedImage getFrame(int frame) {
		if (frame == -1) {
			return null;
		}
		return frames[frame];
	}
	
	public Dimension getSize() {
		return size;
	}
	
	public Animation[] getAnimations() {
		Animation[] newAnims = new Animation[animations.length];
		for (int i = 0; i < newAnims.length; i++) {
			newAnims[i] = animations[i].clone();
		}
		return newAnims;
	}
	
	public void generateFromImage(BufferedImage image) {
		frames = new BufferedImage[count];
		for (int i = 0; i < count; i++) {
			this.frames[i] = isolateFrame(image, i);
		}
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		out.writeInt(1);
		out.writeInt(2);
		ImageIO.write(SpriteUtil.generateImage(this), "png", out);
		out.flush();
	}
	
	private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
		in.defaultReadObject();
		in.readInt();
		in.readInt();
		BufferedImage base = ImageIO.read(in);
		if (base == null) {
			System.err.println("Error: Image not found: " + this);
		}
		generateFromImage(base);
	}

	public int getFrameCount() {
		return frames.length;
	}
}
