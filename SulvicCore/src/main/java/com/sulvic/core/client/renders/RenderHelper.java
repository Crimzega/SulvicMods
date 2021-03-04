package com.sulvic.core.client.renders;

import net.minecraft.client.renderer.block.model.BakedQuad;

public class RenderHelper{
	
	private BakedQuad[] singleQuad = new BakedQuad[1];
	
	public BakedQuad[] getSingleQuad(BakedQuad quad){
		singleQuad[0] = quad;
		return singleQuad;
	}
	
}
