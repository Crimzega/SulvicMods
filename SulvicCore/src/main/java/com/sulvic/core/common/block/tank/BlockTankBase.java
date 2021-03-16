package com.sulvic.core.common.block.tank;

import com.sulvic.core.ReferenceSC;
import com.sulvic.core.common.FolkrumTabs;
import com.sulvic.core.common.SulvicObjects;
import com.sulvic.core.common.SulvicObjects.EnumTankSide;
import com.sulvic.core.common.SulvicObjects.ISulvicTankPiece;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockTankBase extends Block implements ISulvicTankPiece{
	
	public BlockTankBase(){
		super(Material.PISTON);
		setCreativeTab(FolkrumTabs.BLOCKS);
		setHardness(8f);
		setHarvestLevel("wrench", 0);
		setRegistryName(ReferenceSC.MODID, "tank_base");
		setSoundType(SoundType.STONE);
		setUnlocalizedName("tank.base");
	}
	
	public EnumTankSide getSide(){ return SulvicObjects.EnumTankSide.UNIVERSAL; }
	
}
