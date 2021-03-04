package com.sulvic.voidbreak.common;

import com.sulvic.voidbreak.client.ConfigVB;
import com.sulvic.voidbreak.common.biomes.*;
import com.sulvic.voidbreak.common.blocks.*;
import com.sulvic.voidbreak.lib.JekouvuRegistry;
import com.sulvic.voidbreak.world.provider.WorldProviderDeathFusion;

import net.minecraft.util.IStringSerializable;
import net.minecraft.world.DimensionType;

public class ChromuzokObjects{
	
	public static DimensionType dimDeathFusion = DimensionType.register("DEATH_FUSION", "_df", ConfigVB.dfDimensionID, WorldProviderDeathFusion.class, false);
	public static final BlockUltimate ULTIMANIUM_BLOCK = new BlockUltimate(EnumUltimateObject.ULTIMANIUM);
	public static final BlockUltimate ULTIMEX_BLOCK = new BlockUltimate(EnumUltimateObject.ULTIMEX);
	public static final BlockUltimate ULTEZ_BLOCK = new BlockUltimate(EnumUltimateObject.ULTEZ);
	public static final BlockDeathFusionGrass DEATH_FUSION_GRASS = new BlockDeathFusionGrass();
	public static final BlockDeathFusionDirt DEATH_FUSION_DIRT = new BlockDeathFusionDirt();
	public static final BlockDeathFusionStone DEATH_FUSION_STONE = new BlockDeathFusionStone();
	public static final BlockDeathFusionSapling DEATH_FUSION_SAPLING = new BlockDeathFusionSapling();
	public static final BlockDeathFusionLog DEATH_FUSION_LOG = new BlockDeathFusionLog();
	public static final BlockDeathFusionLeaves DEATH_FUSION_LEAVES = new BlockDeathFusionLeaves();
	public static final BiomeDeathFusion DEATH_FUSION_BIOME = new BiomeDeathFusion();
	
	public static void addToRegistry(){
		JekouvuRegistry.addBlock(ULTIMANIUM_BLOCK);
		JekouvuRegistry.addBlock(ULTIMEX_BLOCK);
		JekouvuRegistry.addBlock(ULTEZ_BLOCK);
		JekouvuRegistry.addBlock(DEATH_FUSION_GRASS);
		JekouvuRegistry.addBlock(DEATH_FUSION_DIRT);
		JekouvuRegistry.addBlock(DEATH_FUSION_STONE);
		JekouvuRegistry.addBlock(DEATH_FUSION_SAPLING);
		JekouvuRegistry.addBlock(DEATH_FUSION_LOG);
		JekouvuRegistry.addBlock(DEATH_FUSION_LEAVES);
		JekouvuRegistry.addBiome(DEATH_FUSION_BIOME);
	}
	
	public static enum EnumUltimateObject implements IStringSerializable{
		
		ULTIMANIUM(2, 2, 8),
		ULTIMEX(4, 4, 16),
		ULTEZ(8, 8, 64);
		
		int burnMultiplier, cookMultiplier, multiplier;
		
		EnumUltimateObject(int mult, int cook, int burn){
			multiplier = mult;
			cookMultiplier = cook;
			burnMultiplier = burn;
		}
		
		public String getName(){ return name().toLowerCase(); }
		
		public int getBurnMultiplier(){ return burnMultiplier; }
		
		public int getCookMultiplier(){ return burnMultiplier; }
		
		public int getMultiplier(){ return multiplier; }
		
	}
	
}
