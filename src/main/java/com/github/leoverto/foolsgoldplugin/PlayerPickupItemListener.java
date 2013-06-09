package com.github.leoverto.foolsgoldplugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerPickupItemListener implements Listener {
	
	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent event) {
		event.getItem();
		if (FoolsGoldPlugin.itemsToNotPickup.contains(event.getItem().getEntityId())) {
			event.setCancelled(true);
		}
	}

}
