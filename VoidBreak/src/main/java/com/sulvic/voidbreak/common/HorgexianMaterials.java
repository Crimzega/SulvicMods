package com.sulvic.voidbreak.common;

import com.sulvic.core.util.ModEnumHelper;
import com.sulvic.voidbreak.common.ChromuzokObjects.EnumUltimateObject;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor.ArmorMaterial;

public class HorgexianMaterials{
	
	private static final int CORE = 0xFFFF;
	
	public static final ArmorMaterial ULTIMANIUM_ARMOR = getUltimateArmor(EnumUltimateObject.ULTIMANIUM);
	public static final ArmorMaterial ULTIMEX_ARMOR = getUltimateArmor(EnumUltimateObject.ULTIMEX);
	public static final ArmorMaterial ULTEZ_ARMOR = getUltimateArmor(EnumUltimateObject.ULTEZ);
	
	private static int[] armorStr(int... values){ return values; }
	
	private static ArmorMaterial getUltimateArmor(EnumUltimateObject ultimateObj){
		return ModEnumHelper.createArmorMaterial(ultimateObj.name(), "voidbreak:textures/armor/" + ultimateObj.getName(), 1647000 * ultimateObj.getMultiplier(), armorStr(CORE, CORE, CORE, CORE), 2,
			SoundEvents.ITEM_ARMOR_EQUIP_IRON, 8000f);
	}
	
	public static ArmorMaterial getUltimateArmorType(EnumUltimateObject ultimateObj){
		switch(ultimateObj){
			case ULTIMANIUM:
				return ULTIMANIUM_ARMOR;
			case ULTIMEX:
				return ULTIMEX_ARMOR;
			case ULTEZ:
				return ULTEZ_ARMOR;
			default:
				return ULTIMANIUM_ARMOR;
		}
	}
	
	public static void addRepairStacks(){
		
	}
	
}
