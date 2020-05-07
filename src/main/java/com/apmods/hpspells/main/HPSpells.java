package com.apmods.hpspells.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.apmods.hpspells.block.BlockManager;
import com.apmods.hpspells.command.CommandLearnAll;
import com.apmods.hpspells.entity.EntityManager;
import com.apmods.hpspells.handler.HPEventHandler;
import com.apmods.hpspells.handler.LumosHandler;
import com.apmods.hpspells.handler.SkillsHandler;
import com.apmods.hpspells.inventory.HPSpellsGuiHandler;
import com.apmods.hpspells.item.ItemManager;
import com.apmods.hpspells.lib.SpellLib;
import com.apmods.hpspells.network.HPNetwork;
import com.apmods.hpspells.potion.PotionFlipped;
import com.apmods.hpspells.potion.PotionHandler;
import com.apmods.hpspells.potion.PotionPetrified;
import com.apmods.hpspells.proxy.CommonProxy;
import com.apmods.hpspells.spell.ISpell;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = HPSpells.MODID, version = HPSpells.VERSION, name = "§5HP Spells")
public class HPSpells {
	@Instance
	public static HPSpells instance;
	public static final String MODID = "hpspells";
	public static final String VERSION = "1.1.6";

	public static edu.cmu.sphinx.api.Configuration speechConfig;

	public static boolean isDarkMagicAllowed;

	private int petrifiedID, flippedID;
	public static Potion petrified, flipped;

	public static ResourceLocation potionIcons = new ResourceLocation(MODID + ":textures/potion/potionIcons.png");

	@SidedProxy(clientSide = "com.apmods.hpspells.proxy.ClientProxy", serverSide = "com.apmods.hpspells.proxy.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		speechConfig = new edu.cmu.sphinx.api.Configuration();
		SpellLib.init();
		boolean devEnv = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");

		try {
			copySpeechFile("spells.dic", event.getModConfigurationDirectory());
			copySpeechFile("spells.lm", event.getModConfigurationDirectory());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		speechConfig.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		speechConfig.setDictionaryPath(new File(event.getModConfigurationDirectory(), "spells.dic").getPath());
		speechConfig.setLanguageModelPath(new File(event.getModConfigurationDirectory(), "spells.lm").getPath());

		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		isDarkMagicAllowed = config.get("Magic Permissions", "Are The Dark Arts Allowed", true, "Whether Players can use Avada Kedavra, Crucio, Confringo, and Imperio").getBoolean();
		petrifiedID = config.getInt("Petrified Potion ID", "Potions", 32, 0, 255, "");
		flippedID = config.getInt("Flipped Potion ID", "Potions", 33, 0, 255, "");
		config.save();
		preInitPotions();
	}
	
	private void copySpeechFile(String fileName, File dir) throws IOException{
		InputStream in = getClass().getClassLoader().getResourceAsStream("assets/hpspells/speech/" + fileName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		File parentFile = dir;
		File writingTo = new File(parentFile, fileName);
		PrintWriter writer;
		try {
			writer = new PrintWriter(writingTo);
			String line;
			while ((line = reader.readLine()) != null) {
				writer.println(line);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		reader.close();
		in.close();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
		BlockManager.init();
		ItemManager.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new HPSpellsGuiHandler());
		FMLCommonHandler.instance().bus().register(new LumosHandler());
		MinecraftForge.EVENT_BUS.register(new SkillsHandler());
		FMLCommonHandler.instance().bus().register(new SkillsHandler());
		MinecraftForge.EVENT_BUS.register(new HPEventHandler());
		HPNetwork.init();
		EntityManager.init();
		System.out.println("[HP Spells] Currently loaded " + SpellLib.spells.size() + " spells");

		// try {
		// writeSpellsFile();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		FMLCommonHandler.instance().bus().register(new PotionHandler());
		MinecraftForge.EVENT_BUS.register(new PotionHandler());
		petrified = new PotionPetrified(petrifiedID, true, 0xeeeeee, potionIcons).setIconIndex(0, 0).setPotionName("potion.petrified.name");
		flipped = new PotionFlipped(flippedID, true, 0x0000ee, potionIcons).setIconIndex(1, 0).setPotionName("potion.flipped.name");
	}

	private void writeSpellsFile() throws IOException {
		File file = new File("/Users/aidensweezey/Documents/Modding/HPSpells/src/main/resources/assets/hpspells/speech/spells.txt");
		System.out.println(file.delete());
		file.createNewFile();
		PrintWriter writer = new PrintWriter(file);
		String[] a = new String[SpellLib.spells.size()];
		SpellLib.spells.keySet().toArray(a);
		List<String> list = Arrays.asList(a);
		Collections.sort(list);
		for (String spell : list) {
			writer.println(spell.toUpperCase());
		}
		writer.close();
	}

	private void preInitPotions() {
		Potion[] potionTypes = null;

		for (Field f : Potion.class.getDeclaredFields()) {
			f.setAccessible(true);
			try {
				if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) {
					Field modfield = Field.class.getDeclaredField("modifiers");
					modfield.setAccessible(true);
					modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

					potionTypes = (Potion[]) f.get(null);
					final Potion[] newPotionTypes = new Potion[256];
					System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
					f.set(null, newPotionTypes);
				}
			} catch (Exception e) {
				System.err.println("Severe error, please report this to the mod author:");
				System.err.println(e);
			}
		}

	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandLearnAll());
	}
}
