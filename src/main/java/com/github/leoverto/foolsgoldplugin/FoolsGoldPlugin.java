package com.github.leoverto.foolsgoldplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class FoolsGoldPlugin extends JavaPlugin {
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
	}
	
	public void onDisable() {
		
	}

}
