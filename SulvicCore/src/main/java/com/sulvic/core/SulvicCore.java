package com.sulvic.core;

import org.lwjgl.opengl.Display;

import com.sulvic.core.client.SulvicGui;
import com.sulvic.core.client.config.ConfigWrench;
import com.sulvic.core.common.SulvicObjects;
import com.sulvic.core.common.SulvicObjects.EnumGem;
import com.sulvic.core.common.block.OreSpecial;
import com.sulvic.core.common.tileentity.*;
import com.sulvic.core.compat.*;
import com.sulvic.core.logging.MezidaLogger;
import com.sulvic.core.proxy.AlvontixProxy;
import com.sulvic.core.world.gen.SulvicWorldGeneration;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = ReferenceSC.MODID, name = ReferenceSC.NAME, version = ReferenceSC.VERSION, dependencies = "after:thaumcraft;after:projecte")
public class SulvicCore{
	
	@Instance(ReferenceSC.MODID)
	public static SulvicCore coreInstance;
	@SidedProxy(clientSide = ReferenceSC.CLIENT, serverSide = ReferenceSC.SERVER)
	public static AlvontixProxy modProxy;
	public MezidaLogger logger;
	
	public SulvicCore(){
		Display.setTitle(String.format("%s (%s)", ReferenceSC.NAME, ReferenceSC.VERSION));
		logger = new MezidaLogger(ReferenceSC.NAME);
		logger.info("The \"{}\" logger has been created. Now all it's mod bullshit can be notified. (Yes, I'm aware of my logger, sue me).", ReferenceSC.MODID);
		CompatHelper.attemptResizeMaxStack();
	}
	
	public static MezidaLogger getLogger(){ return coreInstance.logger; }
	
	@EventHandler
	protected void init(FMLInitializationEvent evt){
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new SulvicGui());
		for(EnumGem gem: EnumGem.values()) GameRegistry.registerWorldGenerator(SulvicWorldGeneration.create(gem), 2);
		GameRegistry.registerWorldGenerator(SulvicWorldGeneration.create(new OreSpecial.SpecialVein()), 5);
	}
	
	@EventHandler
	protected void postInit(FMLPostInitializationEvent evt){
		if(CompatHelper.modExists("projecte")) CompatProjE.init();
		if(CompatHelper.modExists("thuamcraft")) CompatThaum.init();
	}
	
	@EventHandler
	protected void preInit(FMLPreInitializationEvent evt){
		logger.info("Attempting to preinitialize mod contents.");
		ConfigWrench.init(evt);
		ConfigWrench config = ConfigWrench.instance;
		SulvicCore.getLogger().info("Is Null: " + (config == null));
		try{
			config.load();
		}
		catch(Exception e){
			logger.warn("The Wrench config could not be created");
			logger.warn(e);
		}
		finally{
			if(config.hasChanged()) config.save();
		}
		CompatHelper.addButtonOreDict();
		SulvicObjects.init();
		SulvicObjects.addToRegistry();
		GameRegistry.addSmelting(SulvicObjects.SPECIAL_DUST, new ItemStack(SulvicObjects.SPECIAL_INGOT), 0.00412f);
		GameRegistry.registerTileEntity(TileSpecialWorkbench.class, new ResourceLocation(ReferenceSC.MODID, "workbench_special"));
		GameRegistry.registerTileEntity(TileSulvicTank.class, new ResourceLocation(ReferenceSC.MODID, "sulvic_tank"));
	}
	
	@EventHandler
	protected void serverStarted(FMLServerStartedEvent evt){}
	
	@EventHandler
	protected void serverStarting(FMLServerStartingEvent evt){}
	
	@EventHandler
	protected void serverStartup(FMLServerAboutToStartEvent evt){}
	
	@EventHandler
	protected void serverStopped(FMLServerStoppedEvent evt){}
	
	@EventHandler
	protected void serverStopping(FMLServerStoppingEvent evt){}
	
}
