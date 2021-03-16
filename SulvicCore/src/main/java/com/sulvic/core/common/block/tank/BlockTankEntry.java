package com.sulvic.core.common.block.tank;

import com.sulvic.core.ReferenceSC;
import com.sulvic.core.common.FolkrumTabs;
import com.sulvic.core.common.SulvicObjects.*;
import com.sulvic.core.common.tileentity.TileSulvicTank;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.*;

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
	
	public EnumTankSide getSide(){ return EnumTankSide.UNIVERSAL; }
	
	public FluidStack getFluid(){ return connectedTank.getFluid(); }
	
	public int getFluidAmount(){ return connectedTank.getFluidAmount(); }
	
	public int getCapacity(){ return connectedTank.getCapacity(); }
	
	public FluidTankInfo getInfo(){ return connectedTank.getInfo(); }
	
	public int fill(FluidStack fluid, boolean doFill){ return connectedTank.fill(fluid, doFill); }
	
	public FluidStack drain(int maxDrain, boolean doDrain){ return connectedTank.drain(maxDrain, doDrain); }
	
	public TileSulvicTank getTank(){ return connectedTank; }
	
	public void setTank(TileSulvicTank tank){ connectedTank = tank; }
	
}
