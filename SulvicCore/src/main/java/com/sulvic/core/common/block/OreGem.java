package com.sulvic.core.common.block;

import static com.sulvic.util.SulvicMath.maxInt;
import static com.sulvic.util.SulvicMath.rangedInt;

import java.util.Random;

import com.sulvic.core.ReferenceSC;
import com.sulvic.core.common.FolkrumTabs;
import com.sulvic.core.common.SulvicObjects;
import com.sulvic.core.util.ModEnumHelper;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class OreGem extends Block{
	
	public static final PropertyEnum<SulvicObjects.EnumGem> TYPE = ModEnumHelper.gemProperty();
	
	public OreGem(){
		super(Material.ROCK);
		setCreativeTab(FolkrumTabs.BLOCKS);
		setDefaultState(blockState.getBaseState().withProperty(TYPE, SulvicObjects.EnumGem.RUBY));
		setHardness(3f);
		setHarvestLevel("pickaxe", 2);
		setRegistryName(ReferenceSC.MODID, "gemstone_ores");
		setResistance(5f);
		setSoundType(SoundType.METAL);
		setUnlocalizedName("gemOre");
	}
	
	@Override
	protected BlockStateContainer createBlockState(){ return new BlockStateContainer(this, TYPE); }
	
	@Override
	public IBlockState getStateFromMeta(int metadata){ return getDefaultState().withProperty(TYPE, SulvicObjects.EnumGem.byMetadata(metadata)); }
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune){ return SulvicObjects.GEMS; }
	
	public int quantityDroppedWithBonus(int fortune, Random random){
		return fortune > 0 && getItemDropped(getBlockState().getValidStates().iterator().next(), random, fortune) != Item.getItemFromBlock(this)?
			quantityDropped(random) * (maxInt(rangedInt(1, fortune + 2), 0) + 1): quantityDropped(random);
	}
	
	@Override
	public int damageDropped(IBlockState state){ return ((SulvicObjects.EnumGem)state.getValue(TYPE)).getMetadata(); }
	
	@Override
	public int getMetaFromState(IBlockState state){ return ((SulvicObjects.EnumGem)state.getValue(TYPE)).getMetadata(); }
	
	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> blocks){ if(tab == FolkrumTabs.BLOCKS) for(int i = 0; i < SulvicObjects.EnumGem.size(); i++) blocks.add(new ItemStack(this, 1, i)); }
	
}
