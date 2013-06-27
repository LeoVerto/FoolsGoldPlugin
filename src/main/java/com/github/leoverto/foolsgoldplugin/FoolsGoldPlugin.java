package com.github.leoverto.foolsgoldplugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class FoolsGoldPlugin extends JavaPlugin {
	
	public void loadConfig() {
		slowRainConfig = this.getConfig().getConfigurationSection("slowRain").getValues(true);
		hayJumpConfig = this.getConfig().getConfigurationSection("hayJump").getValues(true);
	}
	
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		loadConfig();
		
		getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
		getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerPickupItemListener(), this);
	}
	
	public void onDisable() {
		
	}
	
	protected static void addWalkSpeed(Player player, float speed) {
		float currentWalkSpeed = player.getWalkSpeed();
		float newWalkSpeed = currentWalkSpeed + speed;
		player.setWalkSpeed(newWalkSpeed);
	}
	
	protected static void subtractWalkSpeed(Player player, float speed) {
		float currentWalkSpeed = player.getWalkSpeed();
		float newWalkSpeed = currentWalkSpeed - speed;
		player.setWalkSpeed(newWalkSpeed);
	}
	
	protected static Map<String, Object> slowRainConfig;
	protected static Map<String, Object> hayJumpConfig;
	protected static List<Integer> itemsToNotPickup = new ArrayList<Integer>();

}
