package com.snowk.snowkUUFixer.utils;

import org.bukkit.Location;
import org.bukkit.World;

public class GetSideBlocks {
	
	public static Location[] getSideBlocks(Location loc) {
		
		World w = loc.getWorld();
		int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();

		Location[] locs = new Location[6];
		locs[0] = new Location(w,x+1,y,z);
		locs[1] = new Location(w,x-1,y,z);
		locs[2] = new Location(w,x,y+1,z);
		locs[3] = new Location(w,x,y-1,z);
		locs[4] = new Location(w,x,y,z+1);
		locs[5] = new Location(w,x,y,z-1);
		return locs;
	}
}
