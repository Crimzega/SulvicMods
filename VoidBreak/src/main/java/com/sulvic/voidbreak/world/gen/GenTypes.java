package com.sulvic.voidbreak.world.gen;

import com.google.common.base.Predicate;
import com.sulvic.core.world.gen.SulvicWorldGeneration;
import com.sulvic.voidbreak.client.ConfigVB;
import com.sulvic.voidbreak.common.ChromuzokObjects;

import net.minecraft.block.state.IBlockState;

public class GenTypes{
	
	public static void init(){ SulvicWorldGeneration.addPredicate(ConfigVB.dfDimensionID, new DeathFusionPredicate()); }
	
	static class DeathFusionPredicate implements Predicate<IBlockState>{
		
		public boolean apply(IBlockState input){ return input != null && input == ChromuzokObjects.DEATH_FUSION_STONE.getDefaultState(); }
		
	}
	
}
