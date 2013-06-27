package com.github.leoverto.foolsgoldplugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		String playerName = player.getName();
		if (PlayerMoveListener.weGaveSlow.containsKey(playerName)) {
			if (PlayerMoveListener.weGaveSlow.get(playerName)) {
				PlayerMoveListener.weGaveSlow.remove(playerName);
			} else {
				PlayerMoveListener.weGaveSlow.remove(playerName);
			}
		}
		player.setWalkSpeed(0.2f);
	}

}
