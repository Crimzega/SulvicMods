package com.sulvic.lib;

import java.util.Objects;

import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("unchecked")
public class DoubleKeyBasic<K, K1> implements DoubleKeySet<K, K1>{
	
	K mainKey;
	K1 secondKey;
	
	public DoubleKeyBasic(K key, K1 key1){
		mainKey = key;
		secondKey = key1;
	}
	
	public boolean equals(Object obj){
		if(obj instanceof DoubleKeyBasic){
			DoubleKeyBasic<K, K1> keys = (DoubleKeyBasic<K, K1>)obj;
			return hasKeys(keys);
		}
		else return super.equals(obj);
	}
	
	public boolean hasKeys(Object keys){
		if(keys instanceof DoubleKeyBasic){
			DoubleKeyBasic<K, K1> inst = (DoubleKeyBasic<K, K1>)keys;
			return hasKeys(inst.mainKey, inst.secondKey);
		}
		else return false;
	}
	
	public boolean hasKeys(Object key, Object key1){ return Objects.equals(mainKey, key) && Objects.equals(secondKey, key1); }
	
	public int hashCode(){ return new HashCodeBuilder(9, 25).append(mainKey).append(secondKey).toHashCode(); }
	
	public K getMainKey(){ return mainKey; }
	
	public K1 getSecondKey(){ return secondKey; }
	
}
