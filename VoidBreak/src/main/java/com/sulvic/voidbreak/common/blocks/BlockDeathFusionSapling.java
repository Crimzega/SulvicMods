package com.sulvic.voidbreak.common.blocks;

import java.util.Random;

import com.sulvic.core.common.FolkrumTabs;
import com.sulvic.voidbreak.ReferenceVB;
import com.sulvic.voidbreak.common.ChromuzokObjects;
import com.sulvic.voidbreak.world.gen.WorldGenDeathFusionTree;

import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BlockDeathFusionSapling extends BlockBush implements IGrowable{
	
	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
	private static final AxisAlignedBB SAPLING_BOUNDS = new AxisAlignedBB(0.09999999403953552d, 0d, 0.09999999403953552d, 0.8999999761581421d, 0.800000011920929d, 0.8999999761581421d);
	
	public BlockDeathFusionSapling(){
		super();
		setCreativeTab(FolkrumTabs.BLOCKS);
		setHardness(90f);
		setRegistryName(ReferenceVB.MODID, "death_fusion_sapling");
		setResistance(800000f);
		setLightLevel(0.6f);
		setSoundType(SoundType.CLOTH);
		setUnlocalizedName("deathFusion.sapling");
	}
	
	protected boolean canSustainBush(IBlockState state){ return state.getBlock() == ChromuzokObjects.DEATH_FUSION_GRASS || state.getBlock() == ChromuzokObjects.DEATH_FUSION_DIRT; }
	
	protected BlockStateContainer createBlockState(){ return new BlockStateContainer(this, STAGE); }
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){ return SAPLING_BOUNDS; }
	
	public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean client){ return true; }
	
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state){ return world.rand.nextFloat() < 0.2145f; }
	
	public IBlockState getStateFromMeta(int metadata){ return getDefaultState().withProperty(STAGE, (metadata & 8) >> 3); }
	
	public int getMetaFromState(IBlockState state){ return 0 | state.getValue(STAGE).intValue() << 3; }
	
	public void generateTree(World world, BlockPos pos, IBlockState state, Random rand){
		if(!TerrainGen.saplingGrowTree(world, rand, pos)) return;
		WorldGenerator gen = new WorldGenDeathFusionTree(true);
		world.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);
		gen.generate(world, rand, pos);
	}
	
	public void grow(World world, Random rand, BlockPos pos, IBlockState state){
		if(state.getValue(STAGE).intValue() == 0) world.setBlockState(pos, state.cycleProperty(STAGE), 4);
		else generateTree(world, pos, state, rand);
	}
	
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand){
		if(!world.isRemote){
			super.updateTick(world, pos, state, rand);
			if(world.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(19) <= 3) grow(world, rand, pos, state);
		}
	}
	
}
