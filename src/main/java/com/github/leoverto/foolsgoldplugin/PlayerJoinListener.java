package com.github.leoverto.foolsgoldplugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String playerName = player.getName();
		if (PlayerMoveListener.weGaveSlow.containsKey(playerName)) {
			PlayerMoveListener.weGaveSlow.remove(playerName);
		}
		player.setWalkSpeed(0.2f);
	}

}
