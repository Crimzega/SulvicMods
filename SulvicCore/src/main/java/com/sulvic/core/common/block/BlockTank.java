package com.sulvic.core.common.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.sulvic.core.ReferenceSC;
import com.sulvic.core.common.FolkrumTabs;
import com.sulvic.core.common.tileentity.TileSulvicTank;
import com.sulvic.core.proxy.AlvontixClient;

public class BlockTank extends BlockContainer{
	
	public BlockTank(){
		super(Material.PISTON);
		setCreativeTab(FolkrumTabs.BLOCKS);
		setHardness(12f);
		setHarvestLevel("wrench", 0);
		setRegistryName(ReferenceSC.MODID, "tank");
		setResistance(20f);
		setSoundType(SoundType.GLASS);
		setUnlocalizedName("tank");
	}
	
	@AlvontixClient
	public BlockRenderLayer getBlockLayer(){ return BlockRenderLayer.CUTOUT; }
	
	public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer){
		return layer == BlockRenderLayer.CUTOUT || layer == BlockRenderLayer.CUTOUT_MIPPED || layer == BlockRenderLayer.TRANSLUCENT;
	}
	
	public boolean isFullCube(IBlockState state){ return false; }
	
	public EnumBlockRenderType getRenderType(IBlockState state){ return EnumBlockRenderType.MODEL; }
	
	public TileEntity createNewTileEntity(World worldIn, int meta){ return new TileSulvicTank(); }
	
	@AlvontixClient
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced){
		
	}
	
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand){
		TileEntity tile = world.getTileEntity(pos);
		if(tile instanceof TileSulvicTank){
			TileSulvicTank tank = (TileSulvicTank)tile;
			Block block = tank.getFluidBlock();
			IBlockState fluidState = block.getDefaultState();
			float maxLightLevel = block.getLightValue(fluidState, world, pos);
			if(maxLightLevel > 0f) setLightLevel(maxLightLevel * (float)((float)tank.getFluidAmount() / TileSulvicTank.MAX_CAPACITY));
		}
	}
	
}
