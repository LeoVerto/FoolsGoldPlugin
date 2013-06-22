package com.github.leoverto.foolsgoldplugin;

import java.util.HashMap;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerMoveListener implements Listener {
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		if ((Boolean) FoolsGoldPlugin.slowRainConfig.get("enabled")) {
			Player player = event.getPlayer();
			if (!player.getGameMode().equals(GameMode.CREATIVE)) { 
				if (player.getWorld().hasStorm()) { //Make sure it's raining where the player is
					Location playerLocation = event.getTo(); //Find where the player moved to
					int highestBlockY = player.getWorld().getHighestBlockYAt(playerLocation); //Find the highest block where the player moved to
					int playerLocationY = playerLocation.getBlockY() + 1; //Find the player's height
					if (highestBlockY < playerLocationY) { //If the highest block on the player's y is below the player...
						if (weGaveSlow.containsKey(player.getName())) {
							if (weGaveSlow.get(player.getName()) && player.hasPotionEffect(PotionEffectType.SLOW)) { //If we did give the player a slow effect and he has a slow effect...
								 player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 1), true); //Renew his slow effect.
							 }
							 if (!weGaveSlow.get(player.getName()) && !player.hasPotionEffect(PotionEffectType.SLOW)) { //If we didn't give him a slow effect and he doesn't have a slow effect...
								 player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 1), true); //Give him a slow effect.
								 weGaveSlow.put(player.getName(), true);
							 }
							 if (weGaveSlow.get(player.getName()) && !player.hasPotionEffect(PotionEffectType.SLOW)) {
								 player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 1), true);
							 }
						} else {
							if (!player.hasPotionEffect(PotionEffectType.SLOW)) {
								player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 1), true);
								weGaveSlow.put(player.getName(), true);
							}
						}
					} else { //If the highest block on the player's y is above the player, e.g. he's under cover...
						if (weGaveSlow.containsKey(player.getName())) { //Make sure he has the weGaveSlow metadata, otherwise this part is redundant.
							if (weGaveSlow.get(player.getName()) && player.hasPotionEffect(PotionEffectType.SLOW)) { //If we gave the player a slow effect, and he has a slow effect...
								 player.removePotionEffect(PotionEffectType.SLOW); //Remove his slow effect.
								 weGaveSlow.put(player.getName(), false);
							 }
						}
					}
				}
			}
		}
	}
	
	private HashMap<String, Boolean> weGaveSlow = new HashMap<String, Boolean>();
	
	
}
