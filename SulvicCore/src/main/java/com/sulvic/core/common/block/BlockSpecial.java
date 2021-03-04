package com.sulvic.core.common.block;

import com.sulvic.core.ReferenceSC;
import com.sulvic.core.common.FolkrumTabs;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockSpecial extends Block{
	
	public BlockSpecial(){
		super(Material.IRON);
		setCreativeTab(FolkrumTabs.BLOCKS);
		setHardness(7f);
		setHarvestLevel("pickaxe", 3);
		setRegistryName(ReferenceSC.MODID, "special");
		setResistance(21f);
		setSoundType(SoundType.METAL);
		setUnlocalizedName("special");
	}
	
}
