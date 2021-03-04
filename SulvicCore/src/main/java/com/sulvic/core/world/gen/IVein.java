package com.sulvic.core.world.gen;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.Biome;

public interface IVein{
	
	Biome[] allowedBiomes();
	
	IBlockState getBlockState();
	
	int getDimensionId();
	
	VeinInfo getVeinInfo();
	
}
