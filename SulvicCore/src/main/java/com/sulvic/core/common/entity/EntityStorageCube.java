package com.sulvic.core.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IWorldNameable;
import net.minecraft.world.World;

public class EntityStorageCube extends Entity implements IWorldNameable{
	
	private String customName;
	
	public EntityStorageCube(World world){ super(world); }
	
	public boolean hasCustomName(){ return !StringUtils.isNullOrEmpty(customName); }
	
	public ITextComponent getDisplayName(){ return super.getDisplayName(); }
	
	public String getName(){ return super.getName(); }
	
	protected void entityInit(){}
	
	protected void readEntityFromNBT(NBTTagCompound nbtCompound){}
	
	protected void writeEntityToNBT(NBTTagCompound nbtCompound){}
	
}
