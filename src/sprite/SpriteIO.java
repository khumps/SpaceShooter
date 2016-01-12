package sprite;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Provides support for reading and writing ".sprite" files.
 * 
 * @author Benjamin Hetherington
 */
public final class SpriteIO {
	private SpriteIO() {}
	
	/**
	 * Reads the sprite sheet from the specified file.
	 * 
	 * @param file The specified file to read from
	 * @return The sprite sheet stored in the specified file
	 * @throws IOException
	 */
	public static SpriteSheet read(File file) throws IOException {
		FileInputStream fin = new FileInputStream(file);
		ObjectInputStream in = new ObjectInputStream(fin);
		SpriteSheet sheet = null;
		try {
			sheet = (SpriteSheet) in.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		in.close();
		return sheet;
	}
	
	/**
	 * Writes the specified sprite sheet to the specified file.
	 * 
	 * @param file The specified file
	 * @param sheet The specified sprite sheet
	 * @throws IOException
	 */
	public static void write(File file, SpriteSheet sheet) throws IOException {
		FileOutputStream fout = new FileOutputStream(file);
		ObjectOutputStream out = new ObjectOutputStream(fout);
		out.writeObject(sheet);
		out.close();
	}
}
