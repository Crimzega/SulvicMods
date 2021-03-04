package com.sulvic.core.world.gen;

import java.util.Map;
import java.util.Random;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class SulvicWorldGeneration implements IWorldGenerator{
	
	private static final Map<Integer, Predicate<IBlockState>> PREDICATES = Maps.newHashMap();
	private IVein selectedVein;
	
	private SulvicWorldGeneration(IVein vein){ selectedVein = vein; }
	
	static{
		PREDICATES.put(-1, new NetherrackPredicate());
		PREDICATES.put(0, null);
		PREDICATES.put(1, new EndStonePredicate());
	}
	
	public static SulvicWorldGeneration create(IVein vein){ return new SulvicWorldGeneration(vein); }
	
	public static void addPredicate(int dimId, Predicate<IBlockState> statePredicate){ if(!PREDICATES.containsKey(dimId)) PREDICATES.put(dimId, statePredicate); }
	
	public static void setPredicate(int dimId, Predicate<IBlockState> statePredicate){ PREDICATES.put(dimId, statePredicate); }
	
	public static Predicate<IBlockState> getPredicate(int dimId){ return PREDICATES.get(dimId); }
	
	private boolean biomeIsAllowed(Biome[] allowed, Biome biome){
		if(allowed.length > 0){
			for(Biome biome1: allowed) if(biome.equals(biome1)) return true;
			return false;
		}
		else return true;
	}
	
	private void spawnOre(World world, Random random, int chunkX, int chunkZ, Predicate<IBlockState> predicate){
		VeinInfo info = selectedVein.getVeinInfo();
		for(int i = 0; i < info.veinChance; i++){
			BlockPos pos = new BlockPos(chunkX + random.nextInt(16), info.getRandY(random), chunkZ + random.nextInt(16));
			WorldGenMinable generator = predicate != null? new WorldGenMinable(selectedVein.getBlockState(), info.getRandSize(random), predicate):
				new WorldGenMinable(selectedVein.getBlockState(), info.getRandSize(random));
			if(biomeIsAllowed(selectedVein.allowedBiomes(), world.getBiome(pos))) generator.generate(world, random, pos);
		}
	}
	
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider){
		int dimID = selectedVein.getDimensionId();
		if(world.provider.getDimension() == dimID) spawnOre(world, random, chunkX * 16, chunkZ * 16, getPredicate(dimID));
	}
	
	static class EndStonePredicate implements Predicate<IBlockState>{
		
		private EndStonePredicate(){}
		
		public boolean apply(IBlockState input){ return input != null && input.getBlock() == Blocks.END_STONE; }
		
	}
	
	static class NetherrackPredicate implements Predicate<IBlockState>{
		
		private NetherrackPredicate(){}
		
		public boolean apply(IBlockState input){ return input != null && input.getBlock() == Blocks.NETHERRACK; }
		
	}
	
}
