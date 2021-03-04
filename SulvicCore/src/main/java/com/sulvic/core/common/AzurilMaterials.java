package com.sulvic.core.common;

import java.util.Map;

import com.google.common.collect.Maps;
import com.sulvic.core.common.SulvicObjects.EnumGem;
import com.sulvic.core.common.item.ItemArmorGem;
import com.sulvic.core.util.ModEnumHelper;
import com.sulvic.lib.DoubleValueBasic;
import com.sulvic.lib.DoubleValueSet;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;

public class AzurilMaterials{
	
	private static final Map<EnumGem, DoubleValueSet<ToolMaterial, ArmorMaterial>> GEM_MATERIALS = Maps.newHashMap();
	
	static{
		for(EnumGem gem: EnumGem.values()){
			ToolMaterial toolMat = ModEnumHelper.createToolMaterial(gem.getName(), 3, 1561, 8f, 3f, 10);
			ArmorMaterial armorMat = ModEnumHelper.createArmorMaterial(gem.getName(), ItemArmorGem.ARMOR_PATH, 33, new int[]{3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2f);
			GEM_MATERIALS.put(gem, new DoubleValueBasic<ToolMaterial, ArmorMaterial>(toolMat, armorMat));
		}
	}
	
	public static ArmorMaterial getArmorMaterial(EnumGem gem){ return GEM_MATERIALS.get(gem).getSecondValue(); }
	
	public static ToolMaterial getToolMaterial(EnumGem gem){ return GEM_MATERIALS.get(gem).getMainValue(); }
	
	public static void setRepairStack(EnumGem gem){
		ItemStack repairStack = new ItemStack(SulvicObjects.GEMS, 1, gem.getMetadata());
		GEM_MATERIALS.get(gem).getMainValue().setRepairItem(repairStack);
		GEM_MATERIALS.get(gem).getSecondValue().setRepairItem(repairStack);
	}
	
}
