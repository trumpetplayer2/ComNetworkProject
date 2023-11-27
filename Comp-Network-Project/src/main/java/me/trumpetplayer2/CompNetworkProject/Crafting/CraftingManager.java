package me.trumpetplayer2.CompNetworkProject.Crafting;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
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
		
		Main plugin = Main.getInstance(); // Use plugin in place of "this"
		
		NamespacedKey key = new NamespacedKey(plugin, "DoubleBladedNetheriteAxe");
		
		ShapedRecipe recipe = new ShapedRecipe(key, doubleBladedAxe);
		
		recipe.shape("NSN", "NSN", " S ");
		
		recipe.setIngredient('S', Material.STICK);
		recipe.setIngredient('N', Material.NETHERITE_INGOT);
		
		Bukkit.addRecipe(recipe);
	}
}
