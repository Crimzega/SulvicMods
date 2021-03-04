package com.sulvic.core.common.item;

import java.util.List;

import com.google.common.collect.Lists;
import com.sulvic.core.lib.ZimedaRegistry;

import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;

public class ItemToolSet{
	
	private static final List<ItemToolSet> TOOL_SETS = Lists.newArrayList();
	
	private ItemSword itemSword;
	private ItemSpade itemSpade;
	private ItemPickaxe itemPickaxe;
	private ItemAxe itemAxe;
	private ItemHoe itemHoe;
	
	public ItemToolSet(){ TOOL_SETS.add(this); }
	
	public static void addAllToRegistry(){
		List<ItemSword> swords = Lists.newArrayList();
		List<ItemHoe> hoes = Lists.newArrayList();
		for(ItemToolSet set: TOOL_SETS){
			swords.add(set.itemSword);
			hoes.add(set.itemHoe);
		}
		for(ItemSword sword: swords) ZimedaRegistry.addItem(sword);
		for(ItemToolSet set: TOOL_SETS){
			ZimedaRegistry.addItem(set.itemSpade);
			ZimedaRegistry.addItem(set.itemPickaxe);
			ZimedaRegistry.addItem(set.itemAxe);
		}
		for(ItemHoe hoe: hoes) ZimedaRegistry.addItem(hoe);
	}
	
	public ItemAxe getAxe(){ return itemAxe; }
	
	public ItemHoe getHoe(){ return itemHoe; }
	
	public ItemPickaxe getPickaxe(){ return itemPickaxe; }
	
	public ItemSpade getSpade(){ return itemSpade; }
	
	public ItemSword getSword(){ return itemSword; }
	
	public ItemToolSet addAxe(ItemAxe axe){
		itemAxe = axe;
		return this;
	}
	
	public ItemToolSet addHoe(ItemHoe hoe){
		itemHoe = hoe;
		return this;
	}
	
	public ItemToolSet addPickaxe(ItemPickaxe pickaxe){
		itemPickaxe = pickaxe;
		return this;
	}
	
	public ItemToolSet addSpade(ItemSpade spade){
		itemSpade = spade;
		return this;
	}
	
	public ItemToolSet addSword(ItemSword sword){
		itemSword = sword;
		return this;
	}
	
}
