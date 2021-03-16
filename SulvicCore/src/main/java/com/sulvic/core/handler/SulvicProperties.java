package com.sulvic.core.handler;

import net.minecraftforge.common.property.IUnlistedProperty;

public class SulvicProperties{
	
	public static class StringProperty implements IUnlistedProperty<String>{
		
		private String theName;
		
		public StringProperty(String name){ theName = name; }
		
		public String getName(){ return theName; }
		
		public Class<String> getType(){ return String.class; }
		
		public boolean isValid(String value){ return value != null; }
		
		public String valueToString(String value){ return value; }
		
	}
	
}
