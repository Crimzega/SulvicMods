package com.sulvic.core.util;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.EnumHelper;

import com.sulvic.core.common.SulvicObjects;

public class ModEnumHelper{
	
	public static <T extends Enum<T> & IStringSerializable> PropertyEnum<T> createEnumProperty(String name, Class<T> enumClass){ return PropertyEnum.create(name, enumClass); }
	
	public static PropertyEnum<SulvicObjects.EnumGem> gemProperty(){ return createEnumProperty("type", SulvicObjects.EnumGem.class); }
	
	public static ToolMaterial buildWrenchMat(){ return EnumHelper.addToolMaterial("WRENCH", 0, 416, 1.5f, 2.25f, 7); }
	
	public static <T extends Enum<?>> T createNewEnum(String name, Class<T> enumClass, Object... values){
		Class<?>[] valueTypes = new Class<?>[values.length];
		for(int i = 0; i < values.length; i++) valueTypes[i] = values[i].getClass();
		return EnumHelper.addEnum(enumClass, name, valueTypes, values);
	}
	
	public static ArmorMaterial createArmorMaterial(String name, String texture, int durability, int[] strengths, int enchantability, SoundEvent sound, float strength){
		return EnumHelper.addArmorMaterial(name, texture, durability, strengths, enchantability, sound, strength);
	}
	
	public static ArmorMaterial createArmorMaterial(String name, String texture, int durability, int[] strengths, int enchantability, SoundEvent sound, float strength, ItemStack repairStack){
		return createArmorMaterial(name, texture, durability, strengths, enchantability, sound, strength).setRepairItem(repairStack);
	}
	
	public static ToolMaterial createToolMaterial(String name, int harvestLvl, int uses, float efficiency, float damage, int enchantability){
		return EnumHelper.addToolMaterial(name, harvestLvl, uses, efficiency, damage, enchantability);
	}
	
	public static ToolMaterial createToolMaterial(String name, int harvestLvl, int uses, float efficiency, float damage, int enchantability, ItemStack repairStack){
		return createToolMaterial(name, harvestLvl, uses, efficiency, damage, enchantability).setRepairItem(repairStack);
	}
	
}
