package net.craftutil.GUI.Items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Items {

	public static ItemStack BackwardButton(boolean active, int page) {
		if (!active) {
			return GreyGlassPane();
		} else {
			ItemStack stack = new ItemStack(Material.ARROW);
			ItemMeta meta = stack.getItemMeta();

			assert meta != null;

			meta.setDisplayName("§6Page " + page);
			stack.setItemMeta(meta);
			return stack;
		}
	}

	public static ItemStack blackglass() {
		ItemStack stack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta meta = stack.getItemMeta();

		assert meta != null;

		meta.setDisplayName(" ");
		stack.setItemMeta(meta);
		return stack;
	}

	public static ItemStack ForwardButton(boolean active, int page) {
		if (!active) {
			return GreyGlassPane();
		} else {
			ItemStack stack = new ItemStack(Material.ARROW);
			ItemMeta meta = stack.getItemMeta();

			assert meta != null;

			meta.setDisplayName("§6Page " + page);
			stack.setItemMeta(meta);
			return stack;
		}
	}

	public static ItemStack GreyGlassPane() {
		ItemStack stack = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta meta = stack.getItemMeta();

		assert meta != null;

		meta.setDisplayName(" ");
		stack.setItemMeta(meta);
		return stack;
	}

}
