package com.sulvic.core.common.tileentity;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

public class TileSulvicTank extends TileEntity implements IFluidTank{
	
	public static final int MAX_CAPACITY = 64000;
	protected FluidStack currentFluid;
	
	public Block getFluidBlock(){ return currentFluid.getFluid().getBlock(); }
	
	public FluidStack getFluid(){ return currentFluid; }
	
	public int getFluidAmount(){ return currentFluid.amount; }
	
	public int getCapacity(){ return MAX_CAPACITY; }
	
	public FluidTankInfo getInfo(){ return new FluidTankInfo(this); }
	
	public int fill(FluidStack resource, boolean doFill){ return 0; }
	
	public FluidStack drain(int maxDrain, boolean doDrain){ return null; }
	
	public NBTTagCompound writeToNBT(NBTTagCompound nbtCompound){ return super.writeToNBT(nbtCompound); }
	
	public void readFromNBT(NBTTagCompound nbtCompound){ super.readFromNBT(nbtCompound); }
	
}
