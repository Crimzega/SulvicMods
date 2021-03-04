package com.sulvic.core.common.item;

import java.util.Map;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StringUtils;
import net.minecraftforge.common.IRarity;

import com.google.common.collect.Maps;
import com.sulvic.core.ReferenceSC;
import com.sulvic.core.client.ColorHelper.IItemColorizer;
import com.sulvic.core.common.AzurilMaterials;
import com.sulvic.core.common.FolkrumTabs;
import com.sulvic.core.common.RegibaRarities;
import com.sulvic.core.common.SulvicObjects.EnumGem;
import com.sulvic.core.util.ArmorHelper;
import com.sulvic.lib.DoubleKeyBasic;
import com.sulvic.lib.DoubleKeySet;

public class ItemArmorGem extends ItemArmor{
	
	private static final Map<DoubleKeySet<EnumGem, ArmorHelper>, ItemArmorGem> GEM_ARMOR = Maps.newHashMap();
	public static final String ARMOR_PATH = ReferenceSC.MODID + ":textures/armor/colored/base_layer";
	private static boolean useInternalRegistry = false;
	private EnumGem gemType;
	private ArmorHelper armorType;
	
	public ItemArmorGem(EnumGem gem, ArmorHelper armor){
		super(AzurilMaterials.getArmorMaterial(gem), 3, armor.getSlot());
		setCreativeTab(FolkrumTabs.EQUIP);
		setRegistryName(ReferenceSC.MODID, gem.getName() + "_" + armor.getName());
		setUnlocalizedName(gem.getUnlocalName() + armor.getUnlocalizedName());
		gemType = gem;
		armorType = armor;
		if(useInternalRegistry) GEM_ARMOR.put(new DoubleKeyBasic<EnumGem, ArmorHelper>(gem, armor), this);
	}
	
	public static ItemArmorGem getByType(EnumGem gem, ArmorHelper armor){ return DoubleKeySet.getMapObject(GEM_ARMOR, gem, armor); }
	
	public static void init(){
		for(EnumGem gem: EnumGem.values()) for(ArmorHelper armor: ArmorHelper.values()) GEM_ARMOR.put(new DoubleKeyBasic<EnumGem, ArmorHelper>(gem, armor), new ItemArmorGem(gem, armor));
		useInternalRegistry = true;
	}
	
	public boolean hasColor(ItemStack stack){ return true; }
	
	public boolean hasOverlay(ItemStack stack){ return true; }
	
	public int getColor(ItemStack stack){
		NBTTagCompound nbtCompound = stack.getTagCompound();
		if(nbtCompound != null){
			NBTTagCompound displayCompound = nbtCompound.getCompoundTag("display");
			if(displayCompound != null) displayCompound.setInteger("color", gemType.getColor());
		}
		return gemType.getColor();
	}
	
	public IRarity getForgeRarity(ItemStack stack){ return RegibaRarities.BASIC; }
	
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type){
		return ARMOR_PATH + (armorType.hasName("LEGGINGS")? 2: 1) + (!StringUtils.isNullOrEmpty(type)? "": "_glint") + ".png";
	}
	
	public static class Colorizer implements IItemColorizer{
		
		public IItemColor itemColor(){
			return new IItemColor(){
				
				public int colorMultiplier(ItemStack stack, int index){
					Item item = stack.getItem();
					return item instanceof ItemArmorGem && index == 0? ((ItemArmorGem)item).getColor(stack): DEFAULT_COLOR;
				}
				
			};
		}
		
		public Item[] items(){
			Item[] array = new Item[GEM_ARMOR.size()];
			int i = 0;
			for(ItemArmorGem armor: GEM_ARMOR.values()){
				array[i] = armor;
				i++;
			}
			return array;
		}
		
	}
	
}
