package dev.mazyar8.chatwitharound.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import dev.mazyar8.chatwitharound.config.Config;
import dev.mazyar8.chatwitharound.permission.PermissionHelper;
import dev.mazyar8.chatwitharound.util.FormatUtil;

public class GlobalCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String message, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Config.ERROR_JUST_PLAYER_ALLOW_TO_USE);
			return false;
		}
		
		Player player = (Player) sender;
		if (!player.hasPermission(PermissionHelper.COMMAND_GLOBAL)) {
			player.sendMessage(Config.ERROR_NO_PERMISSION);
			return false;
		}
		
		String textMessage = "";
		for (String s : args)
			textMessage = textMessage.concat(" ".concat(s));
		this.broadcast(player, textMessage);
		return true;
	}
	
	public void broadcast(Player sender, String message) {
		for (Player p : Bukkit.getOnlinePlayers())
			this.sendMessage(p, sender, message);
	}
	
	public void sendMessage(Player receiver, Player sender, String message) {
		receiver.sendMessage(FormatUtil.getFinalMessage(sender, message, Config.GLOBAL_CHAT_FORMAT));
	}

}
