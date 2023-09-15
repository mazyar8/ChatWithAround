package dev.mazyar8.chatwitharound.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.ChatColor;

public class ColorUtils {
	
	private static Pattern HEX_PATTERN = Pattern.compile("&#([A-Fa-f0-9]{6})");

	public static String replaceText(String text) {
		text = ChatColor.translateAlternateColorCodes('&', text);
		Matcher matcher = HEX_PATTERN.matcher(text);
		StringBuffer buffer = new StringBuffer();
		
		while (matcher.find())
			matcher.appendReplacement(buffer, net.md_5.bungee.api.ChatColor.of("#" + matcher.group(1)).toString());
		
		return ChatColor.translateAlternateColorCodes('&', matcher.appendTail(buffer).toString());
	}
	
}
