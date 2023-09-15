package dev.mazyar8.chatwitharound.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.cacheddata.CachedMetaData;

public class PrefixUtil {
	
	private static LuckPerms luckperms = Bukkit.getServicesManager().load(LuckPerms.class);
	
	public static String getPrefix(Player player) {
		CachedMetaData metaData = luckperms.getPlayerAdapter(Player.class).getMetaData(player);
		String prefix = metaData.getPrefix();
		prefix = prefix != null ? prefix : "";
		return ColorUtils.replaceText(prefix);
	}
	
}
