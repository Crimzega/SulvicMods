package com.sulvic.core.common;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.IRarity;

public class RegibaRarities{
	
	public static final IRarity BASIC = new IRarity(){
		
		public String getName(){ return "Basic"; }
		
		public TextFormatting getColor(){ return TextFormatting.GREEN; }
		
	}, ADVANCED = new IRarity(){
		
		public String getName(){ return "Advanced"; }
		
		public TextFormatting getColor(){ return TextFormatting.LIGHT_PURPLE; }
		
		
	}, LEGENDARY = new IRarity(){
		
		public String getName(){ return "Legendary"; }
		
		public TextFormatting getColor(){ return TextFormatting.DARK_AQUA; }
		
	}, UNHOLY = new IRarity(){
		
		public String getName(){ return "Unholy"; }
		
		public TextFormatting getColor(){ return TextFormatting.DARK_GRAY; }
		
	}, UNKNOWN = new IRarity(){
		
		public String getName(){ return "Unknown"; }
		
		public TextFormatting getColor(){ return TextFormatting.GRAY; }
		
	};
	
}
