package com.sulvic.core.common.block.tank;

import com.sulvic.core.ReferenceSC;
import com.sulvic.core.common.FolkrumTabs;
import com.sulvic.core.common.SulvicObjects;
import com.sulvic.core.common.SulvicObjects.*;
import com.sulvic.core.common.tileentity.TileSulvicTank;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockTankHandler extends BlockContainer implements ISulvicTankMain{
	
	protected static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public BlockTankHandler(){
		super(Material.PISTON);
		setCreativeTab(FolkrumTabs.BLOCKS);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		setHardness(8f);
		setHarvestLevel("wrench", 0);
		setRegistryName(ReferenceSC.MODID, "tank_entry");
		setResistance(24f);
		setSoundType(SoundType.STONE);
		setUnlocalizedName("tank.handler");
	}
	
	public TileEntity createNewTileEntity(World world, int meta){ return new TileSulvicTank(); }
	
	public EnumTankSide getSide(){ return SulvicObjects.EnumTankSide.FRONT; }
	
}
