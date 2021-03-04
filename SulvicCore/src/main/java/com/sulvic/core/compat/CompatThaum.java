package com.sulvic.core.compat;

import static thaumcraft.api.aspects.Aspect.*;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sulvic.core.common.SulvicObjects;
import com.sulvic.core.common.SulvicObjects.EnumGem;

import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class CompatThaum{
	
	private static final List<AffectedStack> STACK_ASPECTS = Lists.newArrayList();
	private static final Map<EnumGem, AspectList> GEM_ASPECTS = Maps.newHashMap();
	
	static{
		GEM_ASPECTS.put(EnumGem.RUBY, new AspectList().add(DESIRE, 10).add(CRYSTAL, 10));
		GEM_ASPECTS.put(EnumGem.PINK_PANTHER, new AspectList().add(DESIRE, 30).add(CRYSTAL, 20));
		GEM_ASPECTS.put(EnumGem.SAPPHIRE, new AspectList().add(DESIRE, 15).add(CRYSTAL, 30));
		GEM_ASPECTS.put(EnumGem.CASSITERITE, new AspectList().add(DESIRE, 20).add(CRYSTAL, 15));
		GEM_ASPECTS.put(EnumGem.ENSTATITE, new AspectList().add(DESIRE, 15).add(CRYSTAL, 25));
		GEM_ASPECTS.put(EnumGem.MOONSTONE, new AspectList().add(DESIRE, 25).add(CRYSTAL, 10));
		GEM_ASPECTS.put(EnumGem.AQUAMARINE, new AspectList().add(DESIRE, 10).add(CRYSTAL, 25));
		GEM_ASPECTS.put(EnumGem.CITRINE, new AspectList().add(DESIRE, 15).add(CRYSTAL, 20));
		GEM_ASPECTS.put(EnumGem.TOURMALINE, new AspectList().add(DESIRE, 10).add(CRYSTAL, 15));
		GEM_ASPECTS.put(EnumGem.PERIDOT, new AspectList().add(DESIRE, 15).add(CRYSTAL, 10));
		GEM_ASPECTS.put(EnumGem.BERYL, new AspectList().add(DESIRE, 10).add(CRYSTAL, 20));
		GEM_ASPECTS.put(EnumGem.FIRE_AGATE, new AspectList().add(DESIRE, 20).add(CRYSTAL, 20));
		GEM_ASPECTS.put(EnumGem.DRUZY, new AspectList().add(DESIRE, 20).add(CRYSTAL, 25));
		GEM_ASPECTS.put(EnumGem.AMETRINE, new AspectList().add(DESIRE, 25).add(CRYSTAL, 30));
		GEM_ASPECTS.put(EnumGem.ONYX, new AspectList().add(DESIRE, 15).add(CRYSTAL, 20));
	}
	
	public static void init(){
		for(EnumGem gem: EnumGem.values()) {
			AspectList aspects = GEM_ASPECTS.get(gem);
			STACK_ASPECTS.add(AffectedStack.create(new ItemStack(SulvicObjects.GEM_ORES), new AspectList().add(aspects).add(EARTH, 5)));
			STACK_ASPECTS.add(AffectedStack.create(new ItemStack(SulvicObjects.GEMS), aspects));
		}
	}
	
	protected static class AffectedStack{
		
		protected ItemStack theStack;
		protected AspectList aspectList = new AspectList();
		
		private AffectedStack(ItemStack stack){ theStack = stack; }
		
		public static AffectedStack create(ItemStack stack, AspectList aspects){
			return new AffectedStack(stack).addAspects(aspects);
		}
		
		public static AffectedStack create(ItemStack stack, Aspect aspect, int amount){
			return new AffectedStack(stack).addAspect(aspect, amount);
		}
		
		private AffectedStack addAspect(Aspect aspect, int amount){
			aspectList.add(aspect, amount);
			return this;
		}
		
		private AffectedStack addAspects(AspectList aspects){
			aspectList.add(aspects);
			return this;
		}
		
	}
	
}
