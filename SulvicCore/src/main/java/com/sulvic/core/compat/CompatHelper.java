package com.sulvic.core.compat;

import static net.minecraft.init.Blocks.STONE_BUTTON;
import static net.minecraft.init.Blocks.WOODEN_BUTTON;
import static net.minecraft.init.Items.*;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

@SuppressWarnings("deprecation")
public class CompatHelper{
	
	private static final Set<? extends Item> RE_MAX_32 = Sets.newHashSet(BUCKET, EGG, ENDER_PEARL, IRON_DOOR, SNOWBALL, OAK_DOOR, BIRCH_DOOR, SPRUCE_DOOR, JUNGLE_DOOR, ACACIA_DOOR, DARK_OAK_DOOR);
	
	public static boolean modExists(String modid){ return Loader.isModLoaded(modid); }
	
	public static void addButtonOreDict(){
		OreDictionary.registerOre("button", STONE_BUTTON);
		OreDictionary.registerOre("button", WOODEN_BUTTON);
	}
	
	public static void attemptResizeMaxStack(){ for(Item item: RE_MAX_32) if(item.getMaxDamage() < 32) item.setMaxStackSize(32); }
	
}
