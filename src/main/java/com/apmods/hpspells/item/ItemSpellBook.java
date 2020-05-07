package com.apmods.hpspells.item;

import com.apmods.hpspells.gui.GuiSpellbook;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSpellBook extends ItemBase{
	
	public static String[] g1Spells = {"WINGARDIUM LEVIOSA", "FLIPENDO", "PETRIFICUS TOTALUS", "ALOHOMORA"};
	public static String[] g2Spells = {"EXPULSO", "PROTEGO", "ORCHIDEOUS", "INCENDIO"};
	public static String[] g3Spells = {"EPISKEY", "EXPELLIARMUS", "PATRONUS", "AGUAMENTI"};
	public static String[] g4Spells = {"CONFRINGO", "DEFODIO", "STUPEFY", "ACCIO"};
	public static String[] g5Spells = {"DIFFINDO", "GLISSEO", "OBSCURO", "HERBIVICUS"};
	public static String[] g6Spells = {"REFULGENS", "PORTUS", "LUMOS", "FUMOS"};
	public static String[] g7Spells = {"APPARATE", "ALARTE ASCENDARE", "DURO", "AVIS"};
	public static String[] transfig1 = {"MELOFORS", "FERA VERTO", "MULTICOLORFORS", "AVIFORS"};
	public static String[] darkArts = {"AVADA KEDAVRA", "CRUCIO", "IMPERIO"};
	public static String[][] levelbook = {g1Spells, g2Spells, g3Spells, g4Spells, g5Spells, g6Spells, g7Spells, transfig1, darkArts};
	private int level;

	public ItemSpellBook(String name, int level) {
		super(name);
		this.maxStackSize = 1;
		this.level = level;
	}
	@SideOnly(Side.CLIENT)
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player)
    {
//		if(!world.isRemote){
//			MagiSkills ext = MagiSkills.get(player);
//			Random rand = new Random();
//			int randint = rand.nextInt(SpellLib.names.length - 1) + 1;
//			if(ext.getCurrentFizzleLevel(randint) < 2 && ext.getCurrentSkill(randint) < 75){
//				ext.addFizzleLevel(randint);
//				player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "[MagiCraft] You leveled up your " + SpellLib.names[randint] + " experience! Current Level: " + ext.getCurrentFizzleLevel(randint)));
//			}
//			else if(ext.getCurrentFizzleLevel(randint) < 3){
//				ext.addFizzleLevel(randint);
//				player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "[MagiCraft] You leveled up your " + SpellLib.names[randint] + " experience! Current Level: " + ext.getCurrentFizzleLevel(randint)));
//			}
//		}
//		ItemStack is2 = is.copy();
//		is2.stackSize-= 1;
		Minecraft mc = Minecraft.getMinecraft();
		if(world.isRemote){
			mc.displayGuiScreen(new GuiSpellbook(levelbook[level]));
		}
		return is;
    }

}
