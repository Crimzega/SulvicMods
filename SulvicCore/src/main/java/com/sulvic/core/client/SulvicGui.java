package com.sulvic.core.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import com.sulvic.core.SulvicCore;
import com.sulvic.core.client.gui.GuiSpecialWorkbench;
import com.sulvic.core.common.container.ContainerSpecialWorkbench;
import com.sulvic.core.common.tileentity.TileSpecialWorkbench;

public class SulvicGui implements IGuiHandler{
	
	private static final int SPECIAL_WORKBENCH_ID = 0;
	
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z){
		SulvicCore.getLogger().info("Attempting to find current gui!");
		TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
		if(tile != null){
			switch(id){
				case SPECIAL_WORKBENCH_ID:
					if(tile instanceof TileSpecialWorkbench) return new GuiSpecialWorkbench(player.inventory, (TileSpecialWorkbench)tile);
					return null;
			}
		}
		return null;
	}
	
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z){
		TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
		if(tile != null){
			switch(id){
				case SPECIAL_WORKBENCH_ID:
					if(tile instanceof TileSpecialWorkbench) return new ContainerSpecialWorkbench(player.inventory, (TileSpecialWorkbench)tile);
					return null;
			}
		}
		return null;
	}
	
}
