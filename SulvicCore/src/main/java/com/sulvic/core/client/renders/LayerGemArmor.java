package com.sulvic.core.client.renders;

import com.sulvic.core.common.item.ItemArmorGem;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

@SuppressWarnings("incomplete-switch")
public class LayerGemArmor extends LayerArmorBase<ModelBiped>{
	
	private final RenderLivingBase<?> rendererLivingBase;
	private boolean skipRenderGlint;
	private float alpha = 1f, colorR = 1f, colorG = 1f, colorB = 1f;
	
	public LayerGemArmor(RenderLivingBase<?> renderer){
		super(renderer);
		rendererLivingBase = renderer;
	}
	
	private boolean isLegSlot(EntityEquipmentSlot slot){ return slot == EntityEquipmentSlot.LEGS; }
	
	private void renderArmorLayer(EntityLivingBase livingBase, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale,
		EntityEquipmentSlot slot){
		ItemStack stack = livingBase.getItemStackFromSlot(slot);
		if(stack.getItem() instanceof ItemArmorGem){
			ItemArmorGem armor = (ItemArmorGem)stack.getItem();
			if(armor.getEquipmentSlot() == slot){
				ModelBiped t = this.getModelFromSlot(slot);
				t = getArmorModelHook(livingBase, stack, slot, t);
				t.setModelAttributes(rendererLivingBase.getMainModel());
				t.setLivingAnimations(livingBase, limbSwing, limbSwingAmount, partialTicks);
				this.setModelSlotVisible(t, slot);
				boolean flag = isLegSlot(slot);
				rendererLivingBase.bindTexture(this.getArmorResource(livingBase, stack, slot, null));
				{
					if(armor.hasOverlay(stack)){ // Allow this for anything, not only cloth
						int rgb = armor.getColor(stack);
						float r = (float)(rgb >> 16 & 255) / 255.0F;
						float g = (float)(rgb >> 8 & 255) / 255.0F;
						float b = (float)(rgb & 255) / 255.0F;
						GlStateManager.color(colorR * r, colorG * g, colorB * b, alpha);
						t.render(livingBase, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
						rendererLivingBase.bindTexture(getArmorResource(livingBase, stack, slot, "overlay"));
					}
					{ // Non-colored
						int rgb = armor.getColor(stack);
						float r = (float)(rgb >> 16 & 255) / 255.0F;
						float g = (float)(rgb >> 8 & 255) / 255.0F;
						float b = (float)(rgb & 255) / 255.0F;
						GlStateManager.color(colorR * r, colorG * g, colorB * b, alpha);
						t.render(livingBase, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
					} // Default
					if(!this.skipRenderGlint && stack.hasEffect()){
						renderEnchantedGlint(this.rendererLivingBase, livingBase, t, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
					}
				}
			}
		}
	}
	
	protected void setModelVisible(ModelBiped model){ model.setVisible(false); }
	
	protected void initArmor(){
		modelLeggings = new ModelBiped(0.5f);
		modelArmor = new ModelBiped(1f);
	}
	
	protected void setModelSlotVisible(ModelBiped biped, EntityEquipmentSlot slot){
		setModelVisible(biped);
		switch(slot){
			case HEAD:
				biped.bipedHead.showModel = true;
				biped.bipedHeadwear.showModel = true;
			break;
			case CHEST:
				biped.bipedBody.showModel = true;
				biped.bipedRightArm.showModel = true;
				biped.bipedLeftArm.showModel = true;
			break;
			case LEGS:
				biped.bipedBody.showModel = true;
				biped.bipedRightLeg.showModel = true;
				biped.bipedLeftLeg.showModel = true;
			break;
			case FEET:
				biped.bipedRightLeg.showModel = true;
				biped.bipedLeftLeg.showModel = true;
		}
	}
	
	@Override
	public void doRenderLayer(EntityLivingBase livingBase, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale){
		renderArmorLayer(livingBase, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale, EntityEquipmentSlot.CHEST);
		renderArmorLayer(livingBase, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale, EntityEquipmentSlot.LEGS);
		renderArmorLayer(livingBase, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale, EntityEquipmentSlot.FEET);
		renderArmorLayer(livingBase, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale, EntityEquipmentSlot.HEAD);
	}
	
}
