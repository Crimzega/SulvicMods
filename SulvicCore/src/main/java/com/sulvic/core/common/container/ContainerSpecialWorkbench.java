package com.sulvic.core.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.sulvic.core.common.tileentity.TileSpecialWorkbench;

public class ContainerSpecialWorkbench extends Container{
	
	private TileSpecialWorkbench specialWorkbench;
	
	public ContainerSpecialWorkbench(InventoryPlayer playerInv, TileSpecialWorkbench workbench){
		specialWorkbench = workbench;
		for(int y = 0; y < 3; y++) for(int x = 0; x < 9; x++) addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 98 + y * 18));
		for(int x = 0; x < 9; x++) addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 156));
	}
	
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index){
		return super.transferStackInSlot(playerIn, index);
	}
	
	public boolean canInteractWith(EntityPlayer player){ return specialWorkbench.isUsableByPlayer(player); }
	
}
