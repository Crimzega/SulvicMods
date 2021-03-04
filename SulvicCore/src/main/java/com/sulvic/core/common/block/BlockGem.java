package com.sulvic.core.common.block;

import com.sulvic.core.ReferenceSC;
import com.sulvic.core.common.FolkrumTabs;
import com.sulvic.core.common.SulvicObjects;
import com.sulvic.core.util.ModEnumHelper;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGem extends Block{
	
	public static final PropertyEnum<SulvicObjects.EnumGem> TYPE = ModEnumHelper.gemProperty();
	
	public BlockGem(){
		super(Material.ROCK);
		setCreativeTab(FolkrumTabs.BLOCKS);
		setDefaultState(blockState.getBaseState().withProperty(TYPE, SulvicObjects.EnumGem.RUBY));
		setHardness(5f);
		setHarvestLevel("pickaxe", 2);
		setRegistryName(ReferenceSC.MODID, "gemstones");
		setResistance(15f);
		setUnlocalizedName("gem");
	}
	
	protected BlockStateContainer createBlockState(){ return new BlockStateContainer(this, TYPE); }
	
	public IBlockState getStateFromMeta(int metadata){ return getDefaultState().withProperty(TYPE, SulvicObjects.EnumGem.byMetadata(metadata)); }
	
	public boolean isBeaconBase(IBlockAccess access, BlockPos pos, BlockPos beacon){ return true; }
	
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player){
		IBlockState state1 = world.getBlockState(target.getBlockPos());
		return new ItemStack(this, 1, getMetaFromState(state1));
	}
	
	public int damageDropped(IBlockState state){ return ((SulvicObjects.EnumGem)state.getValue(TYPE)).getMetadata(); }
	
	public int getMetaFromState(IBlockState state){ return ((SulvicObjects.EnumGem)state.getValue(TYPE)).getMetadata(); }
	
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> blocks){ if(tab.equals(FolkrumTabs.BLOCKS)) for(int i = 0; i < SulvicObjects.EnumGem.size(); i++) blocks.add(new ItemStack(this, 1, i)); }
	
}
