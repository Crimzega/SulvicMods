package com.sulvic.voidbreak.world.gen;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import com.sulvic.voidbreak.common.ChromuzokObjects;

public class WorldGenDeathFusionTree extends WorldGenAbstractTree{
	
	private static final IBlockState LOG = ChromuzokObjects.DEATH_FUSION_LOG.getDefaultState();
	private static final IBlockState LEAVES = ChromuzokObjects.DEATH_FUSION_LEAVES.getDefaultState();
	
	public WorldGenDeathFusionTree(boolean notify){ super(notify); }
	
	private boolean canGenerateAtPos(World world, BlockPos pos){
		for(int y = 0; y < 8; y++) for(int x = -2; x < 2; x++) for(int z = -2; z < 2; z++){
			BlockPos pos1 = pos.add(x, y, z);
			if(world.getBlockState(pos1) != Blocks.AIR.getDefaultState()) return false;
		}
		return true;
	}
	
	private boolean generateLeavesAt(World world, BlockPos pos){
		if(world.getBlockState(pos) != Blocks.AIR.getDefaultState()) return false;
		world.notifyBlockUpdate(pos, world.getBlockState(pos), LEAVES, 3);
		return true;
	}
	
	public boolean generate(World world, Random rand, BlockPos pos){
		if(canGenerateAtPos(world, pos)){
			for(int y = 0; y < 8; y++){
				BlockPos pos1 = pos.up(y);
				if(y < 8) world.notifyBlockUpdate(pos1, world.getBlockState(pos1), LOG, 3);
				if(y == 3){
					generateLeavesAt(world, pos1.north());
					generateLeavesAt(world, pos1.east());
					generateLeavesAt(world, pos1.south());
					generateLeavesAt(world, pos1.west());
				}
				if(y > 3 && y < 7) for(int x = -2; x < 2; x++) for(int z = -2; z < 2; z++){
					BlockPos pos2 = pos1.add(x, 0, z);
					if(!pos2.equals(pos1)) generateLeavesAt(world, pos2);
				}
				if(y >= 7) for(int x = -1; x < 1; x++) for(int z = -1; z < 1; z++){
					BlockPos pos2 = pos1.add(x, 0, z);
					BlockPos north = pos2.north(), south = pos2.south();
					generateLeavesAt(world, pos1.north());
					generateLeavesAt(world, pos1.east());
					generateLeavesAt(world, pos1.south());
					generateLeavesAt(world, pos1.west());
					if(y != 8){
						if(rand.nextBoolean()) generateLeavesAt(world, north.east());
						if(rand.nextBoolean()) generateLeavesAt(world, north.west());
						if(rand.nextBoolean()) generateLeavesAt(world, south.east());
						if(rand.nextBoolean()) generateLeavesAt(world, south.west());
					}
				}
			}
			return true;
		}
		else return false;
	}
	
}
