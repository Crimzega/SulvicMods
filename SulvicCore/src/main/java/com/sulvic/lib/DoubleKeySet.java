package com.sulvic.lib;

import java.util.Map;

public interface DoubleKeySet<K, K1>{
	
	public static <K, K1, V> boolean mapContainsKeys(Map<DoubleKeySet<K, K1>, V> map, K key, K1 key1){
		for(DoubleKeySet<K, K1> keySet: map.keySet()) if(keySet.hasKeys(key, key1)) return true;
		return false;
	}
	
	public static <K, K1, V> V getMapObject(Map<DoubleKeySet<K, K1>, V> map, K key, K1 key1){
		if(mapContainsKeys(map, key, key1)){
			for(DoubleKeySet<K, K1> keySet: map.keySet()) if(keySet.hasKeys(key, key1)) return map.get(keySet);
			return null;
		}
		else return null;
	}
	
	boolean hasKeys(Object keys);
	
	boolean hasKeys(Object key, Object key1);
	
	K getMainKey();
	
	K1 getSecondKey();
	
}
