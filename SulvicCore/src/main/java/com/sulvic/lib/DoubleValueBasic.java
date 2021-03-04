package com.sulvic.lib;

import java.util.Objects;

@SuppressWarnings("unchecked")
public class DoubleValueBasic<V, V1> implements DoubleValueSet<V, V1>{
	
	V mainValue;
	V1 secondValue;
	
	public DoubleValueBasic(V value, V1 value1){
		mainValue = value;
		secondValue = value1;
	}
	
	public boolean equals(Object obj){
		if(obj instanceof DoubleValueBasic){
			DoubleValueBasic<V, V1> values = (DoubleValueBasic<V, V1>)obj;
			return hasValues(values);
		}
		else return super.equals(obj);
	}
	
	public boolean hasValues(Object values){
		if(values instanceof DoubleValueBasic){
			DoubleValueBasic<V, V1> inst = (DoubleValueBasic<V, V1>)values;
			return hasValues(inst.mainValue, inst.secondValue);
		}
		else return false;
	}
	
	public boolean hasValues(Object value, Object value1){ return Objects.deepEquals(mainValue, value) && Objects.deepEquals(secondValue, value1); }
	
	public V getMainValue(){ return mainValue; }
	
	public V1 getSecondValue(){ return secondValue; }
	
	public V setMainValue(V value){
		V oldValue = mainValue;
		mainValue = value;
		return oldValue;
	}
	
	public V1 setSecondValue(V1 value1){
		V1 oldValue1 = secondValue;
		secondValue = value1;
		return oldValue1;
	}
	
}
