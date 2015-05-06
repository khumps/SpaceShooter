import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Utils {
	public static BufferedImage loadImage(String str) {
		Utils u = new Utils();
		try {
			return ImageIO.read(u.getClass().getResourceAsStream(str));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException o) {
			System.out.println("IMAGE NOT FOUND");
		}
		return null;
	}

	public static double getAngle(Point p1, Point p2) {
		double xDiff = p2.x - p1.x;
		double yDiff = p2.y - p1.y;
		return (Math.atan2(yDiff, xDiff));
	}

	public static BufferedImage rotate(BufferedImage img, double radians) {
		AffineTransform tx = AffineTransform.getRotateInstance(radians,
				img.getWidth() / 2, Math.round(img.getHeight() / 2));
		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_BILINEAR);
		return op.filter(img, null);
	}
	
	public static Area createArea(Shape... shape)
	{
		Area a = new Area();
		for(Shape s: shape)
			a.add(new Area(s));
		return a;
	}
}
