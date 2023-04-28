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
	int dir; // 0 = facing down (toward screen), other directions not yet determined
	String image_name;
	GFStamp image;

	public static Player player = new Player(128, 128, "PLAYER_FORWARD_STILL");

	public static final int PLAYER_HITBOX_WIDTH = 28;
	public static final int PLAYER_HITBOX_HEIGHT = 32;

	public Player(int new_x, int new_y, String new_image_name) {
		x = new_x;
		y = new_y;
		image_name = new_image_name;
		image = TextureEntry.get(image_name);
		dir = 0;
	}

	public GFStamp getImage() {
		return this.image;
	}

	public void updateImage(int dir, boolean isWalking) {
		String prefix = "PLAYER_";
		String direction;
		String walkingState;
	
		switch (dir) {
			case 0:
				direction = "FORWARD_";
				break;
			case 1:
				direction = "BACKWARD_";
				break;
			case 2:
				direction = "RIGHT_";
				break;
			case 3:
				direction = "LEFT_";
				break;
			default:
				direction = "FORWARD_";
		}
	
		if (isWalking) {
			walkingState = (System.currentTimeMillis() / 300 % 2 == 0) ? "WALK1" : "WALK2";
		} else {
			walkingState = "STILL";
		}
	
		image_name = prefix + direction + walkingState;
		image = TextureEntry.get(image_name);
	}	

	// Moves the player to the desired location
	public void moveTo(int nx, int ny) {
		// Determine the direction based on the movement
		if (nx == x && ny == y) {
			updateImage(dir, false);
		} else {
			if (nx < x) {
				dir = 3;
			} else if (nx > x) {
				dir = 2;
			} else if (ny < y) {
				dir = 1;
			} else {
				dir = 0;
			}
			updateImage(dir, true);
		}
		
		DroppedItem.tryPickup(this);
		if (!this.checkCollision(nx, ny)) { // only allow moving if there is NOT a collision
			x = nx;
			y = ny;
			DroppedItem.tryPickup(this);
		}
	}

	// Checks if the player would overlap with the tile at x, y
	// Hitbox size is currently defined as a 32x32 square -- edit isCollideBox to change
	public boolean checkCollision(int destx, int desty) {
		Tile cur;

		for (int i = 0; i < Tile.tiles.size(); i++) {
			cur = Tile.tiles.get(i);
			if (cur.isCollideBox(destx+4, desty+16, destx+PLAYER_HITBOX_WIDTH, desty+PLAYER_HITBOX_HEIGHT) && !cur.isPassable() ) {
				return true;
			} 
		}
		return false;

	}

}