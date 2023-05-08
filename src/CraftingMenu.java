import GaFr.GFStamp;
import GaFr.GFTexture;

public class CraftingMenu {
    private GFStamp craftingBackground;
    private GFStamp craftingBackgroundAvailable = TextureEntry.get("CRAFT_AVAILABLE");
    private GFStamp craftingBackgroundNeedBoth = TextureEntry.get("CRAFT_NEED_BOTH");
    private GFStamp craftingBackgroundNeedDiamonds = TextureEntry.get("CRAFT_NEED_DIAMONDS");
    private GFStamp craftingBackgroundNeedStones = TextureEntry.get("CRAFT_NEED_STONES");
    private GFStamp craftButton = TextureEntry.get("CRAFT_BTN");

    public boolean isVisible;
    private boolean canCraftDiamondSword = false;
    private int bgX, bgY; // Position of the background
    private int btnX, btnY; // Position of the button
    private int backgroundSizeX = 540;
    private int backgroundSizeY = 288;
    private int buttonSizeX = 144;
    private int buttonSizeY = 40;

    public CraftingMenu(int x, int y) {
        this.bgX = x-backgroundSizeX/2;
        this.bgY = y-backgroundSizeY/2;
        this.btnX = x-buttonSizeX/2;
        this.btnY = (y-buttonSizeY/2)+100;
        this.isVisible = false;
    }

    public boolean isCraftButtonClicked(int mouseX, int mouseY) {
        if (canCraftDiamondSword) {
            return (mouseX >= btnX && mouseX <= btnX+buttonSizeX && mouseY >= btnY && mouseY <= btnY+buttonSizeY);
        } 
        return false;
    }

    public void show() {
        if (Game.inventory.hasResource("STONE", 3) && Game.inventory.hasResource("DIAMOND", 2)) {
            craftingBackground = craftingBackgroundAvailable;
            canCraftDiamondSword = true;
        } else if (Game.inventory.hasResource("STONE", 3)) {
            craftingBackground = craftingBackgroundNeedDiamonds;
            canCraftDiamondSword = false;
        } else if (Game.inventory.hasResource("DIAMOND", 2)) {
            craftingBackground = craftingBackgroundNeedStones;
            canCraftDiamondSword = false;
        } else {
            craftingBackground = craftingBackgroundNeedBoth;
            canCraftDiamondSword = false;
        }
        isVisible = true;
    }

    public void hide() {
        isVisible = false;
    }

    public void drawCraftingMenu() {
        if (!isVisible) {
            return;
        }
    
        craftingBackground.moveTo(bgX, bgY);
        craftingBackground.stamp();
        
        if (canCraftDiamondSword) {
            craftButton.moveTo(btnX, btnY);
            craftButton.stamp();
        }
    }

    public void craftDiamondSword() {
        if (canCraftDiamondSword) {
            Game.inventory.removeItem("STONE");
            Game.inventory.removeItem("STONE");
            Game.inventory.removeItem("STONE");
            Game.inventory.removeItem("DIAMOND");
            Game.inventory.removeItem("DIAMOND");

            Game.inventory.addItem("SWORD");
            Player.player.setHasSword(true);

            hide();
        } 
    }
}
