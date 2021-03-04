package com.sulvic.core.common.block;

import java.util.Random;

import com.sulvic.core.ReferenceSC;
import com.sulvic.core.common.FolkrumTabs;
import com.sulvic.core.common.SulvicObjects;
import com.sulvic.core.proxy.AlvontixClient;
import com.sulvic.core.world.gen.IVein;
import com.sulvic.core.world.gen.VeinInfo;
import com.sulvic.util.SulvicMath;

import net.minecraft.block.BlockBreakable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.world.biome.Biome;

public class OreSpecial extends BlockBreakable{
	
	public OreSpecial(){
		super(Material.GLASS, false);
		setCreativeTab(FolkrumTabs.BLOCKS);
		setHardness(3.5f);
		setHarvestLevel("pickaxe", 3);
		setRegistryName(ReferenceSC.MODID, "special_ore");
		setResistance(8f);
		setSoundType(SoundType.GLASS);
		setUnlocalizedName("specialOre");
	}
	
	@AlvontixClient
	public BlockRenderLayer getBlockLayer(){ return BlockRenderLayer.TRANSLUCENT; }
	
	public boolean isFullCube(IBlockState state){ return false; }
	
	public int quantityDropped(IBlockState state, int fortune, Random random){ return SulvicMath.rangedInt(random, 5, 13); }
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune){ return SulvicObjects.SPECIAL_DUST; }
	
	public static class SpecialVein implements IVein{
		
		public Biome[] allowedBiomes(){ return new Biome[0]; }
		
		public int getDimensionId(){ return -1; }
		
		public IBlockState getBlockState(){ return SulvicObjects.SPECIAL_ORE.getDefaultState(); }
		
		public VeinInfo getVeinInfo(){ return VeinInfo.create(0, 256, 3, 11, 15); }
		
	}
	
}
