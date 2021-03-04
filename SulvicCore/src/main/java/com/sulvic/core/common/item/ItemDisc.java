package com.sulvic.core.common.item;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;
import com.sulvic.core.ReferenceSC;
import com.sulvic.core.common.FolkrumTabs;
import com.sulvic.core.common.RegibaRarities;
import com.sulvic.core.lib.GrehzkinStats;
import com.sulvic.core.proxy.AlvontixClient;

import net.minecraft.block.BlockJukebox;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IRarity;

public class ItemDisc extends Item{
	
	private static final Map<SoundEvent, ItemDisc> DISCS = Maps.newHashMap();
	private final SoundEvent discSound;
	private final String discName;
	
	public ItemDisc(String name, SoundEvent sound){
		setCreativeTab(FolkrumTabs.DISCS);
		setRegistryName(ReferenceSC.MODID, "disc_" + name);
		discName = name;
		discSound = sound;
		DISCS.put(sound, this);
	}
	
	@Nullable
	@AlvontixClient
	public static ItemDisc getBySound(SoundEvent sound){ return DISCS.get(sound); }
	
	public IRarity getForgeRarity(ItemStack stack){ return RegibaRarities.ADVANCED; }
	
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
		IBlockState state = world.getBlockState(pos);
		if(state.getBlock() == Blocks.JUKEBOX && !state.getValue(BlockJukebox.HAS_RECORD).booleanValue()){
			if(!world.isRemote){
				ItemStack stack = player.getHeldItem(hand);
				((BlockJukebox)Blocks.JUKEBOX).insertRecord(world, pos, state, stack);
				world.playEvent((EntityPlayer)null, 1010, pos, Item.getIdFromItem(this));
				stack.shrink(1);
				player.addStat(GrehzkinStats.DISC_PLAYED);
			}
			return EnumActionResult.SUCCESS;
		}
		else return EnumActionResult.PASS;
	}
	
	public EnumRarity getRarity(ItemStack stack){ return EnumRarity.RARE; }
	
	@AlvontixClient
	public String getDiscNameLocal(){ return I18n.format(discName); }
	
	@AlvontixClient
	public SoundEvent getSound(){ return discSound; }
	
	@AlvontixClient
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag){ tooltip.add(getDiscNameLocal()); }
	
}
