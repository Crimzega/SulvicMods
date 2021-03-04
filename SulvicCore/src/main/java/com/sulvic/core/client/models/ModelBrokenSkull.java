package com.sulvic.core.client.models;

import static com.sulvic.util.SulvicMath.getRotationFloat;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import com.sulvic.core.proxy.AlvontixClient;

@AlvontixClient
public class ModelBrokenSkull extends ModelBase{
	
	public ModelRenderer head, jaw, chest, gut, armL, armR, legL, legR;
	
	public ModelBrokenSkull(){
		textureWidth = 92;
		textureHeight = 50;
		head = new ModelRenderer(this, 0, 0).setTextureSize(textureWidth, textureHeight).addBox(-5f, -8f, -10f, 10, 8, 10);
		head.setRotationPoint(0f, -13f, -6f);
		setRotation(head, 0f, 0f, 0f);
		jaw = new ModelRenderer(this, 0, 18).setTextureSize(textureWidth, textureHeight).addBox(-5f, 0f, -10f, 10, 2, 10);
		jaw.setRotationPoint(0f, -13f, -6f);
		setRotation(jaw, getRotationFloat(25f), 0f, 0f);
		chest = new ModelRenderer(this, 40, 0).setTextureSize(textureWidth, textureHeight).addBox(-8f, -6f, -5f, 16, 12, 10);
		chest.setRotationPoint(0f, -8f, -3f);
		setRotation(chest, getRotationFloat(20f), 0f, 0f);
		armL = new ModelRenderer(this, 44, 22).setTextureSize(textureWidth, textureHeight).addBox(-2f, -3f, -3f, 4, 22, 6);
		armL.setRotationPoint(10f, -12f, -5f);
		armL.mirror = true;
		armR = new ModelRenderer(this, 44, 22).setTextureSize(textureWidth, textureHeight).addBox(-2f, -3f, -3f, 4, 22, 6);
		armR.setRotationPoint(-10f, -12f, -5f);
		gut = new ModelRenderer(this, 0, 30).setTextureSize(textureWidth, textureHeight).addBox(-7f, -5f, -4f, 14, 10, 8);
		gut.setRotationPoint(0f, 1f, 0f);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn){
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
	}
	
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale){
		super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		head.render(scale);
		jaw.render(scale);
		chest.render(scale);
		armL.render(scale);
		armR.render(scale);
		gut.render(scale);
		legL.render(scale);
		legR.render(scale);
	}
	
}
