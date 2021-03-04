package com.sulvic.core.world;

import com.sulvic.core.ReferenceSC;

import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public class BiomeFlatlands extends Biome{
	
	
	public BiomeFlatlands(){
		super(new BiomeProperties("Giant Flatlands").setBaseHeight(0.25f).setHeightVariation(0.1f).setTemperature(0.8f).setRainfall(0.45f).setWaterColor(2852863));
		setRegistryName(ReferenceSC.MODID, "flatlands");
		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		spawnableMonsterList.add(new SpawnListEntry(EntityGiantZombie.class, 8, 1, 2));
		decorator.grassPerChunk = 0;
		decorator.treesPerChunk = 0;
		decorator.flowersPerChunk = 0;
	}
	
}
