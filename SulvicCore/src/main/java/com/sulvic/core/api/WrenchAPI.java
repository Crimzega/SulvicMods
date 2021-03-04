package com.sulvic.core.api;

import static com.sulvic.core.api.WrenchBlocks.*;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;

public class WrenchAPI{
	
	private static final Set<WrenchBlock> WRENCH_BLOCKS = Sets.newHashSet(GLASS, GLASS_PANE, IRON_BARS, MOB_SPAWNER, STAINED_GLASS, STAINED_GLASS_PANE);
	
	public static boolean addWrenchable(Block block){
		if(!hasWrenchable(block)) return WRENCH_BLOCKS.add(createWrenchable(block));
		else return false;
	}
	
	public static boolean hasDamage(Block block){
		if(hasWrenchable(block)) return getWrenchable(block).hasDamage();
		else return false;
	}
	
	public static boolean hasWrenchable(Block block){
		for(WrenchBlock wrenchable: WRENCH_BLOCKS) if(wrenchable.hasBlock(block)) return true;
		return false;
	}
	
	public static boolean removeWrenchable(Block block){
		if(hasWrenchable(block)) return WRENCH_BLOCKS.remove(getWrenchable(block));
		else return false;
	}
	
	public static WrenchBlock getWrenchable(Block block){
		for(WrenchBlock wrenchable: WRENCH_BLOCKS) if(wrenchable.hasBlock(block)) return wrenchable;
		return (WrenchBlock)null;
	}
	
	public static WrenchBlock.Damage getDamage(Block block){
		WrenchBlock.Damage result = getWrenchable(block).getDamage();
		return result != null? result: WrenchBlock.DEFAULT_DAMAGE;
	}
	
	public static Set<WrenchBlock> getWrenchables(){ return WRENCH_BLOCKS; }
	
	public static Set<Block> getBlocks(){
		Set<Block> blocks = Sets.newHashSet();
		for(WrenchBlock wrenchable: WRENCH_BLOCKS) blocks.add(wrenchable.getBlock());
		return blocks;
	}
	
	public static void setDamage(Block block, int min){
		if(hasWrenchable(block)) getWrenchable(block).setDamage(WrenchBlock.createDamage(min));
		else WRENCH_BLOCKS.add(createWrenchable(block, min));
	}
	
	public static void setDamage(Block block, int min, int max){
		if(hasWrenchable(block)) getWrenchable(block).setDamage(WrenchBlock.createDamage(min, max));
		else WRENCH_BLOCKS.add(createWrenchable(block, min, max));
	}
	
	public static WrenchBlock createWrenchable(Block block){ return WrenchBlock.createWrenchable(block); }
	
	public static WrenchBlock createWrenchable(Block block, int min){ return WrenchBlock.createWrenchable(block, min); }
	
	public static WrenchBlock createWrenchable(Block block, int min, int max){ return WrenchBlock.createWrenchable(block, min, max); }
	
}
