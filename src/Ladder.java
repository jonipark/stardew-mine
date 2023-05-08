import GaFr.GFU;
import java.lang.*;
import java.util.*;
import GaFr.GFSound;
import GaFr.GFStamp;

public class Ladder extends Feature {

	public static final float LADDER_CHANCE = 0.05f; // 5% feels reasonable? 

	public Ladder(int new_x, int new_y, String new_type, String new_image_name) {
		super(new_x, new_y, new_type, new_image_name, false); // ladders aren't passable
	}

	public void followLadder() {
		int rand = GFU.randint(0, Game.LEVELS.length-1);
		Game.readMap(Game.LEVELS[rand]);
		this.delete();
	}

	public void click() {
		this.followLadder();
	}

	public static void trySpawnLadder(int rockx, int rocky) {
		int rand = GFU.randint(0, 100);
		if (rand < (int)(LADDER_CHANCE*100) || Rock.rocks.size() <= 1) {
			// force spawn on last rock break
			new Ladder(rockx, rocky, "LADDER", "LADDER");
		}
	}

}
