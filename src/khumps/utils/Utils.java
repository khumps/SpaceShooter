package khumps.utils;

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

	public static double getAngle(PointDouble pointDouble, PointDouble pointDouble2) {
		double xDiff = pointDouble2.x - pointDouble.x;
		double yDiff = pointDouble2.y - pointDouble.y;
		return (Math.atan2(yDiff, xDiff));
	}

	public static BufferedImage rotate(BufferedImage img, double radians) {
		AffineTransform tx = AffineTransform.getRotateInstance(radians, img.getWidth() / 2,
				Math.round(img.getHeight() / 2));
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		return op.filter(img, null);
	}

	public static Area createArea(Shape... shape) {
		Area a = new Area();
		for (Shape s : shape)
			a.add(new Area(s));
		return a;
	}

	public static double getDistance(PointDouble p1, PointDouble p2) {
		return Math.sqrt(((p1.x - p2.x) * (p1.x - p2.x)) + (p1.y - p2.y) * (p1.y - p2.y));
	}

	public static PointDouble translateToView(PointDouble p, PointDouble offset) {
		int x = (int) (p.x - offset.x);
		int y = (int) (p.y - offset.y);
		return new PointDouble(x, y);
	}

	public static double fuzzyAngle(PointDouble p1, PointDouble p2) {
		double temp = getAngle(p1, p2);
		return Math.random() - .5 + temp;
	}

	public static PointDouble toCartesian(double theta, double distance) {
		double cos = Math.cos(theta);
		double sin = Math.sin(theta);
		return new PointDouble(distance * cos, distance * sin);
	}
}
