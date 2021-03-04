package com.sulvic.core.client.renders;

import java.util.List;
import java.util.function.ToIntFunction;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ObjectHelper{
	
	private static final StateMapperBase STATE_MAPPER = new StateMapperBase(){
		
		protected ModelResourceLocation getModelResourceLocation(IBlockState state){ return new ModelResourceLocation("minecraft:air"); }
		
	};
	
	private static void registerItemBlockModel(IBlockState state, int metadata){
		Item item = Item.getItemFromBlock(state.getBlock());
		if(item != Items.AIR) registerItemModel(item, metadata, STATE_MAPPER.getPropertyString(state.getProperties()));
	}
	
	private static void registerItemBlockModel(IBlockState state){
		Block block = state.getBlock();
		Item item = Item.getItemFromBlock(block);
		if(item != Items.AIR) registerItemModel(item, block.getMetaFromState(state), STATE_MAPPER.getPropertyString(state.getProperties()));
	}
	
	private static void registerItemModel(Item item, int metadata, ModelResourceLocation modelResLoc){ ModelLoader.setCustomModelResourceLocation(item, metadata, modelResLoc); }
	
	public static void registerItemModel(Item item){ registerItemModel(item, "invenotry"); }
	
	public static void registerItemModel(Item item, String variant){ registerItemModel(item, 0, variant); }
	
	public static void registerItemModel(Item item, int metadata, String variant){ registerItemModel(item, metadata, new ModelResourceLocation(item.getRegistryName(), variant)); }
	
	public static void registerItemModel(Item item, int metadata, List<ResourceLocation> resLocs){ registerItemModel(item, metadata, new ModelResourceLocation(resLocs.get(metadata), "inventory")); }
	
	public static <T extends Enum<T> & IStringSerializable> void registerItemModels(Item item, T[] enums){
		ResourceLocation[] resLocs = new ResourceLocation[enums.length];
		for(int i = 0; i < enums.length; i++) resLocs[i] = new ResourceLocation(item.getRegistryName() + "_" + enums[i].getName());
		ModelBakery.registerItemVariants(item, resLocs);
		for(int i = 0; i < enums.length; i++) registerItemModel(item, i, new ModelResourceLocation(resLocs[i], "inventory"));
	}
	
	public static <T extends Comparable<T>> void registerBlockModels(Block block, IProperty<T> property, ToIntFunction<T> metaHelper){
		IBlockState state = block.getDefaultState();
		property.getAllowedValues().forEach(value -> registerItemBlockModel(state.withProperty(property, value), metaHelper.applyAsInt(value)));
	}
	
	public static <T extends Comparable<T>> void registerBlockModels(Block block, IProperty<T> property){
		IBlockState state = block.getDefaultState();
		property.getAllowedValues().forEach(value -> registerItemBlockModel(state.withProperty(property, value)));
	}
	
	public static void registerBlockModel(Block block){ registerItemModel(Item.getItemFromBlock(block), 0, "normal"); }
	
}
