package com.sulvic.core.proxy;

import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;

public class AlvontixProxy{
	
	@AlvontixClient
	public void registerBlockColors(BlockColors colorizer){}
	
	@AlvontixClient
	public void registerItemColors(ItemColors colorizer){}
	
	@AlvontixClient
	public void registerModels(){}
	
	@AlvontixClient
	public void setFancyGraphics(boolean fancy){}
	
}
