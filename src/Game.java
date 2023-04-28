import GaFr.GFGame;
import GaFr.GFStamp;
import GaFr.GFKey;
import GaFr.Gfx;
import GaFr.GFKeyboard;
import GaFr.GFU;
import GaFr.GFSound;

import java.util.*;

import java.io.*;
import java.lang.Integer;
import java.lang.Math;

public class Game extends GFGame
{
	public static Inventory inventory;

	public static final int TILE_SIZE = 32;

	public static final int MAP_WIDTH = 40; // The maximum size a single "level" can be. Right now, it's ~ the size of the screen.
	public static final int MAP_HEIGHT = 40;

	public static Tile[][] map = new Tile[MAP_WIDTH][MAP_HEIGHT]; // The current loaded map
	public static String[][] floormap = new String[MAP_WIDTH][MAP_HEIGHT]; // The floor of the map

 	GFSound walking = new GFSound("assets/sounds/walking.wav");
	GFSound backgroundMusic = new GFSound("assets/sounds/background.mp3");
	public static final boolean scrolling = true;
	HashSet<Integer> pressedKeys = new HashSet<>();

	/*
		Note on world coordinates vs screen coordinates:
		* If scrolling is false, world coords == screen coords
		* If scrolling is true, the world coords can be obtained by adding cameraX/Y to the screen coords
			(useful when trying to click on an in-world object)
			and the screen coords can be obtained by subtracting the camera coordinates from world coords
			(I'm sure there's a use for this.)
			Usually, you'll be working in world coords, unless you're working on a) graphics or b) getting click
			input from the user.
	*/

	public static int cameraX = 0;
	public static int cameraY = 0;

	{
		Gfx.clearColor(Gfx.Color.BLACK);
		backgroundMusic.play();

		TextureEntry.init();
		readMap("assets/map/map.txt"); // initializes tileDict
		initFloor();
		inventory = new Inventory(this.WIDTH / 2, this.HEIGHT - 60);
	}

	private static String getWaterTileType(int x, int y, String[][] mapArray) {
		int mapHeight = mapArray.length;
		int mapWidth = mapArray[0].length;

		boolean isLeftWater = x > 0 && (mapArray[y][x - 1].equals("2") || mapArray[y][x - 1].equals("3"));
		boolean isRightWater = x < mapWidth - 1 && (mapArray[y][x + 1].equals("2") || mapArray[y][x + 1].equals("3"));
		boolean isTopWater = y > 0 && (mapArray[y - 1][x].equals("2") || mapArray[y - 1][x].equals("3"));
		boolean isBottomWater = y < mapHeight - 1 && (mapArray[y + 1][x].equals("2") || mapArray[y + 1][x].equals("3"));

		if (!isLeftWater && !isTopWater) {
			return "SHALLOW_UL_CORNER";
		} else if (!isRightWater && !isTopWater) {
			return "SHALLOW_UR_CORNER";
		} else if (!isLeftWater && !isBottomWater) {
			return "SHALLOW_BL_CORNER";
		} else if (!isRightWater && !isBottomWater) {
			return "SHALLOW_BR_CORNER";
		} else {
			return "WATER";
		}
	}

	private static String getDeepWaterTileType(int x, int y, String[][] mapArray) {
		int mapHeight = mapArray.length;
		int mapWidth = mapArray[0].length;

		boolean isLeftWater = x > 0 && mapArray[y][x - 1].equals("3");
		boolean isRightWater = x < mapWidth - 1 && mapArray[y][x + 1].equals("3");
		boolean isTopWater = y > 0 && mapArray[y - 1][x].equals("3");
		boolean isBottomWater = y < mapHeight - 1 && mapArray[y + 1][x].equals("3");

		if (!isLeftWater && !isTopWater) {
			return "DEEP_UL_CORNER";
		} else if (!isRightWater && !isTopWater) {
			return "DEEP_UR_CORNER";
		} else if (!isLeftWater && !isBottomWater) {
			return "DEEP_BL_CORNER";
		} else if (!isRightWater && !isBottomWater) {
			return "DEEP_BR_CORNER";
		} else {
			return "DEEP_WATER";
		}
	}

