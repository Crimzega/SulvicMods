package com.sulvic.voidbreak.common.biomes;

import java.util.Random;

import com.sulvic.voidbreak.ReferenceVB;
import com.sulvic.voidbreak.common.ChromuzokObjects;
import com.sulvic.voidbreak.world.gen.BlobMountainDecorator;
import com.sulvic.voidbreak.world.gen.WorldGenDeathFusionTree;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeDeathFusion extends Biome{
	
	public BiomeDeathFusion(){
		super(new BiomeProperties("Death Fusion").setBaseHeight(0.3f).setHeightVariation(0.5f).setRainDisabled());
		setRegistryName(ReferenceVB.MODID, "death_fusion");
		topBlock = ChromuzokObjects.DEATH_FUSION_GRASS.getDefaultState();
		fillerBlock = ChromuzokObjects.DEATH_FUSION_DIRT.getDefaultState();
		decorator.treesPerChunk = 4;
		spawnableCaveCreatureList.clear();
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		spawnableWaterCreatureList.clear();
	}
	
	public BiomeDecorator createBiomeDecorator(){ return new BlobMountainDecorator(this); }
	
	public void decorate(World world, Random rand, BlockPos pos){ decorator.decorate(world, rand, this, pos); }
	
	public WorldGenAbstractTree getRandomTreeFeature(Random rand){ return new WorldGenDeathFusionTree(false); }
	
}
