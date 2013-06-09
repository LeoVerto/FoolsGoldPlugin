package com.github.leoverto.foolsgoldplugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.plugin.java.JavaPlugin;

public class FoolsGoldPlugin extends JavaPlugin {
	
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		slowRainConfig = this.getConfig().getConfigurationSection("slowRain").getValues(true);
		noFallDamageInHayConfig = this.getConfig().getConfigurationSection("noFallDamageInHay").getValues(true);
		
		getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
		getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerPickupItemListener(), this);
	}
	
	public void onDisable() {
		
	}
	
	protected static Map<String, Object> slowRainConfig;
	protected static Map<String, Object> noFallDamageInHayConfig;
	protected static List<Integer> itemsToNotPickup = new ArrayList<Integer>();

}