	private static String getWallTileType(int x, int y, String[][] mapArray) {
		int mapHeight = mapArray.length;
		int mapWidth = mapArray[0].length;

		boolean isLeftWall = x > 0 && mapArray[y][x - 1].equals("4");
		boolean isRightWall = x < mapWidth - 1 && mapArray[y][x + 1].equals("4");
		boolean isUpWall = y > 0 && mapArray[y - 1][x].equals("4");
		boolean isBottomWall = y < mapHeight - 1 && mapArray[y + 1][x].equals("4");

		if (!isLeftWall && !isRightWall && !isUpWall && !isBottomWall) {
			return "WALL_L_R_U_B";
		} else if (isLeftWall && !isRightWall && !isUpWall && !isBottomWall) {
			return "WALL_R_U_B";
		} else if (!isLeftWall && isRightWall && !isUpWall && !isBottomWall) {
			return "WALL_L_U_B";
		} else if (!isLeftWall && !isRightWall && isUpWall && !isBottomWall) {
			return "WALL_L_R_B";
		} else if (!isLeftWall && !isRightWall && !isUpWall && isBottomWall) {
			return "WALL_L_R_U";
		} else if (isLeftWall && isRightWall && !isUpWall && !isBottomWall) {
			return "WALL_U_B";
		} else if (!isLeftWall && !isRightWall && isUpWall && isBottomWall) {
			return "WALL_L_R";
		} else if (isLeftWall && !isRightWall && isUpWall && !isBottomWall) {
			return "WALL_R_B";
		} else if (!isLeftWall && isRightWall && isUpWall && !isBottomWall) {
			return "WALL_L_B";
		} else if (isLeftWall && !isRightWall && !isUpWall && isBottomWall) {
			return "WALL_R_U";
		} else if (!isLeftWall && isRightWall && !isUpWall && isBottomWall) {
			return "WALL_L_U";
		} else if (isLeftWall && isRightWall && isUpWall && !isBottomWall) {
			return "WALL_B";
		} else if (isLeftWall && isRightWall && !isUpWall && isBottomWall) {
			return "WALL_U";
		} else if (!isLeftWall && isRightWall && isUpWall && isBottomWall) {
			return "WALL_L";
		} else if (isLeftWall && !isRightWall && isUpWall && isBottomWall) {
			return "WALL_R";
		} else
		return "WALL_INNER_CORNER";
	
	}

	/*
		Reads textures from tiles.txt into textures and tileDict.
	*/
	static void readMap(String filename) {
		String mapString = "";
		try {
			mapString = GFU.loadTextFile(filename);
		} catch (Exception e ) {
			System.out.println("Could not load map "+filename);
		}
		String[] mapRows = mapString.split("\n");

		int mapHeight = mapRows.length;
		int mapWidth = mapRows[0].split(",").length;

		// Create a 2D array for the map
		String[][] mapArray = new String[mapHeight][mapWidth];
		for (int i = 0; i < mapHeight; i++) {
			mapArray[i] = mapRows[i].split(",");
		}

		for (int i = 0; i < mapHeight; i++) {
			for (int j = 0; j < mapWidth; j++) {
				String tileChar = mapArray[i][j];
				if (tileChar.equals("1") || tileChar.equals("5")) {
					//map[j][i] = new Tile(j, i, "BACKGROUND_TILE", "FLOOR", true);
				} else if (tileChar.equals("2")) {
					String waterTileType = getWaterTileType(j, i, mapArray);
					map[j][i] = new Tile(j, i, "BACKGROUND_TILE", waterTileType, false);
				} else if (tileChar.equals("3")) {
					String deepWaterTileType = getDeepWaterTileType(j, i, mapArray);
					map[j][i] = new Tile(j, i, "BACKGROUND_TILE", deepWaterTileType, false);
				} else if (tileChar.equals("4")) {
					String wallTileType = getWallTileType(j, i, mapArray);
					map[j][i] = new Tile(j, i, "WALL_TILE", wallTileType, false);
				}

				// Rock generation
				if (tileChar.equals("5")) {
					int textureIndex = GFU.randint(0, 7);
					String[] textures = {
						"STONE",
						"IRON",
						"GOLD",
						"SILVER",
						"COPPER",
						"STEEL",
						"DIAMOND",
						"RUBY"
					};
					String texture = textures[textureIndex];
					new Rock(j, i, "ROCK_BREAKABLE", texture, texture);
					// new Rock(j, i, "ROCK_BREAKABLE", "STONE", "STONE"); // order of strings: type, image, resource
				}
			}
		}
	}

