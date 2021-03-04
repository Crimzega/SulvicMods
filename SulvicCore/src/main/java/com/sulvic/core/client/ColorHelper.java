package com.sulvic.core.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;

public class ColorHelper{
	
	private static BlockColors blockColorizer;
	private static ItemColors itemColorizer;
	
	private static final Logger logger = LogManager.getLogger("ColorHelper");
	
	public static boolean hasBlockColorizer(){ return blockColorizer != null; }
	
	public static boolean hasItemColorizer(){ return itemColorizer != null; }
	
	public static void addBlockColorizer(BlockColors colorizer){ blockColorizer = colorizer; }
	
	public static void addItemColorizer(ItemColors colorizer){ itemColorizer = colorizer; }
	
	public static void applyBlockColors(IBlockColorizer colorizer){
		try{
			blockColorizer.registerBlockColorHandler(colorizer.blockColor(), colorizer.blocks());
		}
		catch(NullPointerException npe){
			logger.error("The collection of blocks cannot be null.", npe);
		}
	}
	
	public static void applyItemColors(IItemColorizer colorizer){
		try{
			itemColorizer.registerItemColorHandler(colorizer.itemColor(), colorizer.items());
		}
		catch(NullPointerException npe){
			logger.error("The collection of items cannot be null.", npe);
		}
	}
	
	public static interface IBlockColorizer{
		
		int DEFAULT_COLOR = 0xFFFFFF;
		
		Block[] blocks();
		
		IBlockColor blockColor();
		
	}
	
	public static interface IItemColorizer{
		
		int DEFAULT_COLOR = 0xFFFFFF;
		
		Item[] items();
		
		IItemColor itemColor();
		
	}
	
}
