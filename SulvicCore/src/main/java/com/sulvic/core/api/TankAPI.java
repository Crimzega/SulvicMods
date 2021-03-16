package com.sulvic.core.api;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraftforge.fluids.Fluid;

public class TankAPI{
	
	private static final List<Fluid> BLACKLISTED = Lists.newArrayList();
	
	public static boolean isBlacklisted(Fluid fluid){ return BLACKLISTED.contains(fluid); }
	
	public static List<Fluid> getBlacklistedFluids(){ return BLACKLISTED; }
	
	public static void addToBlacklist(Fluid fluid){ if(!isBlacklisted(fluid)) BLACKLISTED.add(fluid); }
	
	public static void removeFromBlacklist(Fluid fluid){ if(isBlacklisted(fluid)) BLACKLISTED.remove(fluid); }
	
}
