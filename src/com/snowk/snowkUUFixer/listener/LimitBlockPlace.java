package com.snowk.snowkUUFixer.listener;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.snowk.snowkUUFixer.SnowkUUFixer;
import com.snowk.snowkUUFixer.utils.GetSideBlocks;
import com.snowk.snowkUUFixer.utils.NBTdump;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
	
public class LimitBlockPlace implements Listener{
	
	@SuppressWarnings({ "deprecation" })
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		
		List<Integer> bannedBlocks = SnowkUUFixer.snowkPlugin.getConfig().getIntegerList("bannedBlockId");
		String scannerId = SnowkUUFixer.snowkPlugin.getConfig().getString("ScannerId").replace("[", "").replace("]", "");
		String msg_2 = SnowkUUFixer.snowkPlugin.getConfig().getString("Message-2");
		String msg_3 = SnowkUUFixer.snowkPlugin.getConfig().getString("Message-3");
		
		int IC2_TE_ID = Integer.parseInt(scannerId.split(":")[0]);
		int SCANNER_DAMAGE = Integer.parseInt(scannerId.split(":")[1]);
		
		Location mainBlockLocation = e.getBlock().getLocation();
		int mainBlockId = e.getItemInHand().getType().getId(); // block ID in hand
		int mainBlockDamage = e.getItemInHand().getDurability(); // block data in hand
				
		for (int bannedId : bannedBlocks) {
			// 弹出升级的越权限制1：扫描机不能与IC2_TE相连
			if (mainBlockId == IC2_TE_ID && mainBlockDamage == SCANNER_DAMAGE) {
				Location[] sixSides = GetSideBlocks.getSideBlocks(mainBlockLocation);
				if (sixSides[0].getBlock().getType().toString().equals("IC2_TE") ||
					sixSides[1].getBlock().getType().toString().equals("IC2_TE") ||
					sixSides[2].getBlock().getType().toString().equals("IC2_TE") ||
					sixSides[3].getBlock().getType().toString().equals("IC2_TE") ||
					sixSides[4].getBlock().getType().toString().equals("IC2_TE") ||
					sixSides[5].getBlock().getType().toString().equals("IC2_TE") ) {
					e.setCancelled(true);
					e.getPlayer().sendMessage(msg_2);
					break;
				}
				
			} else if (mainBlockId == bannedId) {
				// AE，BC, 漏斗，弹出升级的限制2，让特定方块不可与扫描机相连
				Location[] sixSides = GetSideBlocks.getSideBlocks(mainBlockLocation);
				NBTTagCompound nbt_1 = NBTdump.getNBT(sixSides[0]);
				NBTTagCompound nbt_2 = NBTdump.getNBT(sixSides[1]);
				NBTTagCompound nbt_3 = NBTdump.getNBT(sixSides[2]);
				NBTTagCompound nbt_4 = NBTdump.getNBT(sixSides[3]);
				NBTTagCompound nbt_5 = NBTdump.getNBT(sixSides[4]);
				NBTTagCompound nbt_6 = NBTdump.getNBT(sixSides[5]);
				boolean isScanner_1 = nbt_1.toString().contains("ic2:scanner");
				boolean isScanner_2 = nbt_2.toString().contains("ic2:scanner");
				boolean isScanner_3 = nbt_3.toString().contains("ic2:scanner");
				boolean isScanner_4 = nbt_4.toString().contains("ic2:scanner");
				boolean isScanner_5 = nbt_5.toString().contains("ic2:scanner");
				boolean isScanner_6 = nbt_6.toString().contains("ic2:scanner");
				if (isScanner_1 || isScanner_2 || isScanner_3 || isScanner_4 || isScanner_5 || isScanner_6) {
					e.setCancelled(true);
					e.getPlayer().sendMessage(msg_3);
					break;
				}
			}
		}
	}
}