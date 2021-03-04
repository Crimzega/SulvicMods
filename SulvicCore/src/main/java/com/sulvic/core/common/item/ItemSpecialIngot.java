package com.sulvic.core.common.item;

import com.sulvic.core.ReferenceSC;
import com.sulvic.core.common.FolkrumTabs;

import net.minecraft.item.Item;

public class ItemSpecialIngot extends Item{
	
	public ItemSpecialIngot(){
		setCreativeTab(FolkrumTabs.ITEMS);
		setRegistryName(ReferenceSC.MODID, "special_ingot");
		setUnlocalizedName("specialIngot");
	}
	
}
