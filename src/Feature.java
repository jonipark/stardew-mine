import GaFr.GFStamp;
import java.lang.*;
import java.util.*;

public class Feature extends Tile{

	// Features are drawn on top of tiles, and are like tiles but interactible in some way. 

	public static ArrayList<Feature> features = new ArrayList<Feature>(); // A list of all features. 

	public Feature(int new_x, int new_y, String new_type, String new_image_name, boolean is_passable) {
		super(new_x, new_y, new_type, new_image_name, is_passable);
		features.add(this);
	}

	// If passable is not given, features are impassable
	public Feature(int new_x, int new_y, String new_type, String new_image_name) {
		this(new_x, new_y, new_type, new_image_name, false);
	}

	// Gets rid of the feature. Should remove all references. 
	public void delete() {
		features.remove(this);
		Tile.tiles.remove(this);
	}


	public static Feature getFeatureAt(int x, int y) {
		x = x / Game.TILE_SIZE;
		y = y / Game.TILE_SIZE;
		Feature cur;
		for (int i = 0; i < features.size(); i++) {
			cur = features.get(i);
			if (cur.x == x && cur.y == y) {
				return cur;
			}
		}
		return null;
	}

	// Does nothing -- override this for feature specific clicks! 
	public void click() {
		System.out.println("No click method set");
	}


}