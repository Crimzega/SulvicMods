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
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class ItemPickaxeGem extends ItemPickaxe{
	
	protected static final Map<EnumGem, ItemPickaxeGem> GEM_PICKAXES = Maps.newHashMap();
	private static boolean useInternalRegistry = false;
	private EnumGem gemType;
	
	public ItemPickaxeGem(EnumGem gem){
		super(AzurilMaterials.getToolMaterial(gem));
		setCreativeTab(FolkrumTabs.TOOLS);
		setRegistryName(ReferenceSC.MODID, gem.getName() + "_pickaxe");
		setUnlocalizedName(gem.getUnlocalName() + "Pickaxe");
		gemType = gem;
		if(useInternalRegistry) GEM_PICKAXES.put(gem, this);
	}
	
	public static ItemPickaxeGem getByType(EnumGem gem){ return GEM_PICKAXES.get(gem); }
	
	public static void init(){
		for(EnumGem gem: EnumGem.values()) GEM_PICKAXES.put(gem, new ItemPickaxeGem(gem));
		useInternalRegistry = true;
	}
	
	public int getColor(){ return gemType.getColor(); }
	
	public static class Colorizer implements IItemColorizer{
		
		public IItemColor itemColor(){
			return new IItemColor(){
				
				public int colorMultiplier(ItemStack stack, int tintIndex){
					Item item = stack.getItem();
					return item instanceof ItemPickaxeGem && tintIndex == 1? ((ItemPickaxeGem)item).getColor(): DEFAULT_COLOR;
				}
				
			};
		}
		
		public Item[] items(){
			Item[] array = new Item[GEM_PICKAXES.size()];
			for(int i = 0; i < GEM_PICKAXES.size(); i++) array[i] = GEM_PICKAXES.get(EnumGem.byMetadata(i));
			return array;
		}
		
	}
	
}
