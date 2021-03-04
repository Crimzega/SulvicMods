package com.sulvic.voidbreak.world.provider;

import javax.annotation.Nullable;

import com.sulvic.core.proxy.AlvontixClient;
import com.sulvic.voidbreak.common.ChromuzokObjects;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.border.WorldBorder;
import net.minecraftforge.client.IRenderHandler;

public class WorldProviderDeathFusion extends WorldProvider{
	
	private static final double MOVEMENT_FACTOR = 2d;
	
	public WorldProviderDeathFusion(){}
	
	public boolean canRespawnHere(){ return false; }
	
	public float calculateCelestialAngle(long worldTime, float partialTicks){ return -0.5f; }
	
	public DimensionType getDimensionType(){ return ChromuzokObjects.dimDeathFusion; }
	
	public double getMovementFactor(){ return MOVEMENT_FACTOR; }
	
	@Nullable
	@AlvontixClient
	public IRenderHandler getSkyRenderer(){ return null; }
	
	@Nullable
	@AlvontixClient
	public IRenderHandler getWeatherRenderer(){ return null; }
	
	public WorldBorder createWorldBorder(){
		return new WorldBorder(){
			
			public double getCenterX(){ return super.getCenterX() / MOVEMENT_FACTOR; }
			
			public double getCenterZ(){ return super.getCenterZ() / MOVEMENT_FACTOR; }
			
		};
	}
	
}
