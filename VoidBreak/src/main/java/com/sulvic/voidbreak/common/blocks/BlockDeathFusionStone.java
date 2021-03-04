package com.sulvic.voidbreak.common.blocks;

import com.sulvic.core.common.FolkrumTabs;
import com.sulvic.voidbreak.ReferenceVB;
import com.sulvic.voidbreak.lib.DeathFusionCapabilities;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockDeathFusionStone extends Block{
	
	public BlockDeathFusionStone(){
		super(Material.ROCK);
		setCreativeTab(FolkrumTabs.BLOCKS);
		setHardness(180f);
		setHarvestLevel("pickaxe", 4);
		setLightLevel(0.6f);
		setRegistryName(ReferenceVB.MODID, "death_fusion_stone");
		setResistance(800000f);
		setSoundType(SoundType.CLOTH);
		setUnlocalizedName("deathFusion.stone");
	}
	
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity){
		if(entity instanceof EntityPlayer) DeathFusionCapabilities.onPlayerCollided((EntityPlayer)entity);
		else if(entity instanceof EntityLivingBase) DeathFusionCapabilities.onEntityCollided((EntityLivingBase)entity);
	}
	
}
