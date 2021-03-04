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
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemSwordGem extends ItemSword{
	
	protected static final Map<EnumGem, ItemSwordGem> GEM_SWORDS = Maps.newHashMap();
	private static boolean useInternalRegistry = false;
	private EnumGem gemType;
	
	public ItemSwordGem(EnumGem gem){
		super(AzurilMaterials.getToolMaterial(gem));
		setCreativeTab(FolkrumTabs.EQUIP);
		setRegistryName(ReferenceSC.MODID, gem.getName() + "_sword");
		setUnlocalizedName(gem.getUnlocalName() + "Sword");
		gemType = gem;
		if(useInternalRegistry) GEM_SWORDS.put(gem, this);
	}
	
	public static ItemSwordGem getByType(EnumGem gem){ return GEM_SWORDS.get(gem); }
	
	public static void init(){
		for(EnumGem gem: EnumGem.values()) GEM_SWORDS.put(gem, new ItemSwordGem(gem));
		useInternalRegistry = true;
	}
	
	public int getColor(){ return gemType.getColor(); }
	
	public static class Colorizer implements IItemColorizer{
		
		public IItemColor itemColor(){
			return new IItemColor(){
				
				public int colorMultiplier(ItemStack stack, int tintIndex){
					Item item = stack.getItem();
					return item instanceof ItemSwordGem && tintIndex == 1? ((ItemSwordGem)item).getColor(): DEFAULT_COLOR;
				}
				
			};
		}
		
		public Item[] items(){
			Item[] array = new Item[GEM_SWORDS.size()];
			for(int i = 0; i < GEM_SWORDS.size(); i++) array[i] = GEM_SWORDS.get(EnumGem.byMetadata(i));
			return array;
		}
		
	}
	
}
