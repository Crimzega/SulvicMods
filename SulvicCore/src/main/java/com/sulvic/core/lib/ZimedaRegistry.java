package com.sulvic.core.lib;

import java.util.List;

import com.google.common.collect.Lists;
import com.sulvic.core.ReferenceSC;
import com.sulvic.core.SulvicCore;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = ReferenceSC.MODID)
public class ZimedaRegistry{
	
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
		SulvicCore.getLogger().info("Registered Biomes");
	}
	
	@SubscribeEvent
	public static void registerBlocks(Register<Block> evt){
		for(Block block: BLOCKS) evt.getRegistry().register(block);
		SulvicCore.getLogger().info("Registered Blocks");
	}
	
	@SubscribeEvent
	public static void registerItems(Register<Item> evt){
		for(Item item: ITEMS) {
			SulvicCore.getLogger().info(item);
			evt.getRegistry().register(item);
		}
		SulvicCore.getLogger().info("Registered Items");
	}
	
	@SubscribeEvent
	public static void registerBlockColors(ColorHandlerEvent.Block evt){ SulvicCore.modProxy.registerBlockColors(evt.getBlockColors()); }
	
	@SubscribeEvent
	public static void registerItemColors(ColorHandlerEvent.Item evt){ SulvicCore.modProxy.registerItemColors(evt.getItemColors()); }
	
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent evt){ SulvicCore.modProxy.registerModels(); }
	
	// public static void registerVillagers(){
	// VillageMarketRegistry.register();
	// GrocerProfession grocer = GrocerProfession.GROCER;
	// ForgeRegistries.VILLAGER_PROFESSIONS.register(grocer);
	// GrocerCareer grocer_career = new GrocerCareer(grocer);
	// grocer_career.addTrade(1, new FoodForEmerald(ObjectPriceInfo.create(Items.BEETROOT_SEEDS, 4, 12), new EmeraldPriceInfo(1,
	// 3)),
	// new EmeraldForFood(new EmeraldPriceInfo(4, 9), ObjectPriceInfo.create(Items.COAL, 4, 7),
	// ObjectPriceInfo.create(Items.BAKED_POTATO, 10, 18)));
	// }
	
}
