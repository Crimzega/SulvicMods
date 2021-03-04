package com.sulvic.core.api;

import static com.sulvic.util.SulvicMath.*;

import java.util.Objects;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import net.minecraft.block.Block;

public class WrenchBlock{
	
	protected static final Damage DEFAULT_DAMAGE = createDamage(2);
	
	protected final Block theBlock;
	protected Damage theDamage;
	
	private WrenchBlock(Block block){ theBlock = block; }
	
	protected static WrenchBlock createWrenchable(Block block){ return new WrenchBlock(block); }
	
	protected static WrenchBlock createWrenchable(Block block, int min){ return createWrenchable(block).setDamage(createDamage(min)); }
	
	protected static WrenchBlock createWrenchable(Block block, int min, int max){ return createWrenchable(block).setDamage(createDamage(min, max)); }
	
	protected static WrenchBlock.Damage createDamage(int min){ return new WrenchBlock.Damage(min); }
	
	protected static WrenchBlock.Damage createDamage(int min, int max){ return createDamage(minInt(min, max)).setMaxDamage(maxInt(min, max)); }
	
	public Block getBlock(){ return theBlock; }
	
	public boolean equals(Object obj){
		if(obj == this) return true;
		if(!(obj instanceof WrenchBlock)) return false;
		WrenchBlock wrenchable = (WrenchBlock)obj;
		return Objects.equals(theBlock, wrenchable.theBlock) && theDamage.equals(wrenchable.theDamage);
	}
	
	public boolean hasBlock(Block block){ return theBlock.equals(block); }
	
	public boolean hasDamage(){ return theDamage != null; }
	
	public Damage getDamage(){ return theDamage; }
	
	public int hashCode(){ return new HashCodeBuilder(9, 43).append(theBlock).append(hasDamage()).append(hasDamage()? DEFAULT_DAMAGE: theDamage).build(); }
	
	public WrenchBlock setDamage(Damage damage){
		theDamage = damage;
		return this;
	}
	
	public WrenchBlock setDamage(int damage){ return setDamage(new Damage(damage)); }
	
	public WrenchBlock setDamage(int minDamage, int maxDamage){
		setDamage(new Damage(minDamage).setMaxDamage(maxDamage));
		return this;
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Block: ").append(theBlock.getLocalizedName());
		if(theDamage != null) builder.append(theDamage.toString());
		return builder.toString();
	}
	
	public static class Damage{
		
		private final int minDamage;
		private boolean usesMax = false;
		private int maxDamage;
		
		private Damage(int min){ minDamage = min; }
		
		public boolean equals(Object obj){
			if(obj == this) return true;
			if(!(obj instanceof Damage)) return false;
			Damage damage = (Damage)obj;
			return minDamage == damage.minDamage && usesMax == damage.usesMax && (usesMax? maxDamage == damage.maxDamage: true);
		}
		
		public boolean usesMax(){ return usesMax; }
		
		public int getMax(){ return usesMax? maxDamage: minDamage + 3; }
		
		public int getMin(){ return minDamage; }
		
		public int getRandDamage(){ return rangedInt(getMin(), getMax()); }
		
		public int hashCode(){ return new HashCodeBuilder(11, 27).append(getMin()).append(usesMax).append(getMax()).build(); }
		
		public Damage setMaxDamage(int max){
			usesMax = true;
			maxDamage = max;
			return this;
		}
		
		public String toString(){ return "Damage: [Min:" + getMin() + ", Max: " + getMax() + "]"; }
		
	}
	
}
