package com.sulvic.core.common;

import com.sulvic.core.common.SulvicObjects.EnumGem;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class FolkrumTabs{
	
	public static final CreativeTabs BLOCKS = new CustomTab("blocks"){
		
		public ItemStack getTabIconItem(){ return new ItemStack(SulvicObjects.GEM_BLOCKS, 1, 4); }
		
	}, ITEMS = new CustomTab("items"){
		
		public ItemStack getTabIconItem(){ return new ItemStack(SulvicObjects.GEMS, 1, 9); }
		
	}, FOOD = new CustomTab("food"){
		
		public ItemStack getTabIconItem(){ return new ItemStack(Items.APPLE); } // SulvicItems.BAKES_SWEET_POTATO
		
	}, TOOLS = new CustomTab("tools"){
		
		public ItemStack getTabIconItem(){ return new ItemStack(SulvicObjects.getGemSpade(EnumGem.CASSITERITE)); }
		
	}, EQUIP = new CustomTab("equip"){
		
		public ItemStack getTabIconItem(){ return new ItemStack(Items.GOLDEN_LEGGINGS); } // SulvicItems.getGemLeggings(2) // Sapphire
		
	}, DISCS = new CustomTab("discs"){
		
		public ItemStack getTabIconItem(){ return new ItemStack(Items.RECORD_13); }
		
	}, UNSPECIFIED = new CustomTab("unspecified"){
		
		public ItemStack getTabIconItem(){ return new ItemStack(Blocks.BEDROCK); }
		
	};
	
	protected static abstract class CustomTab extends CreativeTabs{
		
		public CustomTab(String label){ super(label); }
		
		public String getTranslatedTabLabel(){ return "modGroup." + getTabLabel(); }
		
	}
	
}