	public void initFloor() {
		String term = "FLOOR_VARIANT_";
		int rand;

		for (int y = 0; y < MAP_HEIGHT; y++) {
			for (int x = 0; x < MAP_WIDTH; x++) {
				rand = GFU.randint(0,40);
				if (rand > 6) { rand = 0;}
				floormap[x][y] = term+GFU.randint(0,6);
			}
		}

	}

	// Draws all the tiles in the map. 
	public void drawTiles() {
		GFStamp s;
		Tile cur;

		for (int y = 0; y < MAP_HEIGHT; y++) {
			for (int x = 0; x < MAP_WIDTH; x++) {
				cur = map[x][y];

				if (cur != null && !cur.image_name.equals("NOTHING")) { // blank tiles aren't rendered
					s = cur.getImage();
					s.moveTo(x * TILE_SIZE - cameraX, y * TILE_SIZE - cameraY);
					s.stamp();
				}
			}
		}
	}

	public void drawBase() {
		GFStamp s = TextureEntry.get("FLOOR");

		for (int y = 0; y < MAP_HEIGHT; y++) {
			for (int x = 0; x < MAP_WIDTH; x++) {
				
				s = TextureEntry.get(floormap[x][y]);
				s.moveTo(x * TILE_SIZE - cameraX, y * TILE_SIZE - cameraY);
				s.stamp();
			}
		}
	}

	public void drawFeatures() {
		GFStamp s;
		Feature cur;

		for (int i = 0; i < Feature.features.size(); i++) {
			cur = Feature.features.get(i);
			if (!cur.image_name.equals("NOTHING")) { // don't render NOTHING features
				s = cur.getImage();
				s.moveTo(cur.x * TILE_SIZE - cameraX, cur.y * TILE_SIZE - cameraY);
				s.stamp();
			}
		}
	}

	public void drawPlayer() {
		GFStamp s = Player.player.getImage();
		s.moveTo(Player.player.x - cameraX, Player.player.y - cameraY);
		s.stamp();
	}

	// sets the camera to the position of the player, such that everything is drawn relative to the camera.
	// Also centers player: this.WIDTH and this.HEIGHT are defined in GFGame as being the size of the window.
	public void updateCamera() {

		if (scrolling) {
			cameraX = Player.player.x - this.WIDTH/2 + TILE_SIZE/2;
			cameraY = Player.player.y - this.HEIGHT/2 + TILE_SIZE/2;
		}
	}

	@Override
	public void onDraw (int frameCount)
	{
		backgroundMusic.volume(0.2f);
		if (frameCount%12600 == 0) {
			backgroundMusic.play();
		}
		updateCamera();
		updatePlayerMovement();
		drawBase();
		drawTiles();
		drawFeatures();
		DroppedItem.drawItems();
		drawPlayer();
		inventory.drawInventory();
	}

	/* Uses the key strokes of WASD, and arrow symbols to move Player (player class)
	 * Checks if player is trying to move past the edge of the screen. If so, doesn't allow
	 * player to move past it, if not allows player to move in its direction.
	 * Implements Player.moveTo function to move player, which automatically checks
	 * for player's collision against other objects.
	 *
	 */
	public void onKeyDown (String key, int code, int flags) {
		pressedKeys.add(code);

		if (code == GFKey.Space) {
			breakRock();
		}
	}

	public void onKeyUp(String key, int code, int flags) {
        pressedKeys.remove(code); // Add this line
    }

    public void updatePlayerMovement() {
        int x = Player.player.x;
        int y = Player.player.y;

		int dx = 0;
		int dy = 0;

		if (pressedKeys.contains(GFKey.ArrowDown) || pressedKeys.contains(GFKey.S)) {
			dy += 1;
		}
		if (pressedKeys.contains(GFKey.ArrowUp) || pressedKeys.contains(GFKey.W)) {
			dy -= 1;
		}
		if (pressedKeys.contains(GFKey.ArrowRight) || pressedKeys.contains(GFKey.D)) {
			dx += 1;
		}
		if (pressedKeys.contains(GFKey.ArrowLeft) || pressedKeys.contains(GFKey.A)) {
			dx -= 1;
		}

		Player.player.moveTo(x + dx, y + dy);
    }
	public void breakRock() {
		Rock current;
		// boolean playerNearRock;
		int x = Player.player.x;
		int y = Player.player.y;

		for (int i = 0; i < Rock.rocks.size(); i++) {
			current = Rock.rocks.get(i);

			if (Math.abs(current.x-x/32)<2 && Math.abs(current.y-y/32)<2) {
				current.breakRock();
				break;
			}
		}
	}

}
