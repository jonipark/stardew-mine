import GaFr.GFU;
import java.util.ArrayList;
import GaFr.GFStamp;

public class DroppedItem { 

	// Spawns a new DroppedItem of type type at tile coordinates x, y.

	int x, y; // World coordinates, NOT tile coordinates -- not bound by grid!
	String resource;
	String image_name;
	GFStamp image; // a ref to the item image

	public static final int DROPPED_ITEM_SIZE = 32; // sprites are smaller
	public static final int PICKUP_RADIUS = 0; // how far away from the player should pickup be "valid" -- QoL
	


	public static ArrayList<DroppedItem> floorItems = new ArrayList<DroppedItem>(); // list of items to draw... 

	// Given tile coordinates and a type, create a dropped item. 
	public DroppedItem(int nx, int ny, String nresource) {

		x = nx*Game.TILE_SIZE + GFU.randint(0, Game.TILE_SIZE);
		y = ny*Game.TILE_SIZE + GFU.randint(0, Game.TILE_SIZE);

		resource = nresource;
		image_name = nresource + "_ITEM";
		this.image = TextureEntry.get(image_name);
		floorItems.add(this);

	}

	public DroppedItem(int nx, int ny, String nresource, int variants) {

		x = nx*Game.TILE_SIZE + GFU.randint(0, Game.TILE_SIZE);
		y = ny*Game.TILE_SIZE + GFU.randint(0, Game.TILE_SIZE);

		resource = nresource;
		image_name = nresource + "_ITEM";
		int rand = GFU.randint(1,variants);
		if (rand == 1) {
			this.image = TextureEntry.get(image_name);
		} else {
			this.image = TextureEntry.get(image_name+"_"+rand);
		}
		floorItems.add(this);

	}

	// Draws all dropped items.
	public static void drawItems() {
		GFStamp s;
		DroppedItem cur;

		for (int i = 0; i < floorItems.size(); i++) {
			cur = floorItems.get(i);
			if (!cur.image_name.equals("NOTHING")) { // don't render NOTHING items
				s = cur.getImage();
				s.moveTo(cur.x - Game.cameraX, cur.y - Game.cameraY);
				s.stamp();
			}
		}
		
	}

	// Removes the item from the world. 
	public void delete() {
		floorItems.remove(this);
	}

	public void pickup() {
		Game.inventory.addItem(this.resource);
		this.delete();
	}


	// Attempts to have a player pick up items
	public static void tryPickup(Player cur_player) {
		DroppedItem cur;
		for (int i = 0; i < floorItems.size(); i++) {
			cur = floorItems.get(i);
			if (cur.isCollideBox(cur_player.x, cur_player.y, cur_player.x+Player.PLAYER_HITBOX_WIDTH, cur_player.y+Player.PLAYER_HITBOX_HEIGHT) ) {
				cur.pickup();
			}
		}
	}

	// taken shamelessly wholesale from Tile.java
	
	// Check for collision
	public boolean isCollideBox(int x1, int y1, int x2, int y2) {


		if ( ( (x+DROPPED_ITEM_SIZE+PICKUP_RADIUS) < x1 || // rect is to the right of the tile
						x2 < x-PICKUP_RADIUS || // rect is to the left of the tile
						(y+DROPPED_ITEM_SIZE+PICKUP_RADIUS) < y1 || // rect is below the tile 
						y2 < y-PICKUP_RADIUS)) { // rect is above the tile
			return false; // no collision detected
		}
		// collision detected
		return true;

	}

	public GFStamp getImage() {
		return this.image;
	}
}