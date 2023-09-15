package dev.mazyar8.chatwitharound.config;

import java.lang.reflect.Field;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import dev.mazyar8.chatwitharound.ChatWithAround;

public class Config {

	public static boolean ENABLED = true;
	
	public static String SELF_CHAT_FORMAT = "{prefix} " + ChatColor.GREEN + "You" + ChatColor.WHITE + " » " + "{message}";
	
	public static int DISTANCE_LOW_DISTANCES_X = 16;
	public static int DISTANCE_LOW_DISTANCES_Y = 16;
	public static int DISTANCE_LOW_DISTANCES_Z = 16;
	public static String DISTANCE_LOW_CHAT_FORMAT = "{prefix} {player} » " + ChatColor.WHITE + "{message}";
	
	public static int DISTANCE_HIGH_DISTANCES_X = 64;
	public static int DISTANCE_HIGH_DISTANCES_Y = 64;
	public static int DISTANCE_HIGH_DISTANCES_Z = 64;
	public static String DISTANCE_HIGH_CHAT_FORMAT = "{prefix} {player} » " + ChatColor.GRAY + "{message}";
	
	public static int DISTANCE_SHOUT_LOW_DISTANCES_X = 32;
	public static int DISTANCE_SHOUT_LOW_DISTANCES_Y = 32;
	public static int DISTANCE_SHOUT_LOW_DISTANCES_Z = 32;
	public static String DISTANCE_SHOUT_LOW_CHAT_FORMAT = "[Shout] {prefix} {player} » " + ChatColor.WHITE + "{message}";
	
	public static int DISTANCE_SHOUT_HIGH_DISTANCES_X = 128;
	public static int DISTANCE_SHOUT_HIGH_DISTANCES_Y = 128;
	public static int DISTANCE_SHOUT_HIGH_DISTANCES_Z = 128;
	public static String DISTANCE_SHOUT_HIGH_CHAT_FORMAT = "[Shout] {prefix} {player} » " + ChatColor.GRAY + "{message}";
	
	public static String GLOBAL_CHAT_FORMAT = ChatColor.GREEN + "[Global] " + ChatColor.RESET + "{prefix} {player} » " + ChatColor.GRAY + "{message}";
	
	public static String ERROR_NO_PERMISSION = ChatColor.RED + "you don't have access to that command.";
	public static String ERROR_JUST_PLAYER_ALLOW_TO_USE = ChatColor.RED + "just players allow to use this command.";
	
	public Config(FileConfiguration fc) {
		boolean shouldSave = false;
		for (Field f : getClass().getFields()) {
			String path = f.getName().toLowerCase().replace("_", ".");
			try {
				if (fc.isSet(path)) {
					f.set(this, fc.get(path));
				} else {
					fc.set(path, f.get(this));
					shouldSave = true;
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		if (shouldSave)
			ChatWithAround.getChatWithAround().saveConfig();
	}
	
	public void load() {
		FileConfiguration fc = ChatWithAround.getChatWithAround().getConfig();
		for (Field f : getClass().getFields()) {
			String path = f.getName().toLowerCase().replace("_", ".");
			try {
				if (fc.isSet(path))
					f.set(this, fc.get(path));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void save() {
		FileConfiguration fc = ChatWithAround.getChatWithAround().getConfig();
		for (Field f : getClass().getFields()) {
			String path = f.getName().toLowerCase().replace("_", ".");
			try {
				if (!fc.isSet(path))
					f.set(this, fc.get(path));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		ChatWithAround.getChatWithAround().saveConfig();
	}
	
	public void reload() {
		load();
	}
	
}
