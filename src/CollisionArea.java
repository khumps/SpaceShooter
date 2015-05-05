import java.awt.Color;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.util.HashSet;


public class CollisionArea {
	HashSet<String> collisions = new HashSet<String>();
	BufferedImage img;

	public CollisionArea(BufferedImage img) {
		this.img = img;
	}

	public static Area getOutline(Color target, Entity e) {
	    // construct the GeneralPath
		BufferedImage bi = e.img;
	    GeneralPath gp = new GeneralPath();

	    gp.moveTo(e.getPosition().x, e.getPosition().y);
	    boolean cont = false;
	    int targetRGB = target.getRGB();
	    for (int xx=0; xx<bi.getWidth(); xx++) {
	        for (int yy=0; yy<bi.getHeight(); yy++) {
	            if (bi.getRGB(xx,yy)==targetRGB) {
	                if (cont) {
	                    gp.lineTo(xx,yy);
	                    gp.lineTo(xx,yy+1);
	                    gp.lineTo(xx+1,yy+1);
	                    gp.lineTo(xx+1,yy);
	                    gp.lineTo(xx,yy);
	                } else {
	                    gp.moveTo(xx,yy);
	                }
	                cont = true;
	            } else {
	                cont = false;
	            }
	        }
	        cont = false;
	    }
	    gp.closePath();

	    // construct the Area from the GP & return it
	    return new Area(gp);
	}
}
