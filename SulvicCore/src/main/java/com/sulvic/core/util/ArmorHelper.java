package com.sulvic.core.util;

import static net.minecraft.inventory.EntityEquipmentSlot.CHEST;
import static net.minecraft.inventory.EntityEquipmentSlot.FEET;
import static net.minecraft.inventory.EntityEquipmentSlot.HEAD;
import static net.minecraft.inventory.EntityEquipmentSlot.LEGS;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.inventory.EntityEquipmentSlot;

public class ArmorHelper{
	
	private static final List<ArmorHelper> TYPES = Lists.newArrayList();
	public static final ArmorHelper HELMET = new ArmorHelper("HELMET", HEAD);
	public static final ArmorHelper CHESTPLATE = new ArmorHelper("CHESTPLATE", CHEST);
	public static final ArmorHelper LEGGINGS = new ArmorHelper("LEGGINGS", LEGS);
	public static final ArmorHelper BOOTS = new ArmorHelper("BOOTS", FEET);
	
	private EntityEquipmentSlot armorSlot;
	private String armorName;
	
	private ArmorHelper(String name, EntityEquipmentSlot slot){
		armorName = name;
		armorSlot = slot;
		TYPES.add(this);
	}
	
	public boolean hasName(String name){ return armorName.equals(name); }
	
	public String getName(){ return armorName.toLowerCase(); }
	
	public String getUnlocalizedName(){ return armorName.substring(0, 1) + armorName.substring(1).toLowerCase(); }
	
	public int getIndex(){ return TYPES.indexOf(this); }
	
	public EntityEquipmentSlot getSlot(){ return armorSlot; }
	
	public static ArmorHelper createHelper(String name, EntityEquipmentSlot slot){ return new ArmorHelper(name, slot); }
	
	public static ArmorHelper getHelperByIndex(int index){ return TYPES.get(index); }
	
	public static ArmorHelper getHelperByType(String name){
		String temp = name.toUpperCase();
		switch(temp){
			case "HELMET":
				return HELMET;
			case "CHESTPLATE":
				return CHESTPLATE;
			case "LEGGINGS":
				return LEGGINGS;
			case "BOOTS":
				return BOOTS;
			default:
				ArmorHelper result = null;
				for(ArmorHelper helper: TYPES){
					if(helper.hasName(temp)){
						result = helper;
						break;
					}
				}
				if(result == null) throw new UnamedArmorTypeException("The expected armor type " + temp + " was expected but wasn't created.");
				return result;
		}
	}
	
	public static ArmorHelper[] values(){
		ArmorHelper[] result = new ArmorHelper[TYPES.size()];
		for(int i = 0; i < TYPES.size(); i++) result[i] = TYPES.get(i);
		return result;
	}
	
	@SuppressWarnings("serial")
	public static class UnamedArmorTypeException extends RuntimeException{
		
		public UnamedArmorTypeException(String msg){ super(msg); }
		
		public UnamedArmorTypeException(String msg, Throwable thrown){ super(msg, thrown); }
		
	}
	
}
