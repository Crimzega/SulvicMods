package com.sulvic.core.api;

import static com.sulvic.core.common.SulvicObjects.*;
import static net.minecraft.init.Blocks.*;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;

public class DetectAPI{
	
	private static final Set<Block> ORES = Sets.newHashSet(COAL_ORE, IRON_ORE, GOLD_ORE, LAPIS_ORE, DIAMOND_ORE, EMERALD_ORE, REDSTONE_ORE, LIT_REDSTONE_ORE, QUARTZ_ORE, GEM_ORES);
	
	public static boolean containsOre(Block block){ return ORES.contains(block); }
	
	public static Set<Block> getDetectableOres(){ return ORES; }
	
	public static void addDetectableOre(Block block){ if(!containsOre(block)) ORES.add(block); }
	
	public static void removeDetectableOre(Block block){ if(containsOre(block)) ORES.remove(block); }
	
}
