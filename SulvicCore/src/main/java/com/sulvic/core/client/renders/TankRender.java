package com.sulvic.core.client.renders;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

@SuppressWarnings("unused")
public class TankRender{
	
	private static TextureAtlasSprite emptySprite = (TextureAtlasSprite)null;
	
	public static BakedQuad[] getTankQuads(IBlockAccess access, IBlockState state, BlockPos pos, BakedQuad quad){
		return (BakedQuad[])null;
	}
	
}
