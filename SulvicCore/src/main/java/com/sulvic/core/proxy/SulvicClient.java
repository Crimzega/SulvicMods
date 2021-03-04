package com.sulvic.core.proxy;

import com.sulvic.core.client.ColorHelper;
import com.sulvic.core.client.renders.ObjectHelper;
import com.sulvic.core.common.SulvicObjects;
import com.sulvic.core.common.SulvicObjects.EnumGem;
import com.sulvic.core.common.block.BlockGem;
import com.sulvic.core.common.block.OreGem;
import com.sulvic.core.common.item.ItemArmorGem;
import com.sulvic.core.common.item.ItemAxeGem;
import com.sulvic.core.common.item.ItemHoeGem;
import com.sulvic.core.common.item.ItemPickaxeGem;
import com.sulvic.core.common.item.ItemSpadeGem;
import com.sulvic.core.common.item.ItemSwordGem;
import com.sulvic.core.util.ArmorHelper;

import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;

public class SulvicClient extends AlvontixProxy{
	
	@AlvontixClient
	public void registerBlockColors(BlockColors colorizer){ if(!ColorHelper.hasBlockColorizer()) ColorHelper.addBlockColorizer(colorizer); }
	
	@AlvontixClient
	public void registerItemColors(ItemColors colorizer){
		if(!ColorHelper.hasItemColorizer()) ColorHelper.addItemColorizer(colorizer);
		ColorHelper.applyItemColors(new ItemSwordGem.Colorizer());
		ColorHelper.applyItemColors(new ItemSpadeGem.Colorizer());
		ColorHelper.applyItemColors(new ItemPickaxeGem.Colorizer());
		ColorHelper.applyItemColors(new ItemAxeGem.Colorizer());
		ColorHelper.applyItemColors(new ItemHoeGem.Colorizer());
		ColorHelper.applyItemColors(new ItemArmorGem.Colorizer());
	}
	
	@AlvontixClient
	public void registerModels(){
		ObjectHelper.registerBlockModels(SulvicObjects.GEM_ORES, OreGem.TYPE, SulvicObjects.EnumGem::getMetadata);
		ObjectHelper.registerBlockModel(SulvicObjects.SPECIAL_ORE);
		ObjectHelper.registerBlockModels(SulvicObjects.GEM_BLOCKS, BlockGem.TYPE, SulvicObjects.EnumGem::getMetadata);
		ObjectHelper.registerBlockModel(SulvicObjects.SPECIAL_BLOCK);
		ObjectHelper.registerBlockModel(SulvicObjects.TANK);
		ObjectHelper.registerBlockModel(SulvicObjects.SPECIAL_WORKBENCH);
		ObjectHelper.registerItemModels(SulvicObjects.GEMS, SulvicObjects.EnumGem.values());
		ObjectHelper.registerItemModel(SulvicObjects.SPECIAL_DUST);
		ObjectHelper.registerItemModel(SulvicObjects.SPECIAL_INGOT);
		ObjectHelper.registerItemModel(SulvicObjects.SWEET_POTATO);
		ObjectHelper.registerItemModel(SulvicObjects.WRENCH);
		for(EnumGem gem: EnumGem.values()){
			ObjectHelper.registerItemModel(ItemSwordGem.getByType(gem));
			ObjectHelper.registerItemModel(ItemSpadeGem.getByType(gem));
			ObjectHelper.registerItemModel(ItemPickaxeGem.getByType(gem));
			ObjectHelper.registerItemModel(ItemAxeGem.getByType(gem));
			ObjectHelper.registerItemModel(ItemHoeGem.getByType(gem));
			for(ArmorHelper armor: ArmorHelper.values()) ObjectHelper.registerItemModel(ItemArmorGem.getByType(gem, armor));
		}
	}
	
}
