package com.apmods.hpspells.item;

import com.apmods.hpspells.entity.EnumBroomstick;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.util.EnumHelper;

public class ItemManager {

	public static Item wand, elderWand, fizzleTester, darkArts, darkHeart, darkWardDisabler, darkWard, 
	broomstick, firebolt, invisCloak, cloak;
	public static ToolMaterial gryffindor = EnumHelper.addToolMaterial("gryffindor", 3, 5000, 8.5f, 4f, 25);

	public static void init() {
		wand = new ItemWand("wand", 20).register();
		elderWand = new ItemElderWand("elderwand").register();
		ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(elderWand), 1, 1, 1));
		fizzleTester = new ItemWand("ft", 2).register();
		darkArts = new ItemBase("darkArts").register().setItemTooltip("By " + EnumChatFormatting.OBFUSCATED + "Lord Voldemort");
		darkHeart = new ItemBase("darkHeart").register().setItemTooltip("A Heart infected by the Dark Arts");
		darkWard = new ItemDarkWard("darkWard").register();
		darkWardDisabler = new ItemDarkWardDisabler("darkWardDisabler").register();
		broomstick = new ItemBroomstick("broomstick", EnumBroomstick.BROOMSTICK).register();
		firebolt = new ItemBroomstick("firebolt", EnumBroomstick.FIREBOLT).register();
		invisCloak = new ItemInvisibilityCloak("invisCloak", 1, 1).register();
		cloak = new ItemCloak("cloak", 1, 1).register();
		addRecipes();
	}

	public static void addRecipes() {
		GameRegistry.addRecipe(new ItemStack(darkArts), new Object[]{"hhh", "hbh", "hhh", 'h', darkHeart, 'b', Items.book});
		GameRegistry.addRecipe(new ItemStack(broomstick), new Object[]{" s ", " s ", " h ", 's', Items.stick, 'h', Blocks.hay_block});
//		GameRegistry.addRecipe(new ItemStack(wand), new Object[]{" s ", "ded", " s ", 's', Items.stick, 'd', Items.diamond, 'e', Items.emerald});
		GameRegistry.addRecipe(new ItemStack(cloak), new Object[]{"lgl", "lll", "l l", 'l', Items.leather, 'g', Items.gold_ingot});
	}
}
