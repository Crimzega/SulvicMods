package com.sulvic.core.handler;

import java.lang.reflect.Field;

import com.sulvic.core.ReferenceSC;
import com.sulvic.core.client.config.ConfigBase;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = ReferenceSC.MODID)
public class GiantHandler{
	
	private static void setExperienceValue(EntityLiving living, int amount){
		Class<? extends EntityLiving> livingClass = living.getClass();
		Field field = null;
		try{
			field = livingClass.getField("experienceValue");
			if(!field.isAccessible()) field.setAccessible(true);
			field.setInt(living, amount);
		}
		catch(IllegalAccessException iae){
			iae.printStackTrace();
		}
		catch(NoSuchFieldException nsfe){
			nsfe.printStackTrace();
		}
		catch(SecurityException se){
			se.printStackTrace();
		}
	}
	
	@SubscribeEvent
	public static void onRenderGiantFromDistance(RenderLivingEvent<EntityGiantZombie> evt){
		EntityPlayer player = Minecraft.getMinecraft().player;
		if(evt.getEntity() instanceof EntityGiantZombie){
			EntityGiantZombie giant = (EntityGiantZombie)evt.getEntity();
			RenderLivingBase<EntityGiantZombie> renderer = evt.getRenderer();
			if(player.getDistanceSq(giant) <= ConfigBase.giantRenderDistance){
				Vec3d pos = giant.getPositionVector();
				renderer.doRender(giant, pos.x, pos.y, pos.z, giant.rotationYaw, evt.getPartialRenderTick());
			}
		}
	}
	
	@SubscribeEvent
	public static void onGiantSpawn(LivingSpawnEvent.CheckSpawn evt){
		if(ConfigBase.spawnGiants){
			Entity entity = evt.getEntity();
			if(entity instanceof EntityGiantZombie){
				EntityGiantZombie giant = (EntityGiantZombie)entity;
				EntityAITasks tasks = giant.tasks, targetTasks = giant.targetTasks;
				tasks.addTask(0, new EntityAISwimming(giant));
				tasks.addTask(2, new EntityAIAttackMelee(giant, 1d, false));
				tasks.addTask(5, new EntityAIMoveTowardsRestriction(giant, 1d));
				tasks.addTask(7, new EntityAIWander(giant, 1d));
				tasks.addTask(8, new EntityAIWatchClosest(giant, EntityPlayer.class, 32f));
				tasks.addTask(8, new EntityAILookIdle(giant));
				targetTasks.addTask(1, new EntityAIHurtByTarget(giant, false));
				targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityPlayer>(giant, EntityPlayer.class, true));
				setExperienceValue(giant, 18);
				evt.setResult(Result.ALLOW);
			}
			else evt.setResult(Result.DEFAULT);
		}
		else evt.setResult(Result.DENY);
	}
	
}
