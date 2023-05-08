import java.lang.*;
import java.util.*;

public class SmithingTable extends Feature {

	public SmithingTable(int new_x, int new_y, String new_type, String new_image_name) {
		super(new_x, new_y, new_type, new_image_name, false); // rocks aren't passable
	}

    public void click() {
        if (Game.craftingMenu.isVisible) {
            Game.craftingMenu.hide();
        } else {
            Game.craftingMenu.show();
        }
    }
}
