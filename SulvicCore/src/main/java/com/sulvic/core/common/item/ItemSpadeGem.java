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
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

public class ItemSpadeGem extends ItemSpade{
	
	protected static final Map<EnumGem, ItemSpadeGem> GEM_SPADES = Maps.newHashMap();
	private static boolean useInternalRegistry = false;
	private EnumGem gemType;
	
	public ItemSpadeGem(EnumGem gem){
		super(AzurilMaterials.getToolMaterial(gem));
		setCreativeTab(FolkrumTabs.TOOLS);
		setRegistryName(ReferenceSC.MODID, gem.getName() + "_spade");
		setUnlocalizedName(gem.getUnlocalName() + "Spade");
		gemType = gem;
		if(useInternalRegistry) GEM_SPADES.put(gem, this);
	}
	
	public static ItemSpadeGem getByType(EnumGem gem){ return GEM_SPADES.get(gem); }
	
	public static void init(){
		for(EnumGem gem: EnumGem.values()) GEM_SPADES.put(gem, new ItemSpadeGem(gem));
		useInternalRegistry = true;
	}
	
	public int getColor(){ return gemType.getColor(); }
	
	public static class Colorizer implements IItemColorizer{
		
		public IItemColor itemColor(){
			return new IItemColor(){
				
				public int colorMultiplier(ItemStack stack, int tintIndex){
					Item item = stack.getItem();
					return item instanceof ItemSpadeGem && tintIndex == 1? ((ItemSpadeGem)item).getColor(): DEFAULT_COLOR;
				}
				
			};
		}
		
		public Item[] items(){
			Item[] array = new Item[GEM_SPADES.size()];
			for(int i = 0; i < GEM_SPADES.size(); i++) array[i] = GEM_SPADES.get(EnumGem.byMetadata(i));
			return array;
		}
		
	}
	
}
