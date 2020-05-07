package com.apmods.hpspells.gui;

import java.util.Arrays;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.apmods.hpspells.entity.EntityWandmaker;
import com.apmods.hpspells.inventory.ContainerWandmaker;
import com.apmods.hpspells.inventory.InventoryWandmaker;
import com.apmods.hpspells.main.HPSpells;
import com.apmods.hpspells.network.HPNetwork;
import com.apmods.hpspells.network.WandmakerPacket.WandmakerMessage;
import com.apmods.hpspells.spell.EnumSpellType;
import com.apmods.hpspells.wand.EnumWandCore;
import com.apmods.hpspells.wand.EnumWandMaterial;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiWandmaker extends GuiContainer {

	public ResourceLocation iconLocation = new ResourceLocation(HPSpells.MODID + ":textures/gui/wandmaker.png");

	private String[] names = { "" };

	public int wandWoodScrollOffset = 0;
	public int wandWoodMaxScroll;
	public int wandCoreScrollOffset = 0;
	public int wandCoreMaxScroll;

	// 0 is not scrolling, 1 is scrolling on the wand woods, 2 is scrolling on
	// the wand cores
	private int scrolling;

	private EnumWandMaterial[] woods;
	private EnumWandCore[] cores;
	
	private boolean wasClicking;
	private boolean shift;

	private EntityWandmaker wandmaker;

	public GuiWandmaker(InventoryPlayer inv, EntityWandmaker entity) {
		super(new ContainerWandmaker(inv, entity));
		wandmaker = entity;
		wandWoodMaxScroll = (entity.getNumWoods() * 18) - 66;
		wandCoreMaxScroll = (entity.getNumCores() * 18) - 66;
				
		cores = new EnumWandCore[entity.getNumCores()];
		for(int i = 0; i < entity.getNumCores(); i++){
			cores[i] = entity.getCore(i);
		}
		
		woods = new EnumWandMaterial[entity.getNumWoods()];
		for(int i = 0; i < entity.getNumWoods(); i++){
			woods[i] = entity.getWood(i);
		}
	}

	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
		boolean flag = Mouse.isButtonDown(0);
		int k = this.guiLeft;
		int l = this.guiTop;
		int i1 = k + 64;
		int j1 = l + 7;
		int k1 = i1 + 7;
		int l1 = j1 + 66;
		int i2 = k + 133;
		int j2 = l + 7;
		int k2 = i2 + 7;
		int l2 = j2 + 66;
		int i3 = k + 9;
		int j3 = l + 7;
		int k3 = i3 + 62;
		int l3 = j3 + 66;
		int i4 = k + 78;
		int j4 = l + 7;
		int k4 = i4 + 62;
		int l4 = j4 + 66;

//		if (!woods.equals(wandmaker.woods)) {
//			woods = wandmaker.woods;
//			cores = wandmaker.cores;
//			wandWoodMaxScroll = (woods.length * 18) - 66;
//			wandCoreMaxScroll = (cores.length * 18) - 66;
//			wandWoodScrollOffset = 0;
//			wandCoreScrollOffset = 0;
//		}

		if (!this.wasClicking && flag) {
			if (p_73863_1_ >= i1 && p_73863_2_ >= j1 && p_73863_1_ < k1 && p_73863_2_ < l1) {
				this.scrolling = 1;
			} else if (p_73863_1_ >= i2 && p_73863_2_ >= j2 && p_73863_1_ < k2 && p_73863_2_ < l2) {
				this.scrolling = 2;
			}
			if (this.scrolling == 0) {
				if (p_73863_1_ >= i3 && p_73863_1_ <= k3 && p_73863_2_ >= j3 && p_73863_2_ <= l3) {
					float pos = p_73863_2_ - 7 - this.guiTop;
					double clickedOnPos = (double) (pos + wandWoodScrollOffset);
					EnumWandMaterial clickedOn = woods[(int) Math.floor(clickedOnPos / 18)];
					((ContainerWandmaker) this.inventorySlots).wandmakerInv.selectedWood = clickedOn;
				} else if (p_73863_1_ >= i4 && p_73863_1_ <= k4 && p_73863_2_ >= j4 && p_73863_2_ <= l4) {
					float pos = p_73863_2_ - 7 - this.guiTop;
					double clickedOnPos = (double) (pos + wandCoreScrollOffset);
					EnumWandCore clickedOn = cores[(int) Math.floor(clickedOnPos / 18)];
					((ContainerWandmaker) this.inventorySlots).wandmakerInv.selectedCore = clickedOn;
				}
				int core = Arrays.asList(EnumWandCore.values()).indexOf(((ContainerWandmaker) this.inventorySlots).wandmakerInv.selectedCore);
				int wood = Arrays.asList(EnumWandMaterial.values()).indexOf(((ContainerWandmaker) this.inventorySlots).wandmakerInv.selectedWood);
				HPNetwork.net.sendToServer(new WandmakerMessage((byte) wood, (byte) core));
			}
		}

		if (!flag) {
			this.scrolling = 0;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			shift = true;
		}else{
			shift = false;
		}

		this.wasClicking = flag;

		if (this.scrolling > 0) {
			float currentScroll = ((float) (p_73863_2_ - j1) - 7.5F) / ((float) (l1 - j1) - 15.0F);

			if (currentScroll < 0.0F) {
				currentScroll = 0.0F;
			}

			if (currentScroll > 1.0F) {
				currentScroll = 1.0F;
			}

			if (scrolling == 1) {
				wandWoodScrollOffset = (int) (currentScroll * wandWoodMaxScroll);
			} else {
				wandCoreScrollOffset = (int) (currentScroll * wandCoreMaxScroll);
			}

		}

		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(iconLocation);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, 175, 165);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
		int firstWandWoodToDraw = (int) Math.floor(wandWoodScrollOffset / 18);
		int upperToDraw = 18 - (wandWoodScrollOffset % 18);
		int fullBoxesAbleToBeDrawn = (int) Math.floor((66 - upperToDraw) / 16);
		if (fullBoxesAbleToBeDrawn >= woods.length)
			fullBoxesAbleToBeDrawn = woods.length - 1;
		int lowerToDraw = 66 - (upperToDraw + (fullBoxesAbleToBeDrawn * 18));
		int drawing = firstWandWoodToDraw;
		if (upperToDraw > 0) {
			this.drawWandWoodBox(woods[drawing], 0, 0, 18 - upperToDraw, upperToDraw);
			drawing++;
		}
		for (int i = 0; i < fullBoxesAbleToBeDrawn; i++) {
			this.drawWandWoodBox(woods[drawing], 0, upperToDraw + (18 * i), 0, 18);
			drawing++;
		}
		if (lowerToDraw > 0 && drawing < woods.length) {
			this.drawWandWoodBox(woods[drawing], 0, 66 - lowerToDraw, 0, lowerToDraw + 1);
		}
		int firstWandCoreToDraw = (int) Math.floor(wandCoreScrollOffset / 18);
		upperToDraw = 18 - (wandCoreScrollOffset % 18);
		fullBoxesAbleToBeDrawn = (int) Math.floor((66 - upperToDraw) / 16);
		if (fullBoxesAbleToBeDrawn >= cores.length)
			fullBoxesAbleToBeDrawn = cores.length - 1;
		lowerToDraw = 66 - (upperToDraw + (fullBoxesAbleToBeDrawn * 18));
		drawing = firstWandCoreToDraw;
		if (upperToDraw > 0) {
			this.drawWandCoreBox(cores[drawing], 1, 0, 18 - upperToDraw, upperToDraw);
			drawing++;
		}
		for (int i = 0; i < fullBoxesAbleToBeDrawn; i++) {
			this.drawWandCoreBox(cores[drawing], 1, upperToDraw + (18 * i), 0, 18);
			drawing++;
		}
		if (lowerToDraw > 0 && drawing < cores.length) {
			this.drawWandCoreBox(cores[drawing], 1, 66 - lowerToDraw, 0, lowerToDraw);
		}
		this.mc.getTextureManager().bindTexture(iconLocation);
		this.drawTexturedModalRect(0, 0, 0, 0, 175, 7);
		this.drawTexturedModalRect(0, 73, 0, 73, 175, 10);
		int k = this.guiLeft;
		int l = this.guiTop;
		int i1 = k + 9;
		int j1 = l + 7;
		int k1 = i1 + 62;
		int l1 = j1 + 66;
		int i2 = k + 78;
		int j2 = l + 7;
		int k2 = i2 + 62;
		int l2 = j2 + 66;
		if ((p_146979_1_ >= i1 && p_146979_1_ <= k1 && p_146979_2_ >= j1 && p_146979_2_ <= l1)) {
			if (scrolling == 0) {
				if (woods.length >= 3) {
					this.drawScrollBar(0);
				}
			}
            GL11.glDisable(GL11.GL_DEPTH_TEST);
			if(shift){
				float pos = p_146979_2_ - 7 - this.guiTop;
				double clickedOnPos = (double) (pos + wandWoodScrollOffset);
				EnumWandMaterial hoverOn = woods[(int) Math.floor(clickedOnPos / 18)];
				int yPos = (int) Math.floor(clickedOnPos / 18) * 18 - 7 + 18 - wandCoreScrollOffset;
				int xPos = 21;
				drawGradientRect(xPos, yPos, xPos + 120, yPos + 63, -(0xff0000), -1073741824);
				fontRendererObj.drawString(hoverOn.name, xPos + 3, yPos + 3, 0xffff44);
				fontRendererObj.drawString("-Charms: " + (int)(hoverOn.getMultiplierForSpellType(EnumSpellType.CHARM) * 100) + "%", xPos + 3, yPos + 13, 0xcccccc);
				fontRendererObj.drawString("-Hexes: " + (int)(hoverOn.getMultiplierForSpellType(EnumSpellType.HEX) * 100) + "%", xPos + 3, yPos + 23, 0xcccccc);
				fontRendererObj.drawString("-Transfiguration: " + (int)(hoverOn.getMultiplierForSpellType(EnumSpellType.TRANSFIGURATION) * 100) + "%", xPos + 3, yPos + 33, 0xcccccc);
				fontRendererObj.drawString("-DADA: " + (int)(hoverOn.getMultiplierForSpellType(EnumSpellType.DADA) * 100) + "%", xPos + 3, yPos + 43, 0xcccccc);
				fontRendererObj.drawString("-Dark Arts: " + (int)(hoverOn.getMultiplierForSpellType(EnumSpellType.DARK) * 100) + "%", xPos + 3, yPos + 53, 0xcccccc);
			}
            GL11.glEnable(GL11.GL_DEPTH_TEST);
		} else if ((p_146979_1_ >= i2 && p_146979_1_ <= k2 && p_146979_2_ >= j2 && p_146979_2_ <= l2 && scrolling == 0)) {
			if (scrolling == 0) {
				if (cores.length >= 3) {
					this.drawScrollBar(1);
				}
			}
            GL11.glDisable(GL11.GL_DEPTH_TEST);
			if(shift){
				float pos = p_146979_2_ - 7 - this.guiTop;
				double clickedOnPos = (double) (pos + wandCoreScrollOffset);
				EnumWandCore hoverOn = cores[(int) Math.floor(clickedOnPos / 18)];
				int yPos = (int) Math.floor(clickedOnPos / 18) * 18 - 7 + 18 - wandCoreScrollOffset;
				int xPos = 90;
				drawGradientRect(xPos, yPos, xPos + 120, yPos + 63, -(0xff0000), -1073741824);
				fontRendererObj.drawString(hoverOn.name, xPos + 3, yPos + 3, 0xffff44);
				fontRendererObj.drawString("-Charms: " + (int)(hoverOn.getMultiplierForSpellType(EnumSpellType.CHARM) * 100) + "%", xPos + 3, yPos + 13, 0xcccccc);
				fontRendererObj.drawString("-Hexes: " + (int)(hoverOn.getMultiplierForSpellType(EnumSpellType.HEX) * 100) + "%", xPos + 3, yPos + 23, 0xcccccc);
				fontRendererObj.drawString("-Transfiguration: " + (int)(hoverOn.getMultiplierForSpellType(EnumSpellType.TRANSFIGURATION) * 100) + "%", xPos + 3, yPos + 33, 0xcccccc);
				fontRendererObj.drawString("-DADA: " + (int)(hoverOn.getMultiplierForSpellType(EnumSpellType.DADA) * 100) + "%", xPos + 3, yPos + 43, 0xcccccc);
				fontRendererObj.drawString("-Dark Arts: " + (int)(hoverOn.getMultiplierForSpellType(EnumSpellType.DARK) * 100) + "%", xPos + 3, yPos + 53, 0xcccccc);
			}
            GL11.glEnable(GL11.GL_DEPTH_TEST);
		}
		
		if(cores.length >= 3 && scrolling == 2){
			this.drawScrollBar(1);
		}else if(woods.length >= 3 && scrolling == 1){
			this.drawScrollBar(0);
		}
		
		ContainerWandmaker container = (ContainerWandmaker) this.inventorySlots;
		this.fontRendererObj.drawString("" + container.cost, 149 + fontRendererObj.getStringWidth("" + container.cost) / 4, 7, 0x686868);
	}

	@Override
	public void handleMouseInput() {
		super.handleMouseInput();
	}

	private void drawScrollBar(int area) {
		this.mc.getTextureManager().bindTexture(iconLocation);
		this.drawTexturedModalRect(64 + (area * 69), 7, 0, 166, 7, 66);
		int totalValues = area == 0 ? woods.length : cores.length;
		int sizeScroller = (int) (4356 / (totalValues * 18));
		float offset = (area == 0 ? wandWoodScrollOffset : wandCoreScrollOffset);
		this.drawTexturedModalRect(64 + (area * 69), (int) (7 + ((offset / (area == 0 ? wandWoodMaxScroll : wandCoreMaxScroll)) * (66 - sizeScroller))), 7, 166, 7, sizeScroller);
	}

	private void drawWandWoodBox(EnumWandMaterial icon, int area, int yOffset, int uvOffset, int height) {
		this.mc.getTextureManager().bindTexture(iconLocation);
		GL11.glColor4f(1f, 1f, 1f, 1f);
		ContainerWandmaker container = (ContainerWandmaker) this.inventorySlots;
		if (icon == ((ContainerWandmaker) this.inventorySlots).wandmakerInv.selectedWood) {
			GL11.glColor4f(0.4f, 0.4f, 0.4f, 1);
		}
		this.drawTexturedModalRect(9 + (area * 71), 7 + yOffset, 176, uvOffset, 62, height);
		int color = icon.color;
		float r = (float) ((color >> 16) & 0xFF) / 255;
		float g = (float) ((color >> 8) & 0xFF) / 255;
		float b = (float) ((color >> 0) & 0xFF) / 255;
		GL11.glPushMatrix();
		GL11.glColor4f(r, g, b, 1);
		this.drawTexturedModalRect(9 + (area * 71), (uvOffset > 2 ? 7 : 8) + yOffset, 176, 18 + uvOffset, 16, Math.max(height - 2, 0));
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();
		if (height > 5 && uvOffset < 14) {
			this.drawString(fontRendererObj, icon.name, 25 + (area * 71), 12 + yOffset - uvOffset, 0xffffff);
		}
	}

	private void drawWandWoodBox(EnumWandMaterial icon, int area, int yOffset) {
		this.drawWandWoodBox(icon, area, yOffset, 0, 18);
	}

	private void drawWandCoreBox(EnumWandCore icon, int area, int yOffset) {
		this.drawWandCoreBox(icon, area, yOffset, 0, 18);
	}

	private void drawWandCoreBox(EnumWandCore icon, int area, int yOffset, int uvOffset, int height) {
		this.mc.getTextureManager().bindTexture(iconLocation);
		ContainerWandmaker container = (ContainerWandmaker) this.inventorySlots;
		if (icon == ((ContainerWandmaker) this.inventorySlots).wandmakerInv.selectedCore) {
			GL11.glColor4f(0.4f, 0.4f, 0.4f, 1);
		}
		this.drawTexturedModalRect(7 + (area * 71), 7 + yOffset, 176, uvOffset, 62, height);
		GL11.glColor4f(1, 1, 1, 1);
		List<EnumWandCore> cores = Arrays.asList(EnumWandCore.values());
		int row = (int) Math.floor(cores.indexOf(icon) / 4);
		int column = cores.indexOf(icon) % 4;
		this.drawTexturedModalRect(8 + (area * 71), (uvOffset > 2 ? 7 : 8) + yOffset, 192 + (column * 16), 18 + (row * 16) + uvOffset, 16, Math.max(height - 1, 0));
		GL11.glPushMatrix();
		GL11.glScalef(0.75f, 0.75f, 0.75f);
		GL11.glTranslatef(33, 4, 0);
		if (height > 4 && uvOffset < 14) {
			fontRendererObj.drawSplitString(icon.name, 24 + (area * 71), (int) (10 + (yOffset * 1.33) - ((int) Math.ceil(fontRendererObj.getStringWidth(icon.name) / 58))) - uvOffset, 58, 0xffffff);
		}
		GL11.glScalef(1, 1, 1);
		GL11.glPopMatrix();
	}

}
