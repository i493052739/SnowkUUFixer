package com.snowk.snowkUUFixer.listener;

import java.util.List;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import com.snowk.snowkUUFixer.SnowkUUFixer;

public class InvListener implements Listener{
	@EventHandler
	public void onClickUUMachine(InventoryClickEvent e) {

		String msg_1 = SnowkUUFixer.snowkPlugin.getConfig().getString("Message-1");
		List<String> bannedList = SnowkUUFixer.snowkPlugin.getConfig().getStringList("bannedItemId");

		try {

			String blockName = e.getInventory().getTitle().toString();
		
			if (blockName.equals("ic2.te.scanner")) {
				@SuppressWarnings("deprecation")
				String blockId = String.valueOf(e.getCurrentItem().getTypeId());
				String blockDamage = e.getCurrentItem().getDurability()+"";
				String blockStr = blockId+":"+blockDamage;
		
				if (Integer.parseInt(blockDamage)==0){ // without damage
					if (bannedList.contains(blockId)) {
						e.setCancelled(true);
						e.getWhoClicked().sendMessage(msg_1);
					}
				} else {
					if (bannedList.contains(blockStr)) { // with damage
						e.setCancelled(true);
						e.getWhoClicked().sendMessage(msg_1);
					}
				}
			}
		} catch (Exception err) {
			return;
		}
	}
}
