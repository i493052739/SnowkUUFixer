package com.snowk.SnowkUUFixer.listener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import com.snowk.SnowkUUFixer.SnowkUUFixer;

public class CustomInvListener implements Listener{
	@EventHandler
	public void onClickUUMachine(InventoryClickEvent e) {

		String msg_1 = SnowkUUFixer.snowkPlugin.getConfig().getString("Message-1");
		List<String> bannedList = SnowkUUFixer.snowkPlugin.getConfig().getStringList("bannedItemId");

		try {

			String blockName = e.getInventory().getTitle().toString();
		
			if (blockName.equals("ic2.te.scanner")) {
				@SuppressWarnings("deprecation")
				String blockId = String.valueOf(e.getCurrentItem().getTypeId());
				String blockDamage = extractMessageByRegular(e.getCurrentItem().getData().toString()).get(0);
				String blockStr = blockId+":"+blockDamage;
		
				if (Integer.parseInt(blockDamage)==0){
					if (bannedList.contains(blockId)) {
						e.setCancelled(true);
						e.getWhoClicked().sendMessage(msg_1);
					}
				} else {
					if (bannedList.contains(blockStr)) {
						e.setCancelled(true);
						e.getWhoClicked().sendMessage(msg_1);
					}
				}
			}
		} catch (Exception err) {
			return;
		}
	}
	/**
	 * 使用正则表达式提取中括号中的内容
	 * @param msg
	 * @return 
	 */
	public static List<String> extractMessageByRegular(String msg){
		List<String> list=new ArrayList<String>();
		Pattern p = Pattern.compile("(\\[[^\\]]*\\])");
		Matcher m = p.matcher(msg.replace("(", "[").replace(")", "]"));
		while(m.find()){
			list.add(m.group().substring(1, m.group().length()-1));
		}
		return list;
	}

}
