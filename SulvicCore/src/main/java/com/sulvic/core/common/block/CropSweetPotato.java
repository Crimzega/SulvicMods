package com.sulvic.core.common.block;

import com.sulvic.core.ReferenceSC;
import com.sulvic.core.common.SulvicObjects;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class CropSweetPotato extends BlockCrops{
	
	private static final AxisAlignedBB[] SWEET_POTATO_AABB = {new AxisAlignedBB(0d, 0d, 0d, 1d, 0.125d, 1d), new AxisAlignedBB(0d, 0d, 0d, 1d, 0.1875d, 1d), new AxisAlignedBB(0d, 0d, 0d, 1d, 0.25d, 1d),
		new AxisAlignedBB(0d, 0D, 0d, 1d, 0.3125d, 1d), new AxisAlignedBB(0d, 0d, 0d, 1d, 0.375d, 1d), new AxisAlignedBB(0d, 0d, 0d, 1d, 0.4375d, 1d), new AxisAlignedBB(0d, 0d, 0d, 1d, 0.5d, 1d),
		new AxisAlignedBB(0d, 0d, 0d, 1d, 0.5625d, 1d)};
	
	public CropSweetPotato(){
		super();
		setRegistryName(ReferenceSC.MODID, "sweet_potatoes");
		setUnlocalizedName("sweetPotatoes");
	}
	
	protected Item getSeed(){ return SulvicObjects.SWEET_POTATO; }
	
	protected Item getCrop(){ return SulvicObjects.SWEET_POTATO; }
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){ return SWEET_POTATO_AABB[state.getValue(getAgeProperty()).intValue()]; }
	
}
