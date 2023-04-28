import GaFr.GFStamp;
import java.lang.*;
import java.util.*;

public class Tile {

	// Tiles track their own position, for collision reasons. 

	public String image_name; // name of image e.g. "TILE_ROCK_VARIETY2", determines display
	String type; // type of image e.g. "ROCK_MINEABLE", determines behaviour
	boolean passable; // whether the player can walk through
	GFStamp image; // a ref to the actual image
	int x; // pos in world coordinates
	int y;
	public static ArrayList<Tile> tiles = new ArrayList<Tile>(); // A list of all tiles INCLUDING features. Used for collision. 


	public Tile(int new_x, int new_y, String new_type, String new_image_name, boolean is_passable) {
		this.setImage(new_image_name);
		type = new_type;
		passable = is_passable;
		x = new_x;
		y = new_y;
		//if (!Feature.features.contains(this)) {}
		tiles.add(this);
	}

	public GFStamp getImage() {
		return this.image;
	}

	// Sets image given an image name -- obvious error of imgname not defined is reported by TextureEntry
	public void setImage(String imgname) {
		this.image_name = imgname;
		this.image = TextureEntry.get(image_name);
	}
	
	public boolean isPassable() {
		return this.passable;
	}


	// Checks if the point at coordinates x,y will collide with this tile.
	// Untested!
	public boolean isCollide(int px, int py) {
		return (px > (x*Game.TILE_SIZE) && px < ((x+1)*Game.TILE_SIZE) && py > (y*Game.TILE_SIZE) && py < ((y+1)*Game.TILE_SIZE));
	}

/*	Check if this box overlaps with an area bounded by x1 < x < x2 and y1 < y < y2
		Reliant on an upper left pin. 
	*/
	public boolean isCollideBox(int x1, int y1, int x2, int y2) {

		//System.out.println("x1 is "+x1+", y1 is "+y1+", x2 is "+x2+", y2 is "+y2+", x is "+x*Game.TILE_SIZE+", y is "+y*Game.TILE_SIZE);
		// Remember that x and y are WORLD COORDS, and the params are SCREEN COORDS

		if ( ( (x+1)*Game.TILE_SIZE <= x1 || // rect is to the right of the tile
						x2 <= x*Game.TILE_SIZE || // rect is to the left of the tile
						(y+1)*Game.TILE_SIZE <= y1 || // rect is below the tile 
						y2 <= y*Game.TILE_SIZE)) { // rect is above the tile
			return false; // no collision detected
		}
		// collision detected
		return true;

	}
}