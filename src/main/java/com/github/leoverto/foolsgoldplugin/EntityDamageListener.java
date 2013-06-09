package com.github.leoverto.foolsgoldplugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class EntityDamageListener implements Listener {
	
	private Random random = new Random();
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onEntityBlock(EntityDamageEvent event) {
		if ((Boolean) FoolsGoldPlugin.noFallDamageInHayConfig.get("enabled")) {
			if (event.getCause().equals(DamageCause.FALL)) {
				if (event.getEntityType().equals(EntityType.PLAYER)) {
					Player ePlayer = (Player) event.getEntity();
					Location playerLocation = ePlayer.getLocation();
					Location locationBelowPlayer = new Location(ePlayer.getWorld(), playerLocation.getX(), playerLocation.getY() - 1, playerLocation.getZ());
					Block blockBelowPlayer = locationBelowPlayer.getBlock();
					if (blockBelowPlayer.getTypeId() == Material.SPONGE.getId()) {
						Location locBelowBelowPlayer = new Location(ePlayer.getWorld(), playerLocation.getX(), playerLocation.getY() - 2, playerLocation.getZ());
						Block blockBelowBelowPlayer = locBelowBelowPlayer.getBlock();
						int amountOfWheat;
						if (blockBelowBelowPlayer.getTypeId() == Material.SPONGE.getId()) {
							event.setDamage(0);
							amountOfWheat = 10;
						} else {
							event.setDamage(event.getDamage() / 2);
							amountOfWheat = 5;
						}
						final World playerWorld = ePlayer.getWorld();
						ItemStack flyingWheat = new ItemStack(Material.WHEAT, 1);
						List<Integer> thingsToDelete = new ArrayList<Integer>();
						for (int i = 1; i < amountOfWheat; i++) {
							thingsToDelete.add(playerWorld.dropItemNaturally(playerLocation, flyingWheat).getEntityId());
						}
						for (int i = 1; i < thingsToDelete.size(); i++) {
							FoolsGoldPlugin.itemsToNotPickup.add(thingsToDelete.get(i));
						}
						final List<Integer> thingsToDeleteFinal = thingsToDelete;
						new BukkitRunnable(){
							public void run() {
								for (int i: thingsToDeleteFinal) {
									Item flyingWheat = (Item) playerWorld.getEntities().get(thingsToDeleteFinal.get(i));
									flyingWheat.remove();
								}
							}
							
						}.runTaskLater(new FoolsGoldPlugin(), 20);
					}
				}
			}
		}
	}

}