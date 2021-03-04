package com.sulvic.core.common.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class EntityBrokenSkull extends EntityLiving{
	
	public EntityBrokenSkull(World world){
		super(world);
		setSize(1.5f, 2.8125f);
	}
	
	public float getEyeHeight(){ return 1.5f; }
	
}
