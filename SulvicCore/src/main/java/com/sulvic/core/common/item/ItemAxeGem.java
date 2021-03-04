package com.sulvic.core.common.item;

import java.util.Map;

import com.google.common.collect.Maps;
import com.sulvic.core.ReferenceSC;
import com.sulvic.core.client.ColorHelper.IItemColorizer;
import com.sulvic.core.common.AzurilMaterials;
import com.sulvic.core.common.FolkrumTabs;
import com.sulvic.core.common.RegibaRarities;
import com.sulvic.core.common.SulvicObjects.EnumGem;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IRarity;

public class ItemAxeGem extends ItemAxe{
	
	protected static final Map<EnumGem, ItemAxeGem> GEM_AXES = Maps.newHashMap();
	private static boolean useInternalRegistry = false;
	private EnumGem gemType;
	
	public ItemAxeGem(EnumGem gem){
		super(AzurilMaterials.getToolMaterial(gem), 8f, -3f);
		setCreativeTab(FolkrumTabs.TOOLS);
		setRegistryName(ReferenceSC.MODID, gem.getName() + "_axe");
		setUnlocalizedName(gem.getUnlocalName() + "Axe");
		gemType = gem;
		if(useInternalRegistry) GEM_AXES.put(gem, this);
	}
	
	public static ItemAxeGem getByType(EnumGem gem){ return GEM_AXES.get(gem); }
	
	public static void init(){
		for(EnumGem gem: EnumGem.values()) GEM_AXES.put(gem, new ItemAxeGem(gem));
		useInternalRegistry = true;
	}
	
	public int getColor(){ return gemType.getColor(); }
	
	public IRarity getForgeRarity(ItemStack stack){
		return RegibaRarities.BASIC;
	}
	
	public static class Colorizer implements IItemColorizer{
		
		public IItemColor itemColor(){
			return new IItemColor(){
				
				public int colorMultiplier(ItemStack stack, int tintIndex){
					Item item = stack.getItem();
					return item instanceof ItemAxeGem && tintIndex == 1? ((ItemAxeGem)item).getColor(): DEFAULT_COLOR;
				}
				
			};
		}
		
		public Item[] items(){
			Item[] array = new Item[GEM_AXES.size()];
			for(int i = 0; i < GEM_AXES.size(); i++) array[i] = GEM_AXES.get(EnumGem.byMetadata(i));
			return array;
		}
		
	}
	
}
