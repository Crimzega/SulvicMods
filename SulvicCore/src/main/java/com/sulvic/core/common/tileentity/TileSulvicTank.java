package com.sulvic.core.common.tileentity;

import java.util.List;

import com.google.common.collect.Lists;
import com.sulvic.core.api.TankAPI;
import com.sulvic.util.SulvicMath;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

public class TileSulvicTank extends TileEntity implements IFluidTank, ITickable{
	
	private static final int[] MIN_INSIDE_SIZE = {1, 1, 1}, MAX_INSIDE_SIZE = {6, 8, 6};
	public static final int MAIN_MAX_CAPACITY = 64000;
	private final int[] insideSize = new int[3];
	private List<BlockPos> entriesPos = Lists.newArrayList();
	protected FluidStack currentFluid;
	
	public FluidStack drain(int maxDrain, boolean doDrain){
		if(currentFluid == null) return null;
		if(currentFluid.amount < maxDrain) maxDrain = currentFluid.amount;
		FluidStack drained = new FluidStack(currentFluid, maxDrain);
		if(doDrain){
			currentFluid.amount -= maxDrain;
			if(currentFluid.amount == 0) currentFluid = null;
		}
		return drained;
	}
	
	public FluidStack getFluid(){ return currentFluid; }
	
	public FluidTankInfo getInfo(){ return new FluidTankInfo(this); }
	
	public boolean isFull(){ return getEmptySpace() == 0; }
	
	public int fill(FluidStack fluid, boolean doFill){
		if(fluid == null) return 0;
		if(currentFluid == null){
			if(!doFill) SulvicMath.minInt(getCapacity(), fluid.amount);
			currentFluid = new FluidStack(fluid, SulvicMath.minInt(getCapacity(), fluid.amount));
			return currentFluid.amount;
		}
		if(!currentFluid.isFluidEqual(fluid)) return 0;
		if(TankAPI.isBlacklisted(fluid.getFluid())) return 0;
		int result = SulvicMath.minInt(getCapacity() - currentFluid.amount, fluid.amount);
		if(doFill && result > 0) currentFluid.amount += result;
		return result;
	}
	
	public int getEmptySpace(){ return getCapacity() - getFluidAmount(); }
	
	public int getCapacity(){ return MAIN_MAX_CAPACITY * (insideSize[0] * insideSize[1] * insideSize[2]); }
	
	public int getFluidAmount(){ return currentFluid != null? currentFluid.amount: 0; }
	
	public NBTTagCompound writeToNBT(NBTTagCompound nbtCompound){
		nbtCompound.setTag("CurrentFluid", currentFluid.writeToNBT(new NBTTagCompound()));
		NBTTagList nbtList = new NBTTagList();
		for(BlockPos pos: entriesPos){
			NBTTagCompound nbtCompound3 = new NBTTagCompound();
			nbtCompound3.setInteger("x", pos.getX());
			nbtCompound3.setInteger("y", pos.getY());
			nbtCompound3.setInteger("z", pos.getZ());
			nbtList.appendTag(nbtCompound3);
		}
		nbtCompound.setTag("EntryPoints", nbtList);
		return nbtCompound;
	}
	
	public void readFromNBT(NBTTagCompound nbtCompound){
		currentFluid = FluidStack.loadFluidStackFromNBT(nbtCompound.getCompoundTag("CurrentFluid"));
		NBTTagList nbtList = nbtCompound.getTagList("EntryPoints", 10);
		for(int i = 0; i < nbtList.tagCount(); i++){
			NBTTagCompound nbtCompound2 = nbtList.getCompoundTagAt(i);
			entriesPos.add(new BlockPos(nbtCompound2.getInteger("x"), nbtCompound2.getInteger("y"), nbtCompound2.getInteger("z")));
		}
	}
	
	public void setFluid(FluidStack fluid){ currentFluid = fluid; }
	
	public void update(){}
	
}
