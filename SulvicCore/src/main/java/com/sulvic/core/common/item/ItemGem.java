package com.sulvic.core.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.IRarity;

import com.sulvic.core.ReferenceSC;
import com.sulvic.core.common.FolkrumTabs;
import com.sulvic.core.common.RegibaRarities;
import com.sulvic.core.common.SulvicObjects;
import com.sulvic.core.common.SulvicObjects.EnumGem;

public class ItemGem extends Item{
	
	public ItemGem(){
		setCreativeTab(FolkrumTabs.ITEMS);
		setHasSubtypes(true);
		setRegistryName(ReferenceSC.MODID, "gem");
		setUnlocalizedName("gem");
	}
	
	public int getMetadata(int metadata){ return metadata; }
	
	public IRarity getForgeRarity(){ return RegibaRarities.BASIC; }
	
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items){ if(tab.equals(FolkrumTabs.ITEMS)) for(int i = 0; i < EnumGem.size(); i++) items.add(new ItemStack(this, 1, i)); }
	
	public String getUnlocalizedName(ItemStack stack){
		int metadata = stack.getItemDamage();
		if(metadata < 0 || metadata > SulvicObjects.EnumGem.size()) metadata = 0;
		return super.getUnlocalizedName() + '.' + SulvicObjects.EnumGem.byMetadata(metadata).getUnlocalName();
	}
	
}
