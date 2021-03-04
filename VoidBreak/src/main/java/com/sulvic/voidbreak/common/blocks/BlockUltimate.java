package com.sulvic.voidbreak.common.blocks;

import com.sulvic.core.common.FolkrumTabs;
import com.sulvic.voidbreak.ReferenceVB;
import com.sulvic.voidbreak.common.ChromuzokObjects.EnumUltimateObject;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.projectile.EntityDragonFireball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockUltimate extends Block{
	
	public BlockUltimate(EnumUltimateObject object){
		super(Material.IRON);
		setCreativeTab(FolkrumTabs.BLOCKS);
		setHardness(12.5f * object.getMultiplier());
		setResistance(4e8f * object.getMultiplier());
		setHarvestLevel("pickaxe", 4 + object.ordinal());
		setRegistryName(ReferenceVB.MODID, object.getName());
		setSoundType(SoundType.METAL);
		setUnlocalizedName(object.getName());
	}
	
	public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity){
		return !(entity instanceof EntityWither) && !(entity instanceof EntityWitherSkull) && !(entity instanceof EntityDragon) && !(entity instanceof EntityDragonFireball);
	}
	
}
