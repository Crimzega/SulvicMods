package com.sulvic.core.handler;

import com.sulvic.core.ReferenceSC;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = ReferenceSC.MODID)
public class GhostStorage{
	
	@SubscribeEvent
	public static void onPlayerDeath(LivingDeathEvent evt){
		Entity entity = evt.getEntity();
		if(entity instanceof EntityPlayer){
			
			
		}
	}
	
}
