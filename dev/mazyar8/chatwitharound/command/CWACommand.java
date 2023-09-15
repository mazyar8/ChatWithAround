package dev.mazyar8.chatwitharound.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import dev.mazyar8.chatwitharound.ChatWithAround;
import dev.mazyar8.chatwitharound.config.Config;
import dev.mazyar8.chatwitharound.permission.PermissionHelper;

public class CWACommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String message, String[] args) {
		if (sender.hasPermission(PermissionHelper.ADMINISTRATOR)) {
			if (args.length > 0) {
				if (args[0].equals("enable")) {
					Config.ENABLED = true;
					save();
					sender.sendMessage(ChatWithAround.getChatWithAround().getDescription().getName() + " has been enabled.");
					return true;
				}
				if (args[0].equals("disable")) {
					Config.ENABLED = false;
					save();
					sender.sendMessage(ChatWithAround.getChatWithAround().getDescription().getName() + " has been disabled.");
					return true;
				}
				if (args[0].equals("reload")) {
					ChatWithAround.getChatWithAround().reloadConfig();
					ChatWithAround.getChatWithAround().config.reload();
					sender.sendMessage(ChatColor.GREEN + "config has been reloaded.");
					return true;
				}
			}
			sender.sendMessage(ChatColor.RED + "Usage: /" + cmd.getName() + " <enable/disable/reload>");
		} else {
			sender.sendMessage(Config.ERROR_NO_PERMISSION);
		}
		return false;
	}
	
	public void save() {
		ChatWithAround.getChatWithAround().config.save();
	}

}
