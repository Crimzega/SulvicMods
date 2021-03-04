package com.sulvic.core.compat;

import static com.sulvic.core.common.SulvicObjects.EnumGem.AMETRINE;
import static com.sulvic.core.common.SulvicObjects.EnumGem.AQUAMARINE;
import static com.sulvic.core.common.SulvicObjects.EnumGem.BERYL;
import static com.sulvic.core.common.SulvicObjects.EnumGem.CASSITERITE;
import static com.sulvic.core.common.SulvicObjects.EnumGem.CITRINE;
import static com.sulvic.core.common.SulvicObjects.EnumGem.DRUZY;
import static com.sulvic.core.common.SulvicObjects.EnumGem.ENSTATITE;
import static com.sulvic.core.common.SulvicObjects.EnumGem.FIRE_AGATE;
import static com.sulvic.core.common.SulvicObjects.EnumGem.MOONSTONE;
import static com.sulvic.core.common.SulvicObjects.EnumGem.PERIDOT;
import static com.sulvic.core.common.SulvicObjects.EnumGem.PINK_PANTHER;
import static com.sulvic.core.common.SulvicObjects.EnumGem.RUBY;
import static com.sulvic.core.common.SulvicObjects.EnumGem.SAPPHIRE;
import static com.sulvic.core.common.SulvicObjects.EnumGem.TOURMALINE;

import java.util.Map;

import com.google.common.collect.Maps;
import com.sulvic.core.common.SulvicObjects;

import moze_intel.projecte.api.ProjectEAPI;
import moze_intel.projecte.api.proxy.IEMCProxy;
import net.minecraft.item.ItemStack;

public class CompatProjE{
	
	private static final Map<SulvicObjects.EnumGem, Long> GEMS_EMC = Maps.newHashMap();
	private static IEMCProxy emcProxy;
	
	static{
		GEMS_EMC.put(RUBY, 5384l);
		GEMS_EMC.put(PINK_PANTHER, 2560l);
		GEMS_EMC.put(SAPPHIRE, 5632l);
		GEMS_EMC.put(CASSITERITE, 4608l);
		GEMS_EMC.put(ENSTATITE, 6656l);
		GEMS_EMC.put(MOONSTONE, 6144l);
		GEMS_EMC.put(AQUAMARINE, 4096l);
		GEMS_EMC.put(CITRINE, 2304l);
		GEMS_EMC.put(TOURMALINE, 3328l);
		GEMS_EMC.put(PERIDOT, 2880l);
		GEMS_EMC.put(BERYL, 3840l);
		GEMS_EMC.put(FIRE_AGATE, 4032l);
		GEMS_EMC.put(DRUZY, 7680l);
		GEMS_EMC.put(AMETRINE, 7168l);
	}
	
	private static void registerEnMatCurrs(){
		for(SulvicObjects.EnumGem gem: SulvicObjects.EnumGem.values()){
			emcProxy.registerCustomEMC(new ItemStack(SulvicObjects.GEMS, 1, gem.getMetadata()), GEMS_EMC.get(gem));
		}
	}
	
	public static void init(){
		emcProxy = ProjectEAPI.getEMCProxy();
		registerEnMatCurrs();
	}
	
}
