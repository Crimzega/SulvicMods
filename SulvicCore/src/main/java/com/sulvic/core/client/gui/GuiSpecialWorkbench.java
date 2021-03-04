package com.sulvic.core.client.gui;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.sulvic.core.ReferenceSC;
import com.sulvic.core.common.container.ContainerSpecialWorkbench;
import com.sulvic.core.common.tileentity.TileSpecialWorkbench;

public class GuiSpecialWorkbench extends GuiContainer{
	
	private static final ResourceLocation GUI_SPECIAL_WORKBENCH = new ResourceLocation(ReferenceSC.MODID, "textures/gui/special_workbench.png");
	private TileSpecialWorkbench specialWorkbench;
	private int xLayerSize, yLayerSize, selectedSubLayer = -1;
	
	public GuiSpecialWorkbench(InventoryPlayer playerInv, TileSpecialWorkbench workbench){
		super(new ContainerSpecialWorkbench(playerInv, workbench));
		specialWorkbench = workbench;
		xSize = 176;
		ySize = 180;
		xLayerSize = 90;
		yLayerSize = 72;
	}
	
	public void initGui(){
		super.initGui();
		for(int y = 0; y < 3; y++) for(int x = 0; x < 3; x++) addButton(new GuiCustomButton(x+ y * 3, x, y));
	}
	
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
		GL11.glColor4f(1f, 1f, 1f, 1f);
		Minecraft.getMinecraft().getTextureManager().bindTexture(GUI_SPECIAL_WORKBENCH);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		super.drawScreen(mouseX, mouseY, partialTicks);
		if(specialWorkbench.isDepthOpen(selectedSubLayer)) drawTexturedModalRect(guiLeft, guiTop - yLayerSize, 0, ySize, xLayerSize, yLayerSize);
	}
	
	protected void mouseClicked(int mouseX, int mouseY, int mouseBtn) throws IOException{
		super.mouseClicked(mouseX, mouseY, mouseBtn);
		if(selectedButton instanceof GuiCustomButton){
			GuiCustomButton workbenchBtn = (GuiCustomButton)selectedButton;
			workbenchBtn.toggled = !workbenchBtn.toggled;
			selectedSubLayer = workbenchBtn.toggled? workbenchBtn.id: -1;
		}
	}
	
	private static class GuiCustomButton extends GuiButton{
		
		private static final int BUTTON_SIZE = 20;
		protected boolean toggled;
		
		public GuiCustomButton(int id, int x, int y){ super(id, x, y, BUTTON_SIZE, BUTTON_SIZE, ""); }
		
		private void setupHovered(int mouseX, int mouseY){ hovered = mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height; }
		
		public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks){
			mc.getTextureManager().bindTexture(GUI_SPECIAL_WORKBENCH);
			GlStateManager.color(1f, 1f, 1f, 1f);
			setupHovered(mouseX, mouseY);
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            drawTexturedModalRect(x, y, 0, 0, mouseX, mouseY);
		}
		
	}
	
}
