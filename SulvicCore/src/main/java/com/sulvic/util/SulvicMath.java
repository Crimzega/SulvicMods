package com.sulvic.util;

import java.util.Random;

public class SulvicMath{
	
	private static final double ROTATION_BASE = Math.PI / 180d;
	private static final Random RAND = new Random();
	
	public static boolean inRange(double value, double min, double max){ return value >= min && value < max; }
	
	public static boolean inRange(float value, float min, float max){ return value >= min && value < max; }
	
	public static boolean inRange(int value, int min, int max){ return value >= min && value < max; }
	
	public static double clampDouble(double value, double min, double max){ return value < min? min: value > max? max: value; }
	
	public static double getRotationDouble(double value){ return ROTATION_BASE * clampDouble(value, -180d, 180d); }
	
	public static double minDouble(double value, double value1){ return value > value1? value: value1; }
	
	public static double maxDouble(double value, double value1){ return value > value1? value1: value; }
	
	public static double rangedDouble(double min, double max){ return rangedDouble(RAND, min, max); }
	
	public static double rangedDouble(Random rand, double min, double max){ return rand.nextDouble() * (max - min) + min; }
	
	public static double wrapDouble(double value, double min, double max){
		double wrap = max - min;
		while(value < min) value += wrap;
		while(value > max) value -= wrap;
		return value;
	}
	
	public static float getRotationFloat(float value){ return (float)ROTATION_BASE * clampFloat(value, -180f, 180f); }
	
	public static float clampFloat(float value, float min, float max){ return value < min? min: value > max? max: value; }
	
	public static float minFloat(float value, float value1){ return value > value1? value: value1; }
	
	public static float maxFloat(float value, float value1){ return value > value1? value1: value; }
	
	public static float rangedFloat(float min, float max){ return rangedFloat(RAND, min, max); }
	
	public static float rangedFloat(Random rand, float min, float max){ return rand.nextFloat() * (max - min) + min; }
	
	public static float wrapFloat(float value, float min, float max){
		float wrap = max - min;
		while(value < min) value += wrap;
		while(value > max) value -= wrap;
		return value;
	}
	
	public static int clampInt(int value, int min, int max){ return value < min? min: value > max? max: value; }
	
	public static int rangedInt(int min, int max){ return rangedInt(RAND, min, max); }
	
	public static int maxInt(int value, int value1){ return value > value1? value: value1; }
	
	public static int minInt(int value, int value1){ return value > value1? value1: value; }
	
	public static int rangedInt(Random rand, int min, int max){ return rand.nextInt(max - min) + min; }
	
	public static float wrapInt(int value, int min, int max){
		float wrap = max - min;
		while(value < min) value += wrap;
		while(value > max) value -= wrap;
		return value;
	}
	
}
