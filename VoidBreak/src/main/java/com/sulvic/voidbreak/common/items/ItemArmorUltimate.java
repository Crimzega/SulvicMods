package com.sulvic.voidbreak.common.items;

import com.sulvic.core.common.FolkrumTabs;
import com.sulvic.core.util.ArmorHelper;
import com.sulvic.voidbreak.ReferenceVB;
import com.sulvic.voidbreak.common.ChromuzokObjects.EnumUltimateObject;
import com.sulvic.voidbreak.common.HorgexianMaterials;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemArmorUltimate extends ItemArmor{
	
	public ItemArmorUltimate(EnumUltimateObject ultimateObj, ArmorHelper armor){
		super(HorgexianMaterials.getUltimateArmorType(ultimateObj), 2, armor.getSlot());
		setCreativeTab(FolkrumTabs.EQUIP);
		setRegistryName(ReferenceVB.MODID, ultimateObj.getName() + "_" + armor.getName());
		setUnlocalizedName(ultimateObj.getName() + armor.getUnlocalizedName());
	}
	
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack){
		if(player.isBurning() && !player.isInLava()) player.setFire(0);
		stack.damageItem(-1, player);
	}
	
}
