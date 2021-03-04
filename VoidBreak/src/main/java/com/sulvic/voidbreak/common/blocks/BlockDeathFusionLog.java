package com.sulvic.voidbreak.common.blocks;

import com.sulvic.core.common.FolkrumTabs;
import com.sulvic.voidbreak.ReferenceVB;
import com.sulvic.voidbreak.lib.DeathFusionCapabilities;

import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDeathFusionLog extends BlockLog{
	
	public BlockDeathFusionLog(){
		super();
		setCreativeTab(FolkrumTabs.BLOCKS);
		// (getDefaultState().withProperty(LOG_AXIS, EnumAxis.Y));
		setHardness(240f);
		setHarvestLevel("axe", 4);
		setLightLevel(0.6f);
		setRegistryName(ReferenceVB.MODID, "death_fusion_log");
		setResistance(800000f);
		setSoundType(SoundType.CLOTH);
		setUnlocalizedName("deathFusion.log");
	}
	
	protected BlockStateContainer createBlockState(){ return new BlockStateContainer(this, LOG_AXIS); }
	
	public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos){ return true; }
	
	protected ItemStack getSilkTouchDrop(IBlockState state){ return new ItemStack(this, 1); }
	
	public IBlockState getStateFromMeta(int metadata){
		IBlockState state = getDefaultState();
		switch(metadata & 12){
			case 0:
				state.withProperty(LOG_AXIS, EnumAxis.Y);
			case 4:
				state.withProperty(LOG_AXIS, EnumAxis.X);
			case 8:
				state.withProperty(LOG_AXIS, EnumAxis.Z);
			default:
				state.withProperty(LOG_AXIS, EnumAxis.NONE);
		}
		return state;
	}
	
	public int damageDropped(IBlockState state){ return 0; }
	
	public int getMetaFromState(IBlockState state){
		int metadata = 0;
		switch(state.getValue(LOG_AXIS)){
			case X:
				metadata |= 4;
			break;
			case Z:
				metadata |= 8;
			break;
			case NONE:
				metadata |= 12;
			break;
			default:
			break;
		}
		return metadata;
	}
	
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos){ return MapColor.BLACK; }
	
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list){ if(tab == FolkrumTabs.BLOCKS) list.add(new ItemStack(this)); }
	
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity){
		if(entity instanceof EntityPlayer) DeathFusionCapabilities.onPlayerCollided((EntityPlayer)entity);
		else if(entity instanceof EntityLivingBase) DeathFusionCapabilities.onEntityCollided((EntityLivingBase)entity);
	}
	
}
