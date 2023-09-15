package dev.mazyar8.chatwitharound.event;

import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import dev.mazyar8.chatwitharound.config.Config;
import dev.mazyar8.chatwitharound.util.DistanceUtil;
import dev.mazyar8.chatwitharound.util.FormatUtil;

public class ChatEvent extends Event {

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		if (!Config.ENABLED) return;
		this.broadcast(e.getRecipients(), e.getPlayer(), e.getMessage());
		e.getRecipients().clear();
	}
	
	public void broadcast(Set<Player> recipients, Player sender, String message) {
		for (Player p : recipients)
			this.sendMessage(p, sender, DistanceUtil.getDistances(p.getLocation(), sender.getLocation()), message);
	}
	
	public void sendMessage(Player receiver, Player sender, double[] distances, String message) {
		double x = distances[0], y = distances[1], z = distances[2];
		if (!receiver.getWorld().equals(sender.getWorld())) return;
		if (receiver.equals(sender))
			receiver.sendMessage(FormatUtil.getFinalMessage(sender, message, Config.SELF_CHAT_FORMAT));
		else if (x <= Config.DISTANCE_LOW_DISTANCES_X && y <= Config.DISTANCE_LOW_DISTANCES_Y && z <= Config.DISTANCE_LOW_DISTANCES_Z)
			receiver.sendMessage(FormatUtil.getFinalMessage(sender, message, Config.DISTANCE_LOW_CHAT_FORMAT));
		else if (x <= Config.DISTANCE_HIGH_DISTANCES_X && y <= Config.DISTANCE_HIGH_DISTANCES_Y && z <= Config.DISTANCE_HIGH_DISTANCES_Z)
			receiver.sendMessage(FormatUtil.getFinalMessage(sender, message, Config.DISTANCE_HIGH_CHAT_FORMAT));
	}
	
}
