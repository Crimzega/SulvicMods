package com.sulvic.core.common.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;

import com.sulvic.core.ReferenceSC;
import com.sulvic.core.common.FolkrumTabs;
import com.sulvic.core.common.tileentity.TileSpecialWorkbench;

public class BlockSpecialWorkbench extends BlockContainer{
	
	public BlockSpecialWorkbench(){
		super(Material.PISTON);
		setCreativeTab(FolkrumTabs.BLOCKS);
		setHardness(50f);
		setHarvestLevel("pickaxe", 2);
		setRegistryName(ReferenceSC.MODID, "special_workbench");
		setSoundType(SoundType.METAL);
		setUnlocalizedName("specialWorkbench");
	}
	
	public EnumBlockRenderType getRenderType(IBlockState state){ return EnumBlockRenderType.MODEL; }
	
	public TileEntity createNewTileEntity(World world, int meta){ return new TileSpecialWorkbench(world); }
	
}
