import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Utils {
	
	public static BufferedImage loadImage(Object o, String str)
	{
		try {
			return ImageIO.read(o.getClass().getResourceAsStream(str));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
