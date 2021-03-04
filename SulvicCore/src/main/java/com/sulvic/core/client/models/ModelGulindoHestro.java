package com.sulvic.core.client.models;

import static com.sulvic.util.SulvicMath.getRotationFloat;

import com.sulvic.core.proxy.AlvontixClient;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@AlvontixClient
public class ModelGulindoHestro extends ModelBase{
	
	public ModelRenderer head, chest, gut, brachiumL, forearmL, brachiumR, forearmR, thighL, cnemisL, thighR, cnemisR;
	
	public ModelGulindoHestro(){
		textureWidth = 104;
		textureHeight = 112;
		head = new ModelRenderer(this, 0, 0).setTextureSize(textureWidth, textureHeight).addBox(-6f, -12f, -6f, 12, 12, 12);
		head.setRotationPoint(0f, -31.9f, -0.7f);
		setRotation(head, getRotationFloat(10f), 0f, 0f);
		chest = new ModelRenderer(this, 0, 52).setTextureSize(textureWidth, textureHeight).addBox(-11f, 0f, -4f, 22, 16, 14);
		chest.setRotationPoint(0f, -31.3f, -2.2f);
		setRotation(chest, getRotationFloat(10f), 0, 0);
		gut = new ModelRenderer(this, 0, 82).setTextureSize(textureWidth, textureHeight).addBox(-11f, 0f, -11f, 22, 16, 14);
		gut.setRotationPoint(0f, -18f, 7.5f);
		setRotation(gut, getRotationFloat(-15f), 0f, 0f);
		brachiumL = new ModelRenderer(this, 40, 24).setTextureSize(textureWidth, textureHeight).addBox(-2f, -4f, -5f, 10, 18, 10);
		brachiumL.setRotationPoint(13f, -28f, 2f);
		setRotation(brachiumL, getRotationFloat(-5f), 0f, getRotationFloat(-5f));
		brachiumL.mirror = true;
		forearmL = new ModelRenderer(this, 0, 24).setTextureSize(textureWidth, textureHeight).addBox(-2f, 12.2f, -1.6f, 10, 18, 10);
		forearmL.setRotationPoint(13f, -28f, 2f);
		setRotation(forearmL, getRotationFloat(-20f), 0, getRotationFloat(-5f));
		forearmL.mirror = true;
		brachiumR = new ModelRenderer(this, 40, 24).setTextureSize(textureWidth, textureHeight).addBox(-8f, -4f, -5f, 10, 18, 10);
		brachiumR.setRotationPoint(-13f, -28f, 2f);
		setRotation(brachiumR, getRotationFloat(-5f), 0f, getRotationFloat(5f));
		forearmR = new ModelRenderer(this, 0, 24).setTextureSize(textureWidth, textureHeight).addBox(-8f, 12.2f, -1.6f, 10, 18, 10);
		forearmR.setRotationPoint(-13f, -28f, 2f);
		setRotation(forearmR, getRotationFloat(-20f), 0, getRotationFloat(5f));
		thighL = new ModelRenderer(this, 72, 64).setTextureSize(textureWidth, textureHeight).addBox(-4f, 0f, -4f, 8, 16, 8);
		thighL.setRotationPoint(6f, -5f, 0f);
		setRotation(thighL, getRotationFloat(-15f), getRotationFloat(-10f), 0);
		thighL.mirror = true;
		cnemisL = new ModelRenderer(this, 72, 88).setTextureSize(textureWidth, textureHeight).addBox(-4f, 11.8f, -11.4f, 8, 16, 8);
		cnemisL.setRotationPoint(6f, -5f, 0f);
		setRotation(cnemisL, getRotationFloat(15f), getRotationFloat(-10f), 0f);
		cnemisL.mirror = true;
		thighR = new ModelRenderer(this, 72, 64).setTextureSize(textureWidth, textureHeight).addBox(-4f, 0f, -4f, 8, 16, 8);
		thighR.setRotationPoint(-6f, -5f, 0f);
		setRotation(thighR, getRotationFloat(-15f), getRotationFloat(10f), 0);
		cnemisR = new ModelRenderer(this, 72, 88).setTextureSize(textureWidth, textureHeight).addBox(-4f, 11.8f, -11.4f, 8, 16, 8);
		cnemisR.setRotationPoint(-6f, -5f, 0f);
		setRotation(cnemisR, getRotationFloat(15f), getRotationFloat(10f), 0f);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scale, Entity entity){
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch, scale, entity);
	}
	
	public void render(Entity entityn, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scale){
		super.render(entityn, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch, scale);
		head.render(scale);
		chest.render(scale);
		gut.render(scale);
		brachiumL.render(scale);
		forearmL.render(scale);
		brachiumR.render(scale);
		forearmR.render(scale);
		thighL.render(scale);
		cnemisL.render(scale);
		thighR.render(scale);
		cnemisR.render(scale);
	}
	
}
