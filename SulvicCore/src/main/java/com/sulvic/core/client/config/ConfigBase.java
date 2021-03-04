package com.sulvic.core.client.config;

import com.sulvic.core.ReferenceSC;

import net.minecraftforge.common.config.Config;

@Config(modid = ReferenceSC.MODID, name = "sulvic/base", category = "sulvic")
public class ConfigBase{
	
	public static boolean allowEnchantedRecipes = false, spawnGiants = true;
	public static double giantRenderDistance = 96d;
	
}
