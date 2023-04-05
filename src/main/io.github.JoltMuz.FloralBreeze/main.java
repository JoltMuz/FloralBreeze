package io.github.JoltMuz.FloralBreeze;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin implements Listener, CommandExecutor
{
	@Override
    public void onEnable()
	{
		this.getCommand("floral").setExecutor(this);
		getServer().getPluginManager().registerEvents(this, this);
		
		NamespacedKey key = new NamespacedKey(this, "FloralBreeze");
		ShapedRecipe recipe = new ShapedRecipe(key, FloralBreeze);
		
		recipe.shape("123","456","789");
		recipe.setIngredient('1', Material.POPPY);
		recipe.setIngredient('2', Material.ALLIUM);
		recipe.setIngredient('3', Material.CORNFLOWER);
		recipe.setIngredient('4', Material.ORANGE_TULIP);
		recipe.setIngredient('5', Material.DIAMOND_BOOTS);
		recipe.setIngredient('6', Material.WHITE_TULIP);
		recipe.setIngredient('7', Material.PINK_TULIP);
		recipe.setIngredient('8', Material.DANDELION);
		recipe.setIngredient('9', Material.BLUE_ORCHID);

		Bukkit.addRecipe(recipe);
		
    }
	
	static ArrayList<Material> Flowers = new ArrayList<Material>();
	static ItemStack FloralBreeze = new ItemStack(Material.DIAMOND_BOOTS,1);
	static
	{
		Flowers.add(Material.POPPY);
		Flowers.add(Material.ALLIUM);
		Flowers.add(Material.CORNFLOWER);
		Flowers.add(Material.ORANGE_TULIP);
		Flowers.add(Material.PINK_TULIP);
		Flowers.add(Material.WHITE_TULIP);
		Flowers.add(Material.DANDELION);
		Flowers.add(Material.BLUE_ORCHID);
		

		
		ItemMeta FloralBreezeMeta = FloralBreeze.getItemMeta();
		FloralBreezeMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Floral" + ChatColor.DARK_PURPLE + "Breeze");
		FloralBreeze.setItemMeta(FloralBreezeMeta);
		
		
	}
	
	@Override
    public boolean onCommand(CommandSender Sender, Command command, String alias, String[] args)
    {
		
		if (Sender instanceof Player)
		{
			((Player) Sender).getInventory().addItem(FloralBreeze);
		}
		else
		{
			Sender.sendMessage(ChatColor.RED + "You must be a player to use this command");
		}
		return true;
		
    }
	
	
	@EventHandler
	void onMove(PlayerMoveEvent e)
	{
		int randomNumber = new Random().nextInt(Flowers.size());
		
		if (e.getPlayer().getInventory().getBoots().getItemMeta().getDisplayName().equals(ChatColor.LIGHT_PURPLE + "Floral" + ChatColor.DARK_PURPLE + "Breeze"))
		{
			
			e.getPlayer().getLocation().getBlock().setType(Flowers.get(randomNumber));
		}
	}

}
