package com.snowk.snowkUUFixer.utils;

import org.bukkit.Location;
import org.bukkit.block.BlockState;


import de.tr7zw.nbtapi.NBTReflectionUtil;
import net.minecraft.server.v1_12_R1.NBTTagCompound;

public class NBTdump {
	public static NBTTagCompound getNBT (Location loc) {

		BlockState tile = loc.getBlock().getState();
		if (!tile.toString().contains("CraftBlockState")) { // is tile
			// do NBTdump
			NBTTagCompound nbt = (NBTTagCompound) NBTReflectionUtil.getTileEntityNBTTagCompound(tile);
			return nbt;
		} else { // is normal block
			// create empty NBT
			NBTTagCompound nbt = new NBTTagCompound();
			return nbt;
		}

	}
}
