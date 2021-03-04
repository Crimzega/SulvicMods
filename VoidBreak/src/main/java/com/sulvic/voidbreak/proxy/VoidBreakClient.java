package com.sulvic.voidbreak.proxy;

import static com.sulvic.core.client.renders.ObjectHelper.*;

import com.sulvic.core.proxy.AlvontixClient;
import com.sulvic.core.proxy.AlvontixProxy;
import com.sulvic.voidbreak.common.ChromuzokObjects;

public class VoidBreakClient extends AlvontixProxy{

	@AlvontixClient
	public void registerItemColors(){}

	@AlvontixClient
	public void registerModels(){
		registerBlockModel(ChromuzokObjects.ULTIMANIUM_BLOCK);
		registerBlockModel(ChromuzokObjects.ULTIMEX_BLOCK);
		registerBlockModel(ChromuzokObjects.ULTEZ_BLOCK);
		registerBlockModel(ChromuzokObjects.DEATH_FUSION_GRASS);
		registerBlockModel(ChromuzokObjects.DEATH_FUSION_DIRT);
		registerBlockModel(ChromuzokObjects.DEATH_FUSION_STONE);
		registerBlockModel(ChromuzokObjects.DEATH_FUSION_SAPLING);
		registerBlockModel(ChromuzokObjects.DEATH_FUSION_LOG);
		registerBlockModel(ChromuzokObjects.DEATH_FUSION_LEAVES);
	}
	
	@AlvontixClient
	public void setFancyGraphics(boolean fancy){ ChromuzokObjects.DEATH_FUSION_LEAVES.setGraphicsLevel(fancy); }
	
}
