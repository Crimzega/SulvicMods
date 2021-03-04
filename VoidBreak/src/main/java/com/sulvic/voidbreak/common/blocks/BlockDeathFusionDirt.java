package com.sulvic.voidbreak.common.blocks;

import com.sulvic.core.common.FolkrumTabs;
import com.sulvic.voidbreak.ReferenceVB;
import com.sulvic.voidbreak.common.ChromuzokObjects;
import com.sulvic.voidbreak.lib.DeathFusionCapabilities;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class BlockDeathFusionDirt extends Block{
	
	public BlockDeathFusionDirt(){
		super(Material.ROCK);
		setCreativeTab(FolkrumTabs.BLOCKS);
		setHardness(100f);
		setHarvestLevel("shovel", 4);
		setLightLevel(0.6f);
		setRegistryName(ReferenceVB.MODID, "death_fusion_dirt");
		setResistance(800000f);
		setSoundType(SoundType.CLOTH);
		setUnlocalizedName("deathFusion.dirt");
		
	}
	
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable){ return plantable == ChromuzokObjects.DEATH_FUSION_SAPLING; }
	
	public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor){ super.onNeighborChange(world, pos, neighbor); }
	
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player){ super.onBlockHarvested(worldIn, pos, state, player); }
	
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity){
		if(entity instanceof EntityPlayer) DeathFusionCapabilities.onPlayerCollided((EntityPlayer)entity);
		if(entity instanceof EntityLivingBase) DeathFusionCapabilities.onEntityCollided((EntityLivingBase)entity);
	}
	
}
