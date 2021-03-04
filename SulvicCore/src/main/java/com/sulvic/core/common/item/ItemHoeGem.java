package com.sulvic.core.common.item;

import java.util.Map;

import com.google.common.collect.Maps;
import com.sulvic.core.ReferenceSC;
import com.sulvic.core.client.ColorHelper.IItemColorizer;
import com.sulvic.core.common.AzurilMaterials;
import com.sulvic.core.common.FolkrumTabs;
import com.sulvic.core.common.SulvicObjects.EnumGem;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;

public class ItemHoeGem extends ItemHoe{
	
	protected static final Map<EnumGem, ItemHoeGem> GEM_HOES = Maps.newHashMap();
	private static boolean useInternalRegistry = false;
	private EnumGem gemType;
	
	public ItemHoeGem(EnumGem gem){
		super(AzurilMaterials.getToolMaterial(gem));
		setCreativeTab(FolkrumTabs.TOOLS);
		setRegistryName(ReferenceSC.MODID, gem.getName() + "_hoe");
		setUnlocalizedName(gem.getUnlocalName() + "Hoe");
		gemType = gem;
		if(useInternalRegistry) GEM_HOES.put(gem, this);
	}
	
	public static ItemHoeGem getByType(EnumGem gem){ return GEM_HOES.get(gem); }
	
	public static void init(){
		for(EnumGem gem: EnumGem.values()) GEM_HOES.put(gem, new ItemHoeGem(gem));
		useInternalRegistry = true;
	}
	
	public int getColor(){ return gemType.getColor(); }
	
	public static class Colorizer implements IItemColorizer{
		
		public IItemColor itemColor(){
			return new IItemColor(){
				
				public int colorMultiplier(ItemStack stack, int tintIndex){
					Item item = stack.getItem();
					return item instanceof ItemHoeGem && tintIndex == 1? ((ItemHoeGem)item).getColor(): DEFAULT_COLOR;
				}
				
			};
		}
		
		public Item[] items(){
			Item[] array = new Item[GEM_HOES.size()];
			for(int i = 0; i < GEM_HOES.size(); i++) array[i] = GEM_HOES.get(EnumGem.byMetadata(i));
			return array;
		}
		
	}
	
}
