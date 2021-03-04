package com.sulvic.voidbreak.world.gen;

import java.util.Random;

import com.sulvic.core.world.gen.SulvicWorldGeneration;
import com.sulvic.voidbreak.client.ConfigVB;
import com.sulvic.voidbreak.common.ChromuzokObjects;
import com.sulvic.voidbreak.common.biomes.BiomeDeathFusion;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BlobMountainDecorator extends BiomeDecorator{
	
	private BiomeDeathFusion biomeDF;
	private ChunkPos chunkPos;
	private Random randomGenerator;
	private World currentWorld;
	
	public BlobMountainDecorator(BiomeDeathFusion biome){ biomeDF = biome; }
	
	protected void decorate(){
		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(currentWorld, randomGenerator, chunkPos));
		generateOres(currentWorld, randomGenerator);
		if(TerrainGen.decorate(currentWorld, randomGenerator, chunkPos, DecorateBiomeEvent.Decorate.EventType.TREE)) for(int i = 0; i < treesPerChunk; i++) biomeDF.getRandomTreeFeature(randomGenerator)
			.generate(currentWorld, randomGenerator, currentWorld.getHeight(chunkPos.getBlock(randomGenerator.nextInt(16) + 8, 0, randomGenerator.nextInt(16) + 8)));
		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(currentWorld, randomGenerator, chunkPos));
	}
	
	public void decorate(World world, Random random, Biome biome, BlockPos pos){
		if(decorating) throw new RuntimeException("Already decorating");
		currentWorld = world;
		randomGenerator = random;
		chunkPos = new ChunkPos(pos);
		dirtGen = new WorldGenMinable(ChromuzokObjects.DEATH_FUSION_DIRT.getDefaultState(), 47, SulvicWorldGeneration.getPredicate(ConfigVB.dfDimensionID));
		// decorateOld();
		genDecorations(biome, world, random);
		currentWorld = null;
		randomGenerator = null;
		decorating = false;
	}
	
	protected void generateOres(World worldIn, Random random){ super.generateOres(worldIn, random); }
	
}
