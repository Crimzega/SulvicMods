package com.sulvic.core.zenscript;

import static com.sulvic.core.api.DetectAPI.*;

import java.util.List;

import com.google.common.collect.Lists;
import com.sulvic.core.api.DetectAPI;

import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.block.IBlock;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.block.Block;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.sulvic.Detector")
@ZenRegister
public class ZenDetector{
	
	private static List<IBlock> getDetectableOres(){
		List<IBlock> list = Lists.newArrayList();
		for(Block block: DetectAPI.getDetectableOres()) list.add(CraftTweakerMC.getBlockAnyMeta(block));
		return list;
	}
	
	@ZenMethod
	public static List<IBlock> getOres(){ return getDetectableOres(); }
	
	@ZenMethod
	public static void addOre(IBlock block){}
	
	@ZenMethod
	public static void removeOre(IBlock block){}
	
	public static class AddOreAction implements IAction{
		
		private final IBlock chosenOre;
		
		public AddOreAction(IBlock ore){ chosenOre = ore; }
		
		public String describe(){ return "Adding an ore to the detector"; }
		
		public void apply(){
			Block block = CraftTweakerMC.getBlock(chosenOre);
			if(!containsOre(block)) addDetectableOre(block);
		}
		
	}
	
	public static class RemoveOreAction implements IAction{
		
		private final IBlock chosenOre;
		
		public RemoveOreAction(IBlock ore){ chosenOre = ore; }
		
		public String describe(){ return "Removing an ore from the detector"; }
		
		public void apply(){
			Block block = CraftTweakerMC.getBlock(chosenOre);
			if(containsOre(block)) removeDetectableOre(block);
		}
		
	}
	
}
