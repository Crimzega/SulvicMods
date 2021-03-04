package com.sulvic.core.zenscript;

import static com.sulvic.core.api.WrenchAPI.*;

import java.util.List;

import com.google.common.collect.Lists;
import com.sulvic.core.api.WrenchAPI;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.block.IBlock;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.block.Block;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.sulvic.Wrench")
@ZenRegister
public class ZenWrench{
	
	private static List<IBlock> getZenWrenchables(){
		List<IBlock> list = Lists.newArrayList();
		for(Block block: WrenchAPI.getBlocks()) list.add(CraftTweakerMC.getBlockAnyMeta(block));
		return list;
	}
	
	@ZenMethod("getBlocks")
	public List<IBlock> getWrenchables(){ return getZenWrenchables(); }
	
	@ZenMethod("addBlock")
	public void addWrenchableBlock(IItemStack block){ CraftTweakerAPI.apply(new AddBlockAction(block)); }
	
	@ZenMethod("addBlock")
	public void addWrenchableBlock(IItemStack block, int min){ CraftTweakerAPI.apply(new AddBlockAction(block, min)); }
	
	@ZenMethod("addBlock")
	public void addWrenchableBlock(IItemStack block, int min, int max){ CraftTweakerAPI.apply(new AddBlockAction(block, min, max)); }
	
	@ZenMethod("changeDamage")
	public void changeWrenchDamage(IItemStack block, int min){ CraftTweakerAPI.apply(new ChangeDamageAction(block, min)); }
	
	@ZenMethod("changeDamage")
	public void changeWrenchDamage(IItemStack block, int min, int max){ CraftTweakerAPI.apply(new ChangeDamageAction(block, min, max)); }
	
	@ZenMethod("removeBlock")
	public void removeWrenchableBlock(IItemStack block){ CraftTweakerAPI.apply(new RemoveBlockAction(block)); }
	
	public static class AddBlockAction implements IAction{
		
		private final Block newBlock;
		private ChangeDamageAction damageAction;
		
		public AddBlockAction(IItemStack block){ newBlock = CraftTweakerMC.getBlock(block); }
		
		public AddBlockAction(IItemStack block, int min){
			this(block);
			damageAction = new ChangeDamageAction(block, min);
		}
		
		public AddBlockAction(IItemStack block, int min, int max){
			this(block, min);
			damageAction = new ChangeDamageAction(block, min, max);
		}
		
		public void apply(){
			if(!hasWrenchable(newBlock)){
				addWrenchable(newBlock);
				if(damageAction != null) CraftTweakerAPI.apply(damageAction);
			}
		}
		
		public String describe(){ return "Attempting to add new wrenchable block: " + newBlock.getLocalizedName(); }
		
	}
	
	public static class ChangeDamageAction implements IAction{
		
		private final Block chosenBlock;
		private final int minDamage;
		private boolean usesMax;
		private int maxDamage;
		
		public ChangeDamageAction(IItemStack block, int min){
			chosenBlock = CraftTweakerMC.getBlock(block);
			minDamage = min;
		}
		
		public ChangeDamageAction(IItemStack block, int min, int max){
			this(block, min);
			usesMax = true;
			maxDamage = max;
		}
		
		public String describe(){ return "Attempting to alter wrench damage to: " + getWrenchable(chosenBlock); }
		
		public void apply(){
			if(hasWrenchable(chosenBlock)){
				if(!usesMax) setDamage(chosenBlock, minDamage);
				else setDamage(chosenBlock, minDamage, maxDamage);
			}
		}
		
	}
	
	public static class RemoveBlockAction implements IAction{
		
		private Block chosenBlock;
		
		public RemoveBlockAction(IItemStack block){ CraftTweakerMC.getBlock(block); }
		
		public String describe(){ return "Attempting to remove wrenchable:" + getWrenchable(chosenBlock); }
		
		public void apply(){ if(hasWrenchable(chosenBlock)) removeWrenchable(chosenBlock); }
		
	}
	
}
