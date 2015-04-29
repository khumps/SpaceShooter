import java.awt.Rectangle;
import java.util.HashMap;


public class SpriteLocations {
	public final int size = 200;
	public HashMap<String,String> sprites = new HashMap<String,String>();

	public SpriteLocations() {
		sprites.put("BACKGROUND", "resources//space.svg");
		sprites.put("SHIP_PLAYER", "resources//starship.svg");
		sprites.put("SHIP_BOMBER", "NEED ASSET");
		sprites.put("WEAPON_TORPEDO", "resources//torpedo.svg");
		sprites.put("WEAPON_LASER", "resources//projectile2.svg");
	}

}
