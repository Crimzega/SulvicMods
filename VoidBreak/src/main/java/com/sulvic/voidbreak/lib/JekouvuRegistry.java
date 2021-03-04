package com.sulvic.voidbreak.lib;

import java.util.List;

import com.google.common.collect.Lists;
import com.sulvic.voidbreak.ReferenceVB;
import com.sulvic.voidbreak.VoidBreak;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = ReferenceVB.MODID)
public class JekouvuRegistry{
	
	private static final List<Block> BLOCKS = Lists.newArrayList();
	private static final List<Item> ITEMS = Lists.newArrayList();
	private static final List<Biome> BIOMES = Lists.newArrayList();
	
	public static void addBiome(Biome biome){ BIOMES.add(biome); }
	
	public static void addBlock(Block block){
		ItemBlock item = new ItemBlock(block);
		item.setRegistryName(block.getRegistryName());
		addBlock(block, item);
	}
	
	public static void addBlock(Block block, ItemBlock item){
		BLOCKS.add(block);
		addItem(item);
	}
	
	public static void addItem(Item item){ ITEMS.add(item); }
	
	@SubscribeEvent
	public static void registerBiomes(Register<Biome> evt){
		for(Biome biome: BIOMES) evt.getRegistry().register(biome);
		VoidBreak.getLogger().info("Registered Biomes");
	}
	
	@SubscribeEvent
	public static void registerBlocks(Register<Block> evt){
		for(Block block: BLOCKS) evt.getRegistry().register(block);
		VoidBreak.getLogger().info("Registered Blocks");
	}
	
	@SubscribeEvent
	public static void registerItems(Register<Item> evt){
		for(Item item: ITEMS) evt.getRegistry().register(item);
		VoidBreak.getLogger().info("Registered Items");
	}
	
	@SubscribeEvent
	public static void registerBlockColors(ColorHandlerEvent.Block evt){ VoidBreak.modProxy.registerBlockColors(evt.getBlockColors()); }
	
	@SubscribeEvent
	public static void registerItemColors(ColorHandlerEvent.Item evt){ VoidBreak.modProxy.registerItemColors(evt.getItemColors()); }
	
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent evt){ VoidBreak.modProxy.registerModels(); }
	
}
