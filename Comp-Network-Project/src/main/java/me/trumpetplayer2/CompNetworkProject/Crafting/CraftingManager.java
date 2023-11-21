package me.trumpetplayer2.CompNetworkProject.Crafting;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.trumpetplayer2.CompNetworkProject.Main;

public class CraftingManager {
	public void registerRecipes() {
		ItemStack doubleBladedAxe = new ItemStack(Material.NETHERITE_AXE);
		ItemMeta axeMeta = doubleBladedAxe.getItemMeta();
		
		axeMeta.setDisplayName(ChatColor.DARK_PURPLE + "Double Bladed Netherite Axe");
		
		ArrayList<String> Lore = new ArrayList<String>();
		Lore.add("Add Lore");
		
		axeMeta.setLore(Lore);
		doubleBladedAxe.setItemMeta(axeMeta);
		
		Main plugin = Main.getInstance(); // Use plugin for "this"
		
	}
}
