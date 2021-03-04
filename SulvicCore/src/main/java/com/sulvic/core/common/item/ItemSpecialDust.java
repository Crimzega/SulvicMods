package com.sulvic.core.common.item;

import com.sulvic.core.ReferenceSC;
import com.sulvic.core.common.FolkrumTabs;

import net.minecraft.item.Item;

public class ItemSpecialDust extends Item{
	
	public ItemSpecialDust(){
		setCreativeTab(FolkrumTabs.ITEMS);
		setRegistryName(ReferenceSC.MODID, "special_dust");
		setUnlocalizedName("specialDust");
	}
	
}
