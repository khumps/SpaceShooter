import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Utils {
	public Utils()
	{
		
	}
	
	public static BufferedImage loadImage(String str)
	{
		Utils u = new Utils();
		try {
			return ImageIO.read(u.getClass().getResourceAsStream(str));
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (NullPointerException o){
			System.out.println("IMAGE NOT FOUND");
		}
		return null;
	}
	
	public static double getAngle(Point p1, Point p2) {
        double xDiff = p2.x - p1.x;
        double yDiff = p2.y - p1.y;
        return (double) (Math.atan2(yDiff, xDiff) * (180 / Math.PI));

}
}

