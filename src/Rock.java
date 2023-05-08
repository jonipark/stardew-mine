import GaFr.GFU;
import java.lang.*;
import java.util.*;
import GaFr.GFSound;

public class Rock extends Feature {

	String resource; // the ore type of the rock

	int health = 3;

	public static ArrayList<Rock> rocks = new ArrayList<Rock>(); // A list of all rocks.
	GFSound breakRock = new GFSound("assets/sounds/breakRock.wav");

	public Rock(int new_x, int new_y, String new_type, String new_image_name, String new_resource) {
		super(new_x, new_y, new_type, new_image_name, false); // rocks aren't passable
		resource = new_resource;
		rocks.add(this);
	}


	public void hitRock() {
		health--;
    Particle.newRockSmash( this.x*Game.TILE_SIZE, this.y*Game.TILE_SIZE);
		if (health == 0 ) {
			breakRock();
		}
	}

	// Called when a rock is broken by the player.
	public void breakRock() {
		// drop 1-3 items
		int dropCount = GFU.randint(1, 3);
		for (int i = 0; i < dropCount; i++) {
			// rocks drop 50% their ore type and 50% rocks
			if (GFU.randint(1, 2) == 1) {
				new DroppedItem(this.x, this.y, "STONE", 3);
			} else {
				new DroppedItem(this.x, this.y, this.resource, 3);
			}
		}
		Ladder.trySpawnLadder(this.x, this.y);
		breakRock.play();
		Rock.rocks.remove(this);
		super.delete();
	}
	
	public void click() {
		this.hitRock();
	}

}
