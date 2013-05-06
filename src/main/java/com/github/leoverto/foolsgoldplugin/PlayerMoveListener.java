package com.github.leoverto.foolsgoldplugin;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerMoveListener implements Listener {
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		if (event.getPlayer().getWorld().hasStorm()) { //Make sure it's raining where the player is
			Location playerLocation = event.getTo(); //Find where the player moved to
			int highestBlockY = event.getPlayer().getWorld().getHighestBlockYAt(playerLocation); //Find the highest block where the player moved to
			int playerLocationY = playerLocation.getBlockY(); //Find the player's height
			if (highestBlockY < playerLocationY) { //If the highest block on the player's y is below the player...
				List<MetadataValue> values = event.getPlayer().getMetadata("weGaveSlow"); //Grab the metadata that tells us whether we gave him a slow effect or not.
				for (MetadataValue value : values) { //Get the metadata value out of the list it returns.
					 if (value.asBoolean() && event.getPlayer().hasPotionEffect(PotionEffectType.SLOW)) { //If we did give the player a slow effect and he has a slow effect...
						 event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1)); //Renew his slow effect.
					 }
					 if (!value.asBoolean() && !event.getPlayer().hasPotionEffect(PotionEffectType.SLOW)) { //If we didn't give him a slow effect and he doesn't have a slow effect...
						 event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1)); //Give him a slow effect.
						 event.getPlayer().setMetadata("weGaveSlow", new FixedMetadataValue(new FoolsGoldPlugin(), true)); //Set the metadata to true.
					 }
				 }
			} else { //If the highest block on the player's y is above the player, e.g. he's under cover...
				if (event.getPlayer().hasMetadata("weGaveSlow")) { //Make sure he has the weGaveSlow metadata, otherwise this part is redundant.
					 List<MetadataValue> values = event.getPlayer().getMetadata("weGaveSlow"); //Grab the metadata
					 for (MetadataValue value : values) { //Get the metadata out of the list it returns.
						 if (value.asBoolean() && event.getPlayer().hasPotionEffect(PotionEffectType.SLOW)) { //If we gave the player a slow effect, and he has a slow effect...
							 event.getPlayer().removePotionEffect(PotionEffectType.SLOW); //Remove his slow effect.
							 event.getPlayer().setMetadata("weGaveSlow", new FixedMetadataValue(new FoolsGoldPlugin(), false)); //Set the weGaveSlow metadata to false.
						 }
					 }
				}
			}
		}
	}
	
	
}
