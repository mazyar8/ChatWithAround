package dev.mazyar8.chatwitharound.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.mazyar8.chatwitharound.config.Config;
import dev.mazyar8.chatwitharound.permission.PermissionHelper;
import dev.mazyar8.chatwitharound.util.DistanceUtil;
import dev.mazyar8.chatwitharound.util.FormatUtil;

public class ShoutCommand implements CommandExecutor {

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
		for (Player p : sender.getWorld().getPlayers())
			this.sendMessage(p, sender, DistanceUtil.getDistances(p.getLocation(), sender.getLocation()), message);
	}
	
	public void sendMessage(Player receiver, Player sender, double[] distances, String message) {
		double x = distances[0], y = distances[1], z = distances[2];
		if (x <= Config.DISTANCE_SHOUT_LOW_DISTANCES_X && y <= Config.DISTANCE_SHOUT_LOW_DISTANCES_Y && z <= Config.DISTANCE_SHOUT_LOW_DISTANCES_Z)
			receiver.sendMessage(FormatUtil.getFinalMessage(sender, message, Config.DISTANCE_SHOUT_LOW_CHAT_FORMAT));
		else if (x <= Config.DISTANCE_SHOUT_HIGH_DISTANCES_X && y <= Config.DISTANCE_SHOUT_HIGH_DISTANCES_Y && z <= Config.DISTANCE_SHOUT_HIGH_DISTANCES_Z)
			receiver.sendMessage(FormatUtil.getFinalMessage(sender, message, Config.DISTANCE_SHOUT_HIGH_CHAT_FORMAT));
	}

}
