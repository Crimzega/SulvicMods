package com.sulvic.core.common.item;

import java.util.Set;

import com.sulvic.core.ReferenceSC;
import com.sulvic.core.api.WrenchAPI;
import com.sulvic.core.api.WrenchBlock;
import com.sulvic.core.common.FolkrumTabs;
import com.sulvic.core.util.ModEnumHelper;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemWrench extends ItemTool{
	
	private static final ToolMaterial WRENCH_MAT = ModEnumHelper.buildWrenchMat();
	private static final Set<Block> BLOCKS = WrenchAPI.getBlocks();
	
	public ItemWrench(){
		super(WRENCH_MAT, BLOCKS);
		setCreativeTab(FolkrumTabs.TOOLS);
		setHarvestLevel("wrench", WRENCH_MAT.getHarvestLevel());
		setMaxStackSize(1);
		setRegistryName(ReferenceSC.MODID, "wrench");
		setUnlocalizedName("wrench");
	}
	
	private boolean isWrenchable(Block block){ return BLOCKS.contains(block); }
	
	private boolean useWrench(Block block, World world, BlockPos pos, IBlockState state){
		try{
			ItemStack stack = new ItemStack(block, 1, block.getMetaFromState(state));
			TileEntity tile = world.getTileEntity(pos);
			if(tile != null){
				NBTTagCompound nbtCompound = new NBTTagCompound();
				tile.writeToNBT(nbtCompound);
				stack.setTagInfo("BlockEntityData", nbtCompound);
				world.setTileEntity(pos, (TileEntity)null);
			}
			world.setBlockState(pos, Blocks.AIR.getDefaultState());
			Block.spawnAsEntity(world, pos, stack);
			return true;
		}
		catch(Exception e){
			return false;
		}
		
	}
	
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand){
		ItemStack heldItem = player.getHeldItem(hand);
		if(world.isRemote) return EnumActionResult.SUCCESS;
		else if(!player.canPlayerEdit(pos.offset(side), side, heldItem)) return EnumActionResult.FAIL;
		else {
			IBlockState state = world.getBlockState(pos);
			Block block = state.getBlock();
			if(isWrenchable(block)){
				WrenchBlock.Damage damage = WrenchAPI.getDamage(block);
				if(!useWrench(block, world, pos, state)) return EnumActionResult.PASS;
				if(!player.capabilities.isCreativeMode) heldItem.damageItem(damage.getRandDamage(), player);
				return EnumActionResult.SUCCESS;
			}
			else return EnumActionResult.PASS;
		}
	}
	
//	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
//		ItemStack heldItem = player.getHeldItem(hand);
//		setCreativeTab(FolkrumTabs.TOOLS);
//		if(world.isRemote) return EnumActionResult.SUCCESS;
//		else if(!player.canPlayerEdit(pos.offset(facing), facing, heldItem)) return EnumActionResult.FAIL;
//		else{
//			IBlockState state = world.getBlockState(pos);
//			Block block = state.getBlock();
//			if(isWrenchable(block)){
//				WrenchBlock.Damage damage = WrenchAPI.getDamage(block);
//				if(!useWrench(world, pos, state, block)) return EnumActionResult.FAIL;
//				if(!player.capabilities.isCreativeMode) heldItem.damageItem(damage.getRandDamage(), player);
//				else return EnumActionResult.SUCCESS;
//			}
//			return EnumActionResult.PASS;
//		}
//	}
	
}
