package com.sulvic.core.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;

import com.sulvic.core.common.container.ContainerSpecialWorkbench;

public class TileSpecialWorkbench extends TileEntity implements IInventory, IInteractionObject{
	
	private NonNullList<SecondDepth> secondDepth = NonNullList.<SecondDepth>withSize(9, new SecondDepth(this));
	private ItemStack craftingResult = ItemStack.EMPTY;
	private SecondDepth currDepth;
	private String customName;
	
	public TileSpecialWorkbench(World world){ this.world = world; }
	
	public boolean hasCustomName(){ return !StringUtils.isNullOrEmpty(customName); }
	
	public boolean isEmpty(){
		for(SecondDepth layer: secondDepth) if(!layer.isEmpty()) return false;
		return craftingResult.isEmpty();
	}
	
	private boolean isDepthOpen(){ return currDepth != null && currDepth.layerOpened; }
	
	public boolean isDepthOpen(int index){ return isDepthOpen() && currDepth.equals(secondDepth.get(index)); }
	
	public boolean isItemValidForSlot(int index, ItemStack stack){ return index != 90; }
	
	public boolean isUsableByPlayer(EntityPlayer player){
		return world.getTileEntity(pos) == this && player.getDistanceSq(pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d) <= 64d;
	}
	
	public Container createContainer(InventoryPlayer playerInv, EntityPlayer player){ return new ContainerSpecialWorkbench(playerInv, this); }
	
	public int getField(int id){ return 0; }
	
	public int getFieldCount(){ return 0; }
	
	public int getInventoryStackLimit(){ return 64; }
	
	public int getSizeInventory(){ return 91; }
	
	public ItemStack decrStackSize(int index, int decr){ return WorkbenchHelper.getAndSplit(this, index, decr); }
	
	public ItemStack getStackInSlot(int index){ return index < 90? getDepth(index / 10).getStackInSlot(index % 10): craftingResult; }
	
	public ItemStack removeStackFromSlot(int index){ return WorkbenchHelper.getAndRemove(this, index); }
	
	public ITextComponent getDisplayName(){ return super.getDisplayName(); }
	
	public NBTTagCompound writeToNBT(NBTTagCompound nbtCompound){
		WorkbenchHelper.saveAllItems(this, nbtCompound);
		return nbtCompound;
	}
	
	public String getGuiID(){ return "sulviccore:workbench_special"; }
	
	public String getName(){ return hasCustomName()? customName: "container.special"; }
	
	public NonNullList<SecondDepth> getCompleteDepth(){ return secondDepth; }
	
	public SecondDepth getDepth(int index){ return secondDepth.get(index); }
	
	public SecondDepth getCurrentDepth(){ return currDepth; }
	
	public void clear(){}
	
	public void closeDepth(){
		currDepth.closeDepth();
		currDepth = (SecondDepth)null;
	}
	
	public void closeInventory(EntityPlayer player){}
	
	public void setField(int id, int value){}
	
	public void markDirty(){}
	
	public void openInventory(EntityPlayer player){}
	
	public void openDepth(int index){
		if(currDepth != null) closeDepth();
		currDepth = getDepth(index);
		currDepth.openDepth();
	}
	
	public void readFromNBT(NBTTagCompound nbtCompound){ WorkbenchHelper.loadAllItems(this, nbtCompound); }
	
	public void setInventorySlotContents(int index, ItemStack stack){
		if(index < 90) getDepth(index / 10).setInventorySlotContents(index % 10, stack);
		else craftingResult = stack;
	}
	
	public static class SecondDepth{
		
		private boolean layerOpened = false;
		private NonNullList<ItemStack> subContent = NonNullList.<ItemStack>withSize(10, ItemStack.EMPTY);
		private TileSpecialWorkbench specialWorkbench;
		
		public SecondDepth(TileSpecialWorkbench workbench){ specialWorkbench = workbench; }
		
		public ItemStack[] getCraftingMatrix(){
			ItemStack[] matrix = new ItemStack[9];
			for(int i = 0; i < matrix.length; i++) matrix[i] = getStackInSlot(i);
			return matrix;
		}
		
		public boolean isEmpty(){
			for(ItemStack stack: subContent) if(!stack.isEmpty()) return false;
			return true;
		}
		
		public boolean isOpened(){ return layerOpened; }
		
		public boolean hasOverrideStack(){ return !getOverrideStack().isEmpty(); }
		
		public ItemStack getOverrideStack(){ return getStackInSlot(9); }
		
		public ItemStack getStackInSlot(int index){ return subContent.get(index); }
		
		public ItemStack removeStackFromSlot(int index){ return ItemStackHelper.getAndRemove(subContent, index); }
		
		public ItemStack decrStackInSlot(int index, int decr){ return ItemStackHelper.getAndSplit(subContent, index, decr); }
		
		public TileSpecialWorkbench getWorkbench(){ return specialWorkbench; }
		
		public NBTTagCompound writeToNBT(NBTTagCompound nbtCompound){
			ItemStackHelper.saveAllItems(nbtCompound, subContent);
			return nbtCompound;
		}
		
		public void closeDepth(){ layerOpened = false; }
		
		public void openDepth(){ layerOpened = true; }
		
		public void readFromNBT(NBTTagCompound nbtCompound){ ItemStackHelper.loadAllItems(nbtCompound, subContent); }
		
		public void setInventorySlotContents(int index, ItemStack stack){ subContent.set(index, stack); }
		
	}
	
	private static class WorkbenchHelper{
		
		private static SecondDepth getLayer(TileSpecialWorkbench tileWorkbench, int index){
			return tileWorkbench.getDepth(index);
		}
		
		private static ItemStack changeCraftingResult(TileSpecialWorkbench tileWorkbench){
			ItemStack result = tileWorkbench.craftingResult;
			tileWorkbench.craftingResult = ItemStack.EMPTY;
			return result;
		}
		
		public static ItemStack getAndRemove(TileSpecialWorkbench tileWorkbench, int index){
			return index < 90? getLayer(tileWorkbench, index / 10).removeStackFromSlot(index % 10): changeCraftingResult(tileWorkbench);
		}
		
		public static ItemStack getAndSplit(TileSpecialWorkbench tileWorkbench, int index, int amount){
			return index < 90? getLayer(tileWorkbench, index / 10).decrStackInSlot(index % 10, amount): tileWorkbench.craftingResult.splitStack(amount);
		}
		
		public static NBTTagCompound saveAllItems(TileSpecialWorkbench tileWorkbench, NBTTagCompound nbtCompound){
			NBTTagList nbtList = nbtCompound.getTagList("CraftingLayers", 10);
			for(int i = 0; i < nbtList.tagCount(); i++){
				NBTTagCompound nbtCompound2 = nbtList.getCompoundTagAt(i);
				tileWorkbench.getDepth(i).readFromNBT(nbtCompound2);
			}
			tileWorkbench.craftingResult.setTagCompound(nbtCompound.getCompoundTag("Result"));
			return nbtCompound;
		}
		
		public static void loadAllItems(TileSpecialWorkbench tileWorkbench, NBTTagCompound nbtCompound){
			NBTTagList nbtList = new NBTTagList();
			for(int i = 0; i < 9; i++) nbtList.appendTag(tileWorkbench.getDepth(i).writeToNBT(new NBTTagCompound()));
			nbtCompound.setTag("CraftingLayers", nbtList);
			nbtCompound.setTag("Result", tileWorkbench.craftingResult.getTagCompound());
		}
		
	}
	
}
