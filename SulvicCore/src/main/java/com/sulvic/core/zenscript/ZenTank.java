package com.sulvic.core.zenscript;

import java.util.List;

import com.google.common.collect.Lists;
import com.sulvic.core.api.TankAPI;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.liquid.ILiquidDefinition;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraftforge.fluids.Fluid;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.sulvic.Tank")
@ZenRegister
public class ZenTank{
	
	private static List<ILiquidDefinition> getList(){
		List<ILiquidDefinition> list = Lists.newArrayList();
		for(Fluid fluid: TankAPI.getBlacklistedFluids()) list.add(CraftTweakerMC.getILiquidDefinition(fluid));
		return list;
	}
	
	@ZenMethod("getFluids")
	public static List<ILiquidDefinition> getBlacklistedFluids(){ return getList(); }
	
	@ZenMethod("blacklistFluid")
	public static void addFluidToBlacklist(ILiquidDefinition liquidDef){ CraftTweakerAPI.apply(new BlacklistFluidAction(liquidDef)); }
	
	@ZenMethod("whitelistFluid")
	public static void removeFluidFromBlacklist(ILiquidDefinition liquidDef){ CraftTweakerAPI.apply(new WhitelistFluidAction(liquidDef)); }
	
	public static class BlacklistFluidAction implements IAction{
		
		private Fluid chosenFluid;
		
		public BlacklistFluidAction(ILiquidDefinition liquidDef){ chosenFluid = CraftTweakerMC.getFluid(liquidDef); }
		
		public void apply(){ TankAPI.addToBlacklist(chosenFluid); }
		
		public String describe(){ return "Blacklisting fluid: " + chosenFluid.getName(); }
		
	}
	
	public static class WhitelistFluidAction implements IAction{
		
		private Fluid chosenFluid;
		
		public WhitelistFluidAction(ILiquidDefinition liquidDef){ chosenFluid = CraftTweakerMC.getFluid(liquidDef); }
		
		@Override
		public void apply(){ TankAPI.removeFromBlacklist(chosenFluid); }
		
		public String describe(){ return "Whitelisting fluid: " + chosenFluid.getName(); }
		
	}
	
}
