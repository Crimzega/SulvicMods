package com.sulvic.voidbreak.handler;

import com.sulvic.voidbreak.VoidBreak;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;

@EventBusSubscriber
public class RenderHandler{
	
	private static boolean lastFancyGraphics;
	
	@SubscribeEvent
	public static void onChangedGraphicsSettings(RenderTickEvent evt){
		boolean fancyGraphics = Minecraft.getMinecraft().gameSettings.fancyGraphics;
		if(lastFancyGraphics != fancyGraphics){
			VoidBreak.modProxy.setFancyGraphics(fancyGraphics);
			lastFancyGraphics = fancyGraphics;
		}
	}
	
}
