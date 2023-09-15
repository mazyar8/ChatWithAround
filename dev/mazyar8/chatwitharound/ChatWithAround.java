package dev.mazyar8.chatwitharound;

import org.bukkit.command.CommandExecutor;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import dev.mazyar8.chatwitharound.command.CWACommand;
import dev.mazyar8.chatwitharound.command.GlobalCommand;
import dev.mazyar8.chatwitharound.command.ShoutCommand;
import dev.mazyar8.chatwitharound.config.Config;
import dev.mazyar8.chatwitharound.event.ChatEvent;
import dev.mazyar8.chatwitharound.event.Event;
import dev.mazyar8.chatwitharound.permission.PermissionHelper;

public class ChatWithAround extends JavaPlugin {
	
	private static ChatWithAround cwa;
	public Config config;

	@Override
	public void onEnable() {
		cwa = this;
		config = new Config(getConfig());
		addCommand("cwa", new CWACommand());
		addCommand("global", new GlobalCommand());
		addCommand("shout", new ShoutCommand());
		addEvent(new ChatEvent());
		addPermission(PermissionHelper.ADMINISTRATOR);
		addPermission(PermissionHelper.COMMAND_GLOBAL);
		addPermission(PermissionHelper.COMMAND_SHOUT);
		addPermission(PermissionHelper.CHAT_COLOR);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	/** get new command by executor */
	public void addCommand(String cmd, CommandExecutor executor) {
		getServer().getPluginCommand(cmd).setExecutor(executor);
	}
	
	/** register new event or listener */
	public void addEvent(Event e) {
		getServer().getPluginManager().registerEvents(e, this);
	}
	
	/** add new permission to server */
	public void addPermission(Permission permission) {
		getServer().getPluginManager().addPermission(permission);
	}

	public static ChatWithAround getChatWithAround() {
		return cwa;
	}
	
}
