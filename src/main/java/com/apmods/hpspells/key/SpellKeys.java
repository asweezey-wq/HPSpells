package com.apmods.hpspells.key;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;

public class SpellKeys {
	public static final KeyBinding spellkey = new KeyBinding("key.lastspell.name", Keyboard.KEY_Z, "key.categories.magicraft");
	public static final KeyBinding castKey = new KeyBinding("key.castspell.name", Keyboard.KEY_X, "key.categories.magicraft");
	public static final KeyBinding spellTreeKey = new KeyBinding("key.spelltree.name", Keyboard.KEY_H, "key.categories.magicraft");
	public static final KeyBinding spellSpeechKey = new KeyBinding("key.speechkey.name", Keyboard.KEY_C, "key.categories.magicraft");
}
