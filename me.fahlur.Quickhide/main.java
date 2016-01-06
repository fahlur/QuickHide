package me.fahlur.QuickHide;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.dynmap.DynmapCommonAPI;

import com.earth2me.essentials.Essentials;

import net.md_5.bungee.api.ChatColor;

public class main extends JavaPlugin {
	
	public static Essentials ess3 = null;
	public static DynmapCommonAPI dynmap = null;
	
	@Override
	public void onEnable(){
		final Plugin dyn = this.getServer().getPluginManager().getPlugin("dynmap"); 
		final Plugin ess = this.getServer().getPluginManager().getPlugin("Essentials"); 
		
		if (dyn != null) { 
            this.dynmap = ((DynmapCommonAPI) dyn); 
            this.getLogger().info("Now hooking into Dynmap"); 
        }else{
        	this.getLogger().warning("You wanted Dynmap support. I could not find Dynmap."); 
        }
		
		
		if (ess != null) { 
            this.ess3 = ((Essentials) ess); 
            this.getLogger().info("Now hooking into Essentials"); 
        }else{
        	this.getLogger().warning("You wanted Essentials support. I could not find Essentials."); 
        }
		
		
		
		
	}
	public void onDisable(){	
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if (!(sender instanceof Player)){
			sender.sendMessage(ChatColor.RED + "You must be a player in game to be able to use this command!");
			return true;
		}
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("hide")){
			if (!sender.hasPermission("quickhide.hide")){
				sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
				return true;
			}
			ess3.getUser(player).setVanished(true);
			dynmap.setPlayerVisiblity(player.getName(), false);
			sender.sendMessage(ChatColor.GRAY + "You are now invisible in game and on dynmap!");
			
		}
		if (cmd.getName().equalsIgnoreCase("unhide")){
			if (!sender.hasPermission("quickhide.hide")){
				sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
				return true;
			}
			ess3.getUser(player).setVanished(false);
			dynmap.setPlayerVisiblity(player.getName(), true);
			sender.sendMessage(ChatColor.GREEN + "You are now visible in game and on dynmap!");
			
		}
		
		
		
		return true;
	}
			
	
	
}
