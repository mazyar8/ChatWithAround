package dev.mazyar8.chatwitharound.util;

import org.bukkit.entity.Player;

import dev.mazyar8.chatwitharound.permission.PermissionHelper;

public class FormatUtil {

	public static String getFinalMessage(Player sender, String message, String format) {
		String[] phrases = new String[] {"{player}", "{prefix}", "{message}"};
		String[] alternatives = new String[] {sender.getName(), PrefixUtil.getPrefix(sender), sender.hasPermission(PermissionHelper.CHAT_COLOR) ? ColorUtils.replaceText(message) : message};
		String finalText = format;
		
		for (int i = 0; i < phrases.length; i++)
			finalText = finalText.replace(phrases[i], alternatives[i]);
		
		return finalText;
	}
	
}
