package dev.mazyar8.chatwitharound.util;

import org.bukkit.Location;

public class DistanceUtil {

	public static double[] getDistances(Location receiver, Location sender) {
		double deltaX = receiver.getX() - sender.getX();
		double deltaY = receiver.getY() - sender.getY();
		double deltaZ = receiver.getZ() - sender.getZ();
		return new double[] {Math.abs(deltaX), Math.abs(deltaY), Math.abs(deltaZ)};
	}
	
}
