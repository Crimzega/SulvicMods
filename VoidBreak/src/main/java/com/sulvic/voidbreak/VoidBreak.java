package com.sulvic.voidbreak;

import com.sulvic.core.logging.MezidaLogger;
import com.sulvic.core.proxy.AlvontixProxy;
import com.sulvic.voidbreak.common.ChromuzokObjects;
import com.sulvic.voidbreak.world.gen.GenTypes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ReferenceVB.MODID, name = ReferenceVB.NAME, version = ReferenceVB.VERSION, dependencies = "required-after:sulviccore")
public class VoidBreak{
	
	@Instance(ReferenceVB.MODID)
	public static VoidBreak modInstance;
	@SidedProxy(clientSide = ReferenceVB.CLIENT, serverSide = ReferenceVB.SERVER)
	public static AlvontixProxy modProxy;
	public MezidaLogger logger;
	
	public VoidBreak(){
		logger = new MezidaLogger(ReferenceVB.NAME);
		logger.info("The \"{}\" logger has been created. Now all additional bullshit can be used.", ReferenceVB.MODID);
	}
	
	public static MezidaLogger getLogger(){ return modInstance.logger; }
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent evt){
		for(Block block: Block.REGISTRY) logger.info("{}, ID: {}", block.toString(), Item.getIdFromItem(Item.getItemFromBlock(block)));
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent evt){
		logger.info("Attempting to preinitialize mod contents.");
		ChromuzokObjects.addToRegistry();
		GenTypes.init();
	}
	
}
