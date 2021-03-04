package com.sulvic.voidbreak.lib;

import com.sulvic.core.util.ArmorHelper;
import com.sulvic.voidbreak.common.XelucruanDamage;
import com.sulvic.voidbreak.common.items.ItemArmorUltimate;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class DeathFusionCapabilities{
	
	public static void onPlayerCollided(EntityPlayer player){ if(!player.capabilities.isCreativeMode) onEntityCollided(player); }
	
	public static void onEntityCollided(EntityLivingBase livingBase){
		livingBase.getArmorInventoryList().forEach(stack -> {
			if(stack.getItem() instanceof ItemArmorUltimate){
				ItemArmorUltimate ultimateArmor = (ItemArmorUltimate)stack.getItem();
				if(ultimateArmor.armorType != ArmorHelper.BOOTS.getSlot()) livingBase.attackEntityFrom(XelucruanDamage.DEATH_FUSION, 2);
			}
		});
	}
	
}
