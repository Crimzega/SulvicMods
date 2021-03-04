package com.sulvic.core.common.item;

import com.sulvic.core.common.RegibaRarities;
import com.sulvic.core.common.SulvicObjects;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IRarity;

public class ItemBlockGem extends ItemBlock{
	
	public ItemBlockGem(Block block){
		super(block);
		setHasSubtypes(true);
		setRegistryName(block.getRegistryName());
	}
	
	@Override
	public int getMetadata(int metadata){ return metadata; }
	
	public IRarity getForgeRarity(ItemStack stack){ return RegibaRarities.BASIC; }
	
	public String getUnlocalizedName(ItemStack stack){
		int metadata = stack.getItemDamage();
		if(metadata < 0 || metadata > SulvicObjects.EnumGem.size()) metadata = 0;
		return super.getUnlocalizedName() + '.' + SulvicObjects.EnumGem.byMetadata(metadata).getUnlocalName();
	}
	
}
