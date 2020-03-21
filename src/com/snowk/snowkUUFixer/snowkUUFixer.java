package com.snowk.SnowkUUFixer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import com.snowk.snowkUUFixer.listener.CustomInvListener;


public class SnowkUUFixer extends JavaPlugin{
	
	public static SnowkUUFixer snowkPlugin;
	
    @Override
    public void onEnable() {
    	saveDefaultConfig();

    	snowkPlugin = this;
    	getLogger().info("SnowkUUFixer successfully enabled! - By:Bear");
    	registerListeners();
    }
    
    
    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new CustomInvListener(), this);
    }

    @Override
    public void onDisable() {
    	getLogger().info("SnowkUUFixer successfully disabled!");
    	
    }
    	
}
