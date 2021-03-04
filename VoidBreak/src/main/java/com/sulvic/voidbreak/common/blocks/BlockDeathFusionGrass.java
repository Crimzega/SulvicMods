package com.sulvic.voidbreak.common.blocks;

import java.util.Random;

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
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class BlockDeathFusionGrass extends Block{
	
	public BlockDeathFusionGrass(){
		super(Material.GROUND);
		setCreativeTab(FolkrumTabs.BLOCKS);
		setHardness(120f);
		setHarvestLevel("shovel", 4);
		setLightLevel(0.6f);
		setRegistryName(ReferenceVB.MODID, "death_fusion_grass");
		setResistance(800000f);
		setSoundType(SoundType.CLOTH);
		setTickRandomly(true);
		setUnlocalizedName("deathFusion.grass");
	}
	
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable){ return plantable == ChromuzokObjects.DEATH_FUSION_SAPLING; }
	
	public int tickRate(World world){ return 2; }
	
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity){
		if(entity instanceof EntityPlayer) DeathFusionCapabilities.onPlayerCollided((EntityPlayer)entity);
		if(entity instanceof EntityLivingBase) DeathFusionCapabilities.onEntityCollided((EntityLivingBase)entity);
	}
	
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand){
		if(!world.isRemote){
			if(!world.isAreaLoaded(pos, 3)) return;
			BlockPos above = pos.up();
			if(world.getBlockState(above).getBlock() == ChromuzokObjects.DEATH_FUSION_DIRT){
				world.setBlockState(pos, ChromuzokObjects.DEATH_FUSION_DIRT.getDefaultState());
				world.setBlockState(above, ChromuzokObjects.DEATH_FUSION_GRASS.getDefaultState());
				return;
			}
			if(world.getLightFromNeighbors(above) < 4 && world.getBlockState(above).getLightOpacity(world, above) > 1 && world.getBlockState(pos.up()) != Blocks.AIR.getDefaultState())
				world.setBlockState(pos, ChromuzokObjects.DEATH_FUSION_DIRT.getDefaultState());
			else if(world.getLightFromNeighbors(above) >= 4) for(int i = 0; i < 4; ++i){
				BlockPos pos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
				if(pos1.getY() >= 0 && pos1.getY() < 256 && !world.isBlockLoaded(pos1)) return;
				IBlockState state1 = world.getBlockState(pos1.up());
				IBlockState state2 = world.getBlockState(pos1);
				if(state2.getBlock() == ChromuzokObjects.DEATH_FUSION_DIRT && world.getLightFromNeighbors(pos1.up()) >= 4 && state1.getLightOpacity(world, above) <= 2)
					world.setBlockState(pos1, getDefaultState());
			}
		}
	}
	
}
