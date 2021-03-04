package com.sulvic.core.common.item;

import com.sulvic.core.ReferenceSC;
import com.sulvic.core.common.FolkrumTabs;
import com.sulvic.core.common.SulvicObjects;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeedFood;

public class ItemSweetPotato extends ItemSeedFood{
	
	public ItemSweetPotato(){
		super(2, 0.45f, SulvicObjects.SWEET_POTATOES, Blocks.FARMLAND);
		setCreativeTab(FolkrumTabs.FOOD);
		setRegistryName(ReferenceSC.MODID, "sweet_potato");
		setUnlocalizedName("sweetPotato");
	}
	
}
