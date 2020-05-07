package com.apmods.hpspells.gui;

import org.lwjgl.input.Keyboard;

import com.apmods.hpspells.extendedplayer.SpellSkills;
import com.apmods.hpspells.main.HPSpells;
import com.apmods.hpspells.network.CopyCoordsPacket;
import com.apmods.hpspells.network.HPNetwork;
import com.apmods.hpspells.network.PortkeyPacket;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiPortkey extends GuiScreen {
	/** The X size of the inventory window in pixels. */
    protected int xSize = 176;
    /** The Y size of the inventory window in pixels. */
    protected int ySize = 166;
	public GuiTextField textX;
	public GuiTextField textY;
	public GuiTextField textZ;
	public GuiButton done;
	public GuiButton copyCoords, pasteCoords, myCoords;
	public void initGui(){
		super.initGui();
		int i = (this.width - this.xSize) / 2;
	    int j = (this.height - this.ySize) / 2;
		textX = new GuiTextField(fontRendererObj, i + 13, j + 23, 150, 12);
		this.textX.setTextColor(-1);
        this.textX.setDisabledTextColour(-1);
        this.textX.setEnableBackgroundDrawing(false);
        this.textX.setMaxStringLength(90);
        textY = new GuiTextField(fontRendererObj, i + 13, j + 52, 150, 12);
		this.textY.setTextColor(-1);
        this.textY.setDisabledTextColour(-1);
        this.textY.setEnableBackgroundDrawing(false);
        this.textY.setMaxStringLength(90);
        textZ = new GuiTextField(fontRendererObj, i + 13, j + 81, 150, 12);
		this.textZ.setTextColor(-1);
        this.textZ.setDisabledTextColour(-1);
        this.textZ.setEnableBackgroundDrawing(false);
        this.textZ.setMaxStringLength(90);
        done = new GuiButton(0, this.width/2 - 78, this.height/2 + 11, 157, 20, "Done");
        this.buttonList.add(done);
        
        copyCoords = new GuiButton(1, this.width/2 + 50, this.height/2 + 32, 30, 20, "Copy");
        this.buttonList.add(copyCoords);
        pasteCoords = new GuiButton(2, this.width/2 + 50, this.height/2 + 55, 30, 20, "Paste");
        this.buttonList.add(pasteCoords);
        myCoords = new GuiButton(3, this.width/2 - 15, this.height/2 + 45, 60, 20, "My Coords");
        this.buttonList.add(myCoords);
	}
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
		int m = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
		this.mc.getTextureManager().bindTexture(new ResourceLocation(HPSpells.MODID + ":textures/gui/portkey2.png"));
		int k = (this.width - 175) / 2;
        int b0 = (this.height - 165) /2;
		this.drawTexturedModalRect(k, b0, 0, 0, 176, 166);
		this.drawTexturedModalRect(m + 10, l + 19, 0, 166, 156, 16);
		this.drawTexturedModalRect(m + 10, l + 48, 0, 166, 156, 16);
		this.drawTexturedModalRect(m + 10, l + 77, 0, 166, 156, 16);
		fontRendererObj.drawString("X Coordinate:", m + 10, l + 9, 4210752);
		fontRendererObj.drawString("Y Coordinate:", m + 10, l + 38, 4210752);
		fontRendererObj.drawString("Z Coordinate:", m + 10, l + 67, 4210752);
        this.textX.drawTextBox();
        this.textY.drawTextBox();
        this.textZ.drawTextBox();
        String s = "Valid";
        boolean flag = checkValidity();
        if(!flag){
        	s = "Invalid";
        }
        fontRendererObj.drawSplitString("Coordinates " + s, m + 10, l + 130, 70,flag ? 0x00d000 : 0xff0000);

        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
    }
	protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_)
    {
        super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
        this.textX.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
        this.textY.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
        this.textZ.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
    }
	
	public boolean checkValidity(){
		if(!textX.getText().isEmpty() && !textY.getText().isEmpty() && !textZ.getText().isEmpty()){
			if(!textX.getText().equals("-") && !textY.getText().equals("-") && !textZ.getText().equals("-")){
				int i = Integer.parseInt(textX.getText());
				int j = Integer.parseInt(textY.getText());
				int k = Integer.parseInt(textZ.getText()); 
				if(mc.theWorld.isAirBlock(i, j, k) && mc.theWorld.isAirBlock(i, j + 1, k)){
					return true;
				}
			}
		}
		return false;
	}
	
	protected void keyTyped(char p_73869_1_, int p_73869_2_)
    {
		if(Character.isDigit(p_73869_1_) || p_73869_2_ == Keyboard.KEY_MINUS || p_73869_2_ == Keyboard.KEY_BACK || p_73869_2_ == Keyboard.KEY_ESCAPE){
	        if (this.textX.textboxKeyTyped(p_73869_1_, p_73869_2_))
	        {
	           
	        }
	        else if (this.textY.textboxKeyTyped(p_73869_1_, p_73869_2_))
	        {
	           
	        }
	        else if (this.textZ.textboxKeyTyped(p_73869_1_, p_73869_2_))
	        {
	           
	        }
	        else
	        {
	            super.keyTyped(p_73869_1_, p_73869_2_);
	        }
		}
    }
	protected void actionPerformed(GuiButton b)
    {
		EntityPlayer player = this.mc.thePlayer;
		SpellSkills ext = SpellSkills.get(player);
        if (b.enabled)
        {
        	if(b.id == 0){
        		if(!textX.getText().isEmpty() && !textY.getText().isEmpty() && !textZ.getText().isEmpty()){
	        		int i = Integer.parseInt(textX.getText());
	        		int j = Integer.parseInt(textY.getText());
	        		int k = Integer.parseInt(textZ.getText());
	        		HPNetwork.net.sendToServer(new PortkeyPacket.PortkeyMessage(i, j, k));
        		}
        		this.mc.displayGuiScreen((GuiScreen)null);
        		
        	}else if(b.id == 1){
        		if(!textX.getText().isEmpty() && !textY.getText().isEmpty() && !textZ.getText().isEmpty()){
	        		int i = Integer.parseInt(textX.getText());
	        		int j = Integer.parseInt(textY.getText());
	        		int k = Integer.parseInt(textZ.getText());
	        		HPNetwork.net.sendToServer(new CopyCoordsPacket.CopyCoordsMessage(true, i, j, k));
        		}
        	}else if(b.id == 2){
        		textX.setText("" + ext.getPortkeyX());
        		textY.setText("" + ext.getPortkeyY());
        		textZ.setText("" + ext.getPortkeyZ());
        	}else if(b.id == 3){
        		textX.setText("" + (int)player.posX);
        		textY.setText("" + (int)player.posY);
        		textZ.setText("" + (int)player.posZ);
        	}
        }
    }
}
