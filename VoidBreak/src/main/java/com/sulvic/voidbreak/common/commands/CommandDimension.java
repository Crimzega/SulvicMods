package com.sulvic.voidbreak.common.commands;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;

public class CommandDimension extends CommandBase{
	
	public int getRequiredPermissionLevel(){ return super.getRequiredPermissionLevel(); }
	
	public static List<String> getListOfDimensionIds(){
		List<String> result = Lists.newArrayList();
		for(DimensionType type: DimensionType.values()) result.add(String.format("%d", type.getId()));
		return result;
	}
	
	public static DimensionType getDimensionType(Entity entity, String input) throws NumberInvalidException{
		int dimId = parseInt(input);
		DimensionType newDim = DimensionType.getById(dimId);
		if(newDim == null) newDim = DimensionType.getById(entity.dimension);
		return newDim;
	}
	
	public String getName(){ return "dimension"; }
	
	public String getUsage(ICommandSender sender){ return "commands.dimension.usage"; }
	
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException{
		if(args.length < 1) throw new WrongUsageException("commands.dimension.usage");
		else{
			Entity entity = getEntity(server, sender, args[0]);
			if(entity.world != null){
				DimensionType newDim = getDimensionType(entity, args[1]), curr = DimensionType.getById(entity.dimension);
				if(newDim != curr){
					doDimensionTeleport(entity, curr, newDim);
					notifyCommandListener(sender, this, "commands.dimension.success.dimension", entity.getName(), newDim.getName());
				}
			}
		}
	}
	
	public void doDimensionTeleport(Entity teleportingEntity, DimensionType from, DimensionType to){
		WorldProvider fromWP = from.createDimension(), toWP = to.createDimension();
		double newFactor = fromWP.getMovementFactor() / toWP.getMovementFactor();
		double x = teleportingEntity.posX * newFactor, y = teleportingEntity.posY, z = teleportingEntity.posZ * newFactor;
		if(teleportingEntity instanceof EntityPlayerMP){
			teleportingEntity.dismountRidingEntity();
			((EntityPlayerMP)teleportingEntity).connection.setPlayerLocation(x, y, z, teleportingEntity.rotationYaw, teleportingEntity.rotationPitch);
			teleportingEntity.setRotationYawHead(teleportingEntity.rotationYaw);
		}
		teleportingEntity.changeDimension(to.getId());
		teleportingEntity.setPosition(x, y, z);
		if(!(teleportingEntity instanceof EntityLivingBase) || !((EntityLivingBase)teleportingEntity).isElytraFlying()){
			teleportingEntity.motionY = 0d;
			teleportingEntity.onGround = true;
		}
	}
	
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos){
		return args.length == 1? getListOfDimensionIds(): args.length > 1? getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()): Collections.emptyList();
	}
	
}
