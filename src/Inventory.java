import GaFr.GFStamp;
import GaFr.GFTexture;
import java.util.HashMap;
import GaFr.GFFont;
import GaFr.GFSound;


public class Inventory {

    private int[] itemCount;
    private HashMap<String, Integer> itemTypeMap;
    private static final int ITEM_TYPES = 10;
    private int x, y; // Position of the inventory on the screen
    private static final int BOX_SIZE = 32;
    private static final int MAX_PREVIEW = 10;
    GFStamp boxImg =  TextureEntry.get("INVENTORY_BACKGROUND");
    GFSound scoreIncrease = new GFSound("assets/sounds/scoreIncrease.mp3");
    GFStamp[] itemStamps = {
			TextureEntry.get("SWORD"), // Sword!
			TextureEntry.get("GOLD_ITEM"),
			TextureEntry.get("SILVER_ITEM"),
			TextureEntry.get("COPPER_ITEM"),
			TextureEntry.get("IRON_ITEM"),
			TextureEntry.get("STEEL_ITEM"),
			TextureEntry.get("DIAMOND_ITEM"),
			TextureEntry.get("RUBY_ITEM"),
			TextureEntry.get("STONE_ITEM"),
			TextureEntry.get("NOT_FOUND"), // empty -- add more!
		};

    GFFont font = new GFFont("gafr/fonts/spleen/spleen-8x16.ffont.json");

    public Inventory(int x, int y) {
        this.itemCount = new int[ITEM_TYPES];
        this.itemTypeMap = new HashMap<>();
        initializeItemTypeMap();
        this.x = x; // middle of the screen
        this.y = y;
    }

    private void initializeItemTypeMap() {
        itemTypeMap.put("SWORD", 0);
        itemTypeMap.put("GOLD", 1);
        itemTypeMap.put("SILVER", 2);
        itemTypeMap.put("COPPER", 3);
        itemTypeMap.put("IRON", 4);
        itemTypeMap.put("STEEL", 5);
        itemTypeMap.put("DIAMOND", 6);
        itemTypeMap.put("RUBY", 7);
        itemTypeMap.put("STONE", 8);
        itemTypeMap.put("empty", 9);
    }

    public boolean hasResource(String itemType, int amount) {
        Integer index = itemTypeMap.get(itemType);
        if (index != null) {
            return itemCount[index] >= amount;
        }
        return false;
    }

    public void addItem(String itemType) {
        Integer index = itemTypeMap.get(itemType);
        if (index != null) {
            itemCount[index]++;
            scoreIncrease.play();
        }
    }

    public void removeItem(String itemType) {
        Integer index = itemTypeMap.get(itemType);
        if (index != null && itemCount[index] > 0) {
            itemCount[index]--;
        }
    }

    public void deleteItem(String itemType) {
        Integer index = itemTypeMap.get(itemType);
        if (index != null) {
            itemCount[index] = 0;
        }
    }

    public void drawInventory() {
        int xPos = x - (BOX_SIZE * (MAX_PREVIEW - 1)) / 2; // Center the inventory items
        int itemXPos = xPos;
        for (int i = 0; i < MAX_PREVIEW; i++) {
            GFStamp itemStamp;

            boxImg.moveTo(xPos, y);
            boxImg.stamp();
            xPos += BOX_SIZE;

            if (itemCount[i] > 0) {
                itemStamp = (i >= 0 && i < itemStamps.length) ? itemStamps[i] : null;
                itemStamp.moveTo(itemXPos, y);
                itemStamp.stamp();
                font.draw(itemXPos + BOX_SIZE - 12, y + BOX_SIZE - 16, String.valueOf(itemCount[i])); // Display item count
                itemXPos += BOX_SIZE;
            }
        }
    }
}
