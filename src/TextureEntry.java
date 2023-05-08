/* 

	This is an API of sorts for getting the stamp from the String ID.
	It's almost certainly more complicated than necessary, but changes will happen
	in refactoring. 
	TL;DR: 
		To get a texture from elsewhere: TextureEntry.get(name)
		To put a new tile on a preexisting texture map: edit the init statement, adding a new line
		To add a new texture map (i.e. in cases where you don't want a 32x32 texture): copy the format of the preexisting map


*/ 

import GaFr.GFStamp;
import GaFr.GFTexture;
import java.util.HashMap;

public class TextureEntry {

	public static HashMap<String, GFStamp> textureMap = new HashMap<String, GFStamp>();

	public static void init() {

		index ( // obvious placeholder sprites :)
			new String[] {
				// format: NAME, XPOS, YPOS
				"NOTHING, 0, 4", // special case -- just won't be rendered, so xy don't matter
				"NOT_FOUND, 0, 2", // special case -- is the fallback, and should be an obvious texture to signal problems
				"FLOOR, 3, 1",
				"SWORD, 2, 2",
				"PLAYER, 4, 2",
				"STONE, 0, 0",
				"IRON, 1, 0",
				"GOLD, 2, 0",
				"SILVER, 3, 0",
				"COPPER, 4, 0",
				"STEEL, 5, 0",
				"DIAMOND, 6, 0",
				"RUBY, 7, 0",
				"DEEP_WATER, 0, 1",
				"WATER, 1, 1",
				"INVENTORY_BACKGROUND, 1, 2",
				"LADDER, 6, 2", // placeholder :)
				"SMITHING_TABLE, 7, 2", 
				"MINERAL_PARTICLE, 5, 2", // placeholder :)
				"STONE_ITEM, 0, 13",
				"IRON_ITEM, 1, 13",
				"GOLD_ITEM, 2, 13",
				"SILVER_ITEM, 3, 13",
				"COPPER_ITEM, 4, 13",
				"STEEL_ITEM, 5, 13",
				"DIAMOND_ITEM, 6, 13",
				"RUBY_ITEM, 7, 13",
				"STONE_ITEM_2, 0, 14",
				"IRON_ITEM_2, 1, 14",
				"GOLD_ITEM_2, 2, 14",
				"SILVER_ITEM_2, 3, 14",
				"COPPER_ITEM_2, 4, 14",
				"STEEL_ITEM_2, 5, 14",
				"DIAMOND_ITEM_2, 6, 14",
				"RUBY_ITEM_2, 7, 14",
				"STONE_ITEM_3, 0, 15",
				"IRON_ITEM_3, 1, 15",
				"GOLD_ITEM_3, 2, 15",
				"SILVER_ITEM_3, 3, 15",
				"COPPER_ITEM_3, 4, 15",
				"STEEL_ITEM_3, 5, 15",
				"DIAMOND_ITEM_3, 6, 15",
				"RUBY_ITEM_3, 7, 15",
				// Optional floor variants
				"FLOOR_VARIANT_0, 3, 1",
				"FLOOR_VARIANT_1, 6, 3",
				"FLOOR_VARIANT_2, 6, 4",
				"FLOOR_VARIANT_3, 6, 5",
				"FLOOR_VARIANT_4, 7, 3",
				"FLOOR_VARIANT_5, 7, 4",
				"FLOOR_VARIANT_6, 7, 5",
				// Water pools
				"SHALLOW_UL_CORNER, 0, 6",
				"SHALLOW_UR_CORNER, 1, 6",
				"SHALLOW_BL_CORNER, 0, 7",
				"SHALLOW_BR_CORNER, 1, 7",
				"SHALLOW_UL_CORNER_FLOOR, 2, 6",
				"SHALLOW_UR_CORNER_FLOOR, 3, 6",
				"SHALLOW_BL_CORNER_FLOOR, 2, 7",
				"SHALLOW_BR_CORNER_FLOOR, 3, 7",
				"DEEP_UL_CORNER, 4, 6",
				"DEEP_UR_CORNER, 5, 6",
				"DEEP_BL_CORNER, 4, 7",
				"DEEP_BR_CORNER, 5, 7",
				"DEEP_UL_CORNER_FLOOR, 6, 6",
				"DEEP_UR_CORNER_FLOOR, 7, 6",
				"DEEP_BL_CORNER_FLOOR, 6, 7",
				"DEEP_BR_CORNER_FLOOR, 7, 7",
				// the deep water only tiles are not indexed!
				"SHALLOW_LOBE_UPPER, 0, 10",
				"SHALLOW_LOBE_LOWER, 1, 11",
				"SHALLOW_LOBE_LEFT, 0, 11",
				"SHALLOW_LOBE_RIGHT, 1, 10",
				"DEEP_LOBE_UPPER, 0, 12",
				"DEEP_LOBE_LOWER, 1, 13",
				"DEEP_LOBE_LEFT, 0, 13",
				"DEEP_LOBE_RIGHT, 1, 12",
				"DEEPFLOOR_UL_CORNER, 0, 8",
				"DEEPFLOOR_UR_CORNER, 1, 8",
				"DEEPFLOOR_BL_CORNER, 0, 9",
				"DEEPFLOOR_BR_CORNER, 1, 9",
				"DEEPFLOOR_UL_CORNER_FLOOR, 2, 8", // should probably be walkable tiles
				"DEEPFLOOR_UR_CORNER_FLOOR, 3, 8",
				"DEEPFLOOR_BL_CORNER_FLOOR, 2, 9",
				"DEEPFLOOR_BR_CORNER_FLOOR, 3, 9",
				// Edge shadows
				"SHADOW_LEFT, 0, 12",
				"SHADOW_TOP, 1, 12",
				"SHADOW_RIGHT, 2, 12",
				"SHADOW_BOTTOM, 3, 12",

			}, 32, "assets/images/sdv-tiles.png"
		);

		index(
			new String[] {
				"WALL_INNER_CORNER, 2, 2",
				"WALL_L_R_U_B, 0, 2",
				"WALL_L_B, 0, 0",
				"WALL_B, 1, 0",
				"WALL_R_B, 2, 0",
				"WALL_R, 3, 0",
				"WALL_R_U, 4, 0",
				"WALL_L_R, 0, 1",
				"WALL_L_R_U, 1, 1",
				"WALL_U_B, 2, 1",
				"WALL_L_R_B, 3, 1",
				"WALL_L_U_B, 4, 1",
				"WALL_R_U_B, 1, 2",
				"WALL, 3, 2",
				"WALL_L_U, 0, 3",
				"WALL_U, 1, 3",
				"WALL_L, 2, 3",
			}, 40, "assets/images/silhouettes-overhang.png"
		);
		
		// Crystal theme
		index(
			new String[] {
				"CRYSTAL_WALL_INNER_CORNER, 2, 2",
				"CRYSTAL_WALL_L_R_U_B, 0, 2",
				"CRYSTAL_WALL_L_B, 0, 0",
				"CRYSTAL_WALL_B, 1, 0",
				"CRYSTAL_WALL_R_B, 2, 0",
				"CRYSTAL_WALL_R, 3, 0",
				"CRYSTAL_WALL_R_U, 4, 0",
				"CRYSTAL_WALL_L_R, 0, 1",
				"CRYSTAL_WALL_L_R_U, 1, 1",
				"CRYSTAL_WALL_U_B, 2, 1",
				"CRYSTAL_WALL_L_R_B, 3, 1",
				"CRYSTAL_WALL_L_U_B, 4, 1",
				"CRYSTAL_WALL_R_U_B, 1, 2",
				"CRYSTAL_WALL, 3, 2",
				"CRYSTAL_WALL_L_U, 0, 3",
				"CRYSTAL_WALL_U, 1, 3",
				"CRYSTAL_WALL_L, 2, 3",
				"CRYSTAL_FLOOR, 0, 4",
				"CRYSTAL_FLOOR_VARIANT_0, 0, 4",
				"CRYSTAL_FLOOR_VARIANT_1, 0, 4",
				"CRYSTAL_FLOOR_VARIANT_2, 0, 4",
				"CRYSTAL_FLOOR_VARIANT_3, 0, 4",
				"CRYSTAL_FLOOR_VARIANT_4, 0, 4",
				"CRYSTAL_FLOOR_VARIANT_5, 0, 4",
				"CRYSTAL_FLOOR_VARIANT_6, 0, 4"
			}, 40, "assets/images/Theme-Crystal.png"
		);
		
		// Dirt theme
		index(
			new String[] {
				"DIRT_WALL_INNER_CORNER, 2, 2",
				"DIRT_WALL_L_R_U_B, 0, 2",
				"DIRT_WALL_L_B, 0, 0",
				"DIRT_WALL_B, 1, 0",
				"DIRT_WALL_R_B, 2, 0",
				"DIRT_WALL_R, 3, 0",
				"DIRT_WALL_R_U, 4, 0",
				"DIRT_WALL_L_R, 0, 1",
				"DIRT_WALL_L_R_U, 1, 1",
				"DIRT_WALL_U_B, 2, 1",
				"DIRT_WALL_L_R_B, 3, 1",
				"DIRT_WALL_L_U_B, 4, 1",
				"DIRT_WALL_R_U_B, 1, 2",
				"DIRT_WALL, 3, 2",
				"DIRT_WALL_L_U, 0, 3",
				"DIRT_WALL_U, 1, 3",
				"DIRT_WALL_L, 2, 3",
				"DIRT_FLOOR, 0, 4",
				"DIRT_FLOOR_VARIANT_0, 0, 4",
				"DIRT_FLOOR_VARIANT_1, 0, 4",
				"DIRT_FLOOR_VARIANT_2, 0, 4",
				"DIRT_FLOOR_VARIANT_3, 0, 4",
				"DIRT_FLOOR_VARIANT_4, 0, 4",
				"DIRT_FLOOR_VARIANT_5, 0, 4",
				"DIRT_FLOOR_VARIANT_6, 0, 4"
			}, 40, "assets/images/Theme-Dirt.png"
		);
		
		// Lava theme
		index(
			new String[] {
				"LAVA_WALL_INNER_CORNER, 2, 2",
				"LAVA_WALL_L_R_U_B, 0, 2",
				"LAVA_WALL_L_B, 0, 0",
				"LAVA_WALL_B, 1, 0",
				"LAVA_WALL_R_B, 2, 0",
				"LAVA_WALL_R, 3, 0",
				"LAVA_WALL_R_U, 4, 0",
				"LAVA_WALL_L_R, 0, 1",
				"LAVA_WALL_L_R_U, 1, 1",
				"LAVA_WALL_U_B, 2, 1",
				"LAVA_WALL_L_R_B, 3, 1",
				"LAVA_WALL_L_U_B, 4, 1",
				"LAVA_WALL_R_U_B, 1, 2",
				"LAVA_WALL, 3, 2",
				"LAVA_WALL_L_U, 0, 3",
				"LAVA_WALL_U, 1, 3",
				"LAVA_WALL_L, 2, 3",
				"LAVA_FLOOR, 0, 4",
				"LAVA_FLOOR_VARIANT_0, 0, 4",
				"LAVA_FLOOR_VARIANT_1, 0, 4",
				"LAVA_FLOOR_VARIANT_2, 0, 4",
				"LAVA_FLOOR_VARIANT_3, 0, 4",
				"LAVA_FLOOR_VARIANT_4, 0, 4",
				"LAVA_FLOOR_VARIANT_5, 0, 4",
				"LAVA_FLOOR_VARIANT_6, 0, 4"
			}, 40, "assets/images/Theme-Lava.png"
		);

		// Abyss theme
		index(
			new String[] {
				"ABYSS_WALL_INNER_CORNER, 2, 2",
				"ABYSS_WALL_L_R_U_B, 0, 2",
				"ABYSS_WALL_L_B, 0, 0",
				"ABYSS_WALL_B, 1, 0",
				"ABYSS_WALL_R_B, 2, 0",
				"ABYSS_WALL_R, 3, 0",
				"ABYSS_WALL_R_U, 4, 0",
				"ABYSS_WALL_L_R, 0, 1",
				"ABYSS_WALL_L_R_U, 1, 1",
				"ABYSS_WALL_U_B, 2, 1",
				"ABYSS_WALL_L_R_B, 3, 1",
				"ABYSS_WALL_L_U_B, 4, 1",
				"ABYSS_WALL_R_U_B, 1, 2",
				"ABYSS_WALL, 3, 2",
				"ABYSS_WALL_L_U, 0, 3",
				"ABYSS_WALL_U, 1, 3",
				"ABYSS_WALL_L, 2, 3",
				"ABYSS_FLOOR, 0, 4",
				"ABYSS_FLOOR_VARIANT_0, 0, 4",
				"ABYSS_FLOOR_VARIANT_1, 0, 4",
				"ABYSS_FLOOR_VARIANT_2, 0, 4",
				"ABYSS_FLOOR_VARIANT_3, 0, 4",
				"ABYSS_FLOOR_VARIANT_4, 0, 4",
				"ABYSS_FLOOR_VARIANT_5, 0, 4",
				"ABYSS_FLOOR_VARIANT_6, 0, 4"
			}, 40, "assets/images/Theme-Abyss.png"
		);

		index(
			new String[] {
				"PLAYER_FORWARD_STILL, 0, 0",
				"PLAYER_FORWARD_WALK1, 1, 0",
				"PLAYER_FORWARD_WALK2, 2, 0",
				"PLAYER_BACKWARD_STILL, 3, 0",
				"PLAYER_BACKWARD_WALK1, 4, 0",
				"PLAYER_BACKWARD_WALK2, 5, 0",
				"PLAYER_RIGHT_STILL, 0, 1",
				"PLAYER_RIGHT_WALK1, 2, 1",
				"PLAYER_RIGHT_WALK2, 3, 1",
				"PLAYER_LEFT_STILL, 1, 1",
				"PLAYER_LEFT_WALK1, 4, 1",
				"PLAYER_LEFT_WALK2, 5, 1",
			}, 32, "assets/images/person-sprites.png"
		);

		index(
			new String[] {
				"SWORD_PLAYER_FORWARD_STILL, 0, 0",
				"SWORD_PLAYER_FORWARD_WALK1, 1, 0",
				"SWORD_PLAYER_FORWARD_WALK2, 2, 0",
				"SWORD_PLAYER_BACKWARD_STILL, 3, 0",
				"SWORD_PLAYER_BACKWARD_WALK1, 4, 0",
				"SWORD_PLAYER_BACKWARD_WALK2, 5, 0",
				"SWORD_PLAYER_RIGHT_STILL, 0, 1",
				"SWORD_PLAYER_RIGHT_WALK1, 2, 1",
				"SWORD_PLAYER_RIGHT_WALK2, 3, 1",
				"SWORD_PLAYER_LEFT_STILL, 1, 1",
				"SWORD_PLAYER_LEFT_WALK1, 4, 1",
				"SWORD_PLAYER_LEFT_WALK2, 5, 1",
			}, 32, "assets/images/person-sprites-sword.png"
		);

		// Special process for the fog tile, since it needs a custom pin!
		GFStamp fog = new GFStamp("assets/images/FoggyTile.png");
		fog.movePinTo(32f, 32f);
		textureMap.put("FOG", fog);

		// Special process for crafting screens
		GFStamp craftAvailable = new GFStamp("assets/images/craft-available.png");
		textureMap.put("CRAFT_AVAILABLE", craftAvailable);

		GFStamp craftNeedBoth = new GFStamp("assets/images/craft-need-both.png");
		textureMap.put("CRAFT_NEED_BOTH", craftNeedBoth);

		GFStamp craftNeedDiamonds = new GFStamp("assets/images/craft-need-diamonds.png");
		textureMap.put("CRAFT_NEED_DIAMONDS", craftNeedDiamonds);

		GFStamp craftNeedStones = new GFStamp("assets/images/craft-need-stones.png");
		textureMap.put("CRAFT_NEED_STONES", craftNeedStones);

		GFStamp craftBtn = new GFStamp("assets/images/craft-button.png");
		textureMap.put("CRAFT_BTN", craftBtn);
	}

	public static void index(String[] input, int size, String texture_file) {
		String[] args;
		GFStamp[][] stamps = new GFTexture(texture_file).splitIntoTilesBySize2D(size,size);
		int tx;
		int ty;

		for (int i = 0; i < input.length; i++) {
			args = input[i].split(",");

			// trim whitespace
			for (int j = 0; j < args.length; j++) {
				args[j] = args[j].trim();
			}

			try {
				tx = Integer.parseInt(args[1]);
				ty = Integer.parseInt(args[2]);

				stamps[tx][ty].movePinTo(0.0f,0.0f); // upper left pin

				textureMap.put(args[0], stamps[tx][ty]);

			} catch (Exception e) {
				System.out.println("Error processing line \""+input[i]+"\" from "+texture_file+": check arguments.");
			}
		}
		System.out.println("Texture map initialized");
	}


	public static GFStamp get(String name) {
		if (textureMap.containsKey(name)) {
			return textureMap.get(name);
		} else {

			System.out.println("\""+name+"\" not found.");
			if (textureMap.size() == 0) {
				System.out.println("Has the texture map been initialized?");
				return null;
			} else {
				return textureMap.get("NOT_FOUND");
			}

		}
	}

}