package com.sulvic.core.world.gen;

import java.util.Random;

import com.sulvic.util.SulvicMath;

public class VeinInfo{
	
	public int maxSize, maxY, minSize, minY, veinChance;
	
	private VeinInfo(int y, int y1){
		minY = y;
		maxY = y1;
	}
	
	public static VeinInfo create(int y, int y1, int min, int max, int chance){ return new VeinInfo(y, y1).setSize(min, max).setChance(chance); }
	
	private VeinInfo setSize(int min, int max){
		minSize = min;
		maxSize = max;
		return this;
	}
	
	private VeinInfo setChance(int chance){
		veinChance = chance;
		return this;
	}
	
	public int getRandSize(Random random){ return SulvicMath.rangedInt(random, minSize, maxSize); }
	
	public int getRandY(Random random){ return SulvicMath.rangedInt(random, minY, maxY); }
	
	public int getVeinChance(){ return veinChance; }
	
}
