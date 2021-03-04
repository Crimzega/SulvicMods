package com.sulvic.core.client.models;

import com.sulvic.core.proxy.AlvontixClient;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@AlvontixClient
public class ModelSulvicSorter extends ModelBase{
	
	public ModelRenderer base, output, input;
	
	public ModelSulvicSorter(){
		textureWidth = 68;
		textureHeight = 46;
		
		base = new ModelRenderer(this, 0, 0).setTextureSize(textureWidth, textureHeight);
		base.addBox(-8f, -16f, -8f, 16, 16, 16);
		base.setRotationPoint(0f, 24f, 0f);
		setRotation(base, 0f, 0f, 0f);
		output = new ModelRenderer(this, 0, 0).setTextureSize(textureWidth, textureHeight);
		output.addBox(-3f, -11f, -9f, 6, 6, 2);
		output.setRotationPoint(0, 24f, 0f);
		setRotation(output, 0f, 0f, 0f);
		input = new ModelRenderer(this).setTextureSize(textureWidth, textureHeight);
		input.setTextureOffset(38, 0).addBox("base", -5f, -12f, -5f, 10, 0, 10);
		input.setTextureOffset(-14, 32).addBox("top", -7f, -14f, -7f, 14, 0, 14);
		input.setTextureOffset(28, 32).addBox("sideN", -5f, -16f, -5f, 10, 4, 0);
		input.setTextureOffset(28, 26).addBox("sideW", -5f, -16f, -5f, 0, 4, 10);
		input.setTextureOffset(38, 32).addBox("sideS", -5f, -16f, 5f, 10, 4, 0);
		input.setTextureOffset(38, 26).addBox("sideE", 5f, -16f, -5f, 0, 4, 10);
		input.setRotationPoint(0f, 0f, 0f);
		setRotation(input, 0f, 0f, 0f);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scale){
		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch, scale);
		setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch, scale, entity);
		base.render(scale);
		output.render(scale);
		input.render(scale);
	}
	
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scale, Entity entity){
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch, scale, entity);
	}
	
}
