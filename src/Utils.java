import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Utils
	{
		public static BufferedImage loadImage(String str) {
			Utils u = new Utils();
			try
				{
					return ImageIO.read(u.getClass().getResourceAsStream(str));
				}
			catch (IOException e)
				{
					e.printStackTrace();
				}
			catch (NullPointerException o)
				{
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

		public static void resizeProjectile(Entity e) {

			AffineTransform tx = AffineTransform.getRotateInstance(e.getOrientation(),
					e.img.getWidth() / 2, e.img.getHeight() / 2);
			tx = AffineTransform.getScaleInstance(e.BULLET_SIZE / e.img.getWidth(), e.BULLET_SIZE
					- e.img.getWidth());
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			/*
			 * g.drawImage(op.filter(origImg, null), x, y, origImg.getWidth(),
			 * origImg.getHeight(), 0, 0, BULLET_SIZE, BULLET_SIZE, null);
			 */
			e.img = op.filter(e.getOrigImg(), null);
			e.setOrigImg(e.img);

			// RESIZE ONCE
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

		public static PointDouble translateToView(PointDouble p, PointDouble offset, Screen screen)
		{
			int x = (int) (p.x - offset.x);
			int y = (int) (p.y - offset.y);
			return new PointDouble(x, y);
		}

		public static double fuzzyAngle(PointDouble p1, PointDouble p2) {
			double temp = getAngle(p1, p2);
			return Math.random() - .5 + temp;
		}
	}
