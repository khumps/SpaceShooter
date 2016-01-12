package sprite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SpriteUtil {
	
	private static final Map<String, SpriteSheet> SPRITE_SHEETS = new HashMap<>();
	
	private static void preloadSpriteSheet(String id, SpriteSheet sprite) {
		SPRITE_SHEETS.put(id, sprite);
	}
	
	public static void preloadSpriteSheets() throws IOException {
		File spriteFolder = new File("resources/sprites");
		if (spriteFolder.isDirectory()) {
			File[] sprites = spriteFolder.listFiles();
			for (File sprite : sprites) {
				try {
					String name = sprite.getName();
					if (name.endsWith(".sprt")) {
						preloadSpriteSheet(name, SpriteIO.read(sprite));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static SpriteSheet loadSpriteSheet(String id) {
		if (SPRITE_SHEETS.containsKey(id)) {
			SpriteSheet sprite = SPRITE_SHEETS.get(id);
			if (sprite == null) {
				System.err.println("Null sprite: " + id);
			}
			return SPRITE_SHEETS.get(id);
		} else {
			System.err.println("Sprite not found: " + id);
			return null;
		}
	}
	
	public static BufferedImage generateImage(SpriteSheet sprite) {
		Dimension size = sprite.getSize();
		int rows = sprite.getRows(), cols = sprite.getCols(),
			width = cols * size.width, height = rows * size.height,
			frame;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				frame = row * cols + col;
				g.drawImage(sprite.getFrame(frame), col * size.width, row * size.height, null);
			}
		}
		return image;
	}
}	
