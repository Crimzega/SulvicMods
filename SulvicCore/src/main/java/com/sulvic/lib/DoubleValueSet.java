package com.sulvic.lib;

public interface DoubleValueSet<V, V1>{
	
	boolean hasValues(Object values);
	
	boolean hasValues(Object value, Object value1);
	
	V getMainValue();
	
	V1 getSecondValue();
	
	V setMainValue(V value);
	
	V1 setSecondValue(V1 value1);
	
}
