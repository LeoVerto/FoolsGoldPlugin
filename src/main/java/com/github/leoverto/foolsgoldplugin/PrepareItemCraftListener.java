package com.github.leoverto.foolsgoldplugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

public class PrepareItemCraftListener implements Listener {
	
	@EventHandler
	public void onPrepareItemCraft(PrepareItemCraftEvent event) {
		CraftingInventory craftInventory = event.getInventory();
		ItemStack result = craftInventory.getResult();
		if (result.getTypeId() == 283 || result.getTypeId() == 284 || result.getTypeId() == 285 || result.getTypeId() == 286 || result.getTypeId() == 294 || result.getTypeId() == 314 || result.getTypeId() == 315 || result.getTypeId() == 316 || result.getTypeId() == 317) {
			craftInventory.setResult(new ItemStack(0));
		} else if (result.getTypeId() == 276 || result.getTypeId() == 277 || result.getTypeId() == 278 || result.getTypeId() == 279 || result.getTypeId() == 293 || result.getTypeId() == 310 || result.getTypeId() == 311 || result.getTypeId() == 312 || result.getTypeId() == 313) {
			craftInventory.setResult(new ItemStack(0));
		}
	}

}
