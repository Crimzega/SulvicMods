package com.sulvic.core.common.block.tank;

import com.sulvic.core.ReferenceSC;
import com.sulvic.core.common.FolkrumTabs;
import com.sulvic.core.common.SulvicObjects;
import com.sulvic.core.common.SulvicObjects.EnumTankSide;
import com.sulvic.core.common.SulvicObjects.ISulvicTankEntry;
import com.sulvic.core.common.tileentity.TileSulvicTank;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;

public class BlockTankEntry extends Block implements ISulvicTankEntry{
	
	private static final PropertyDirection FACING = BlockHorizontal.FACING;
	private TileSulvicTank connectedTank;
	
	public BlockTankEntry(){
		super(Material.PISTON);
		setCreativeTab(FolkrumTabs.BLOCKS);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		setHardness(8f);
		setHarvestLevel("wrench", 0);
		setRegistryName(ReferenceSC.MODID, "tank_entry");
		setResistance(24f);
		setSoundType(SoundType.STONE);
		setUnlocalizedName("tank.entry");
	}
	
	private void setDefaultFacing(World world, BlockPos pos, IBlockState state){
		if(!world.isRemote){
			IBlockState northState = world.getBlockState(pos.north());
			IBlockState southState = world.getBlockState(pos.south());
			IBlockState westStaet = world.getBlockState(pos.west());
			IBlockState eastState = world.getBlockState(pos.east());
			EnumFacing facing = (EnumFacing)state.getValue(FACING);
			if(facing == EnumFacing.NORTH && northState.isFullBlock() && !southState.isFullBlock()) facing = EnumFacing.SOUTH;
			else if(facing == EnumFacing.SOUTH && southState.isFullBlock() && !northState.isFullBlock()) facing = EnumFacing.NORTH;
			else if(facing == EnumFacing.WEST && westStaet.isFullBlock() && !eastState.isFullBlock()) facing = EnumFacing.EAST;
			else if(facing == EnumFacing.EAST && eastState.isFullBlock() && !westStaet.isFullBlock()) facing = EnumFacing.WEST;
			world.setBlockState(pos, state.withProperty(FACING, facing), 2);
		}
	}
	
	protected BlockStateContainer createBlockState(){ return new BlockStateContainer(this, FACING); }
	
	public EnumTankSide getSide(){ return SulvicObjects.EnumTankSide.SIDE; }
	
	public FluidStack drain(int maxDrain, boolean doDrain){ return connectedTank != null? connectedTank.drain(maxDrain, doDrain): null; }
	
	public FluidStack getFluid(){ return connectedTank != null? connectedTank.getFluid(): null; }
	
	public FluidTankInfo getInfo(){ return connectedTank != null? connectedTank.getInfo(): null; }
	
	public IBlockState getStateFromMeta(int meta){
		EnumFacing facing = EnumFacing.getFront(meta);
		if(facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
		return getDefaultState().withProperty(FACING, facing);
	}
	
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer){
		return getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}
	
	public int fill(FluidStack fluid, boolean doFill){ return connectedTank != null? connectedTank.fill(fluid, doFill): 0; }
	
	public int getCapacity(){ return connectedTank != null? connectedTank.getCapacity(): null; }
	
	public int getFluidAmount(){ return connectedTank != null? connectedTank.getFluidAmount(): 0; }
	
	public int getMetaFromState(IBlockState state){ return ((EnumFacing)state.getValue(FACING)).getIndex(); }
	
	public TileSulvicTank getTank(){ return connectedTank; }
	
	public void onBlockAdded(World world, BlockPos pos, IBlockState state){ setDefaultFacing(world, pos, state); }
	
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack){
		world.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}
	
	public void setTank(TileSulvicTank tank){ connectedTank = tank; }
	
}
