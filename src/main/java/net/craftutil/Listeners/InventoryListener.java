package net.craftutil.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;

import net.craftutil.GUI.SettingsGUI;

public class InventoryListener implements Listener {

	SettingsGUI settingsGUI = new SettingsGUI();

	@EventHandler
	public void handleDrag(InventoryDragEvent e) {
		if (e.getInventory().getType().equals(InventoryType.PLAYER)) {
			return;
		}
		String name = e.getView().getTitle();
		if (name.equalsIgnoreCase(settingsGUI.invName)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void InventoryClick(InventoryClickEvent e) {
		if (e.getInventory().getType().equals(InventoryType.PLAYER)) {
			return;
		}
		String name = e.getView().getTitle();
		if (name.equalsIgnoreCase(settingsGUI.invName)) {
			this.settingsGUI.handleClick(e);
		}
	}
}
