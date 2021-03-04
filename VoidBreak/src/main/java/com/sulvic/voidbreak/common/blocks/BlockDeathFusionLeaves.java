package com.sulvic.voidbreak.common.blocks;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.sulvic.core.common.FolkrumTabs;
import com.sulvic.voidbreak.ReferenceVB;
import com.sulvic.voidbreak.common.ChromuzokObjects;
import com.sulvic.voidbreak.lib.DeathFusionCapabilities;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDeathFusionLeaves extends BlockLeaves{
	
	public BlockDeathFusionLeaves(){
		super();
		setCreativeTab(FolkrumTabs.BLOCKS);
		setGraphicsLevel(Minecraft.getMinecraft().gameSettings.fancyGraphics);
		setHardness(110f);
		setHarvestLevel("axe", 4);
		setLightLevel(0.6f);
		setRegistryName(ReferenceVB.MODID, "death_fusion_leaves");
		setResistance(800000f);
		setSoundType(SoundType.CLOTH);
		setUnlocalizedName("deathFusion.leaves");
	}
	
	protected BlockStateContainer createBlockState(){ return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE); }
	
	protected ItemStack getSilkTouchDrop(IBlockState state){ return new ItemStack(this); }
	
	public EnumType getWoodType(int meta){ return null; }
	
	public IBlockState getStateFromMeta(int meta){ return getDefaultState().withProperty(DECAYABLE, (meta & 4) == 0).withProperty(CHECK_DECAY, (meta & 8) > 0); }
	
	public int damageDropped(IBlockState state){ return 0; }
	
	public int getMetaFromState(IBlockState state){
		int metadata = 0;
		if(!state.getValue(DECAYABLE).booleanValue()) metadata |= 4;
		if(!state.getValue(CHECK_DECAY).booleanValue()) metadata |= 8;
		return metadata;
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune){ return Item.getItemFromBlock(ChromuzokObjects.DEATH_FUSION_SAPLING); }
	
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune){ return Lists.newArrayList(new ItemStack(this, 1)); }
	
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list){ if(tab == FolkrumTabs.BLOCKS) list.add(new ItemStack(this)); }
	
	public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tile, ItemStack stack){
		if(!world.isRemote && stack.getItem() == Items.SHEARS) player.addStat(StatList.getBlockStats(this));
		else super.harvestBlock(world, player, pos, state, tile, stack);
	}
	
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity){
		if(entity instanceof EntityPlayer) DeathFusionCapabilities.onPlayerCollided((EntityPlayer)entity);
		else if(entity instanceof EntityLivingBase) DeathFusionCapabilities.onEntityCollided((EntityLivingBase)entity);
	}
	
}
