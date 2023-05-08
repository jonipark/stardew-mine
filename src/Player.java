import GaFr.GFStamp;

public class Player {

	/*

	The basic Player. Doesn't do a lot right now, just rendering and collision. Todo:
		* Move with keyboard
		* Better graphics
		* Interaction with rocks

	*/

	int x;
	int y;
	String dir;
	String image_name;
	GFStamp image;
	Boolean has_sword;

	public static Player player = new Player(128, 128, "PLAYER_FORWARD_STILL");

	public static final int PLAYER_HITBOX_WIDTH = 28;
	public static final int PLAYER_HITBOX_HEIGHT = 32;

	public static final int CLICK_RADIUS = 64;

	public Player(int new_x, int new_y, String new_image_name) {
		x = new_x;
		y = new_y;
		image_name = new_image_name;
		image = TextureEntry.get(image_name);
		dir = "FORWARD";
		has_sword = false;
	}

	public GFStamp getImage() {
		return this.image;
	}

	public void setHasSword(boolean has_sword) {
		this.has_sword = has_sword;
	}

	public void updateImage(boolean isWalking) {
		String prefix = "PLAYER_";
		String walkingState;
	
		if (isWalking) {
			walkingState = (System.currentTimeMillis() / 300 % 2 == 0) ? "WALK1" : "WALK2";
		} else {
			walkingState = "STILL";
		}
	
		image_name = prefix + dir + "_" + walkingState;
		image = TextureEntry.get(image_name);
	}	

	public void updateImageWithSword(boolean isWalking) {
		String prefix = "SWORD_PLAYER_";
		String walkingState;
	
		if (isWalking) {
			walkingState = (System.currentTimeMillis() / 300 % 2 == 0) ? "WALK1" : "WALK2";
		} else {
			walkingState = "STILL";
		}
	
		image_name = prefix + dir + "_" + walkingState;
		image = TextureEntry.get(image_name);
	}

	// Sets the player's position, not respecting collision.
	public void setPos(int nx, int ny) {
		x = nx;
		y = ny;
	}

	// Moves the player to the desired location
	public void moveTo(int nx, int ny) {
		// Determine the direction based on the movement

		if (nx < x) {
			dir = "LEFT";
		} else if (nx > x) {
			dir = "RIGHT";
		} else if (ny < y) {
			dir = "BACKWARD";
		} else if (ny > y) {
			dir = "FORWARD";
		}

		boolean[] collisions = checkCollision(nx, ny);
		boolean didMove = false;
		if (!collisions[0] && x != nx) { // a movement will happen
			x = nx;
			didMove = true;
		}
		if (!collisions[1] && y != ny) {
			y = ny;
			didMove = true;
		}

		if (this.has_sword) {
			updateImageWithSword(didMove);
		} else {
			updateImage(didMove);
		}
		
		DroppedItem.tryPickup(this);
	}

	// Checks if the player would overlap with the tile at x, y
	// Hitbox size is currently defined as a 32x32 square -- edit isCollideBox to change
	public boolean[] checkCollision(int destx, int desty) {
		Tile cur;

		boolean xCollision = false;
		boolean yCollision = false;

		for (int i = 0; i < Tile.tiles.size(); i++) {
			cur = Tile.tiles.get(i);
			if (!cur.isPassable()) {
				if (cur.isCollideBox(destx + 4, y + 16, destx + PLAYER_HITBOX_WIDTH, y + PLAYER_HITBOX_HEIGHT)) {
					xCollision = true;
				}
				if (cur.isCollideBox(x + 4, desty + 16, x + PLAYER_HITBOX_WIDTH, desty + PLAYER_HITBOX_HEIGHT)) {
					yCollision = true;
				}
			}
		}

		return new boolean[]{xCollision, yCollision};
	}

	

	// Check whether the player is close enough to the click
	// and there's a feature at this position -- if so, hit it
	public static void clickFeature(int cx, int cy) {

		int px = player.x+16; // the x pos of the *center* of the player
		int py = player.y+16;
		// d=sqrt((x2 - x1)^2 + (y2 - y1)^2)
		int dist_to_player = (int)Math.sqrt((cx-px)*(cx-px) + (cy-py)*(cy-py));

		if (dist_to_player < CLICK_RADIUS) {
			Feature feature = Feature.getFeatureAt(cx, cy);
			if (feature != null) {
				feature.click();
			}
		}
	}

}