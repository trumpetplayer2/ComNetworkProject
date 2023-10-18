package me.trumpetplayer2.CompNetworkProject.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import me.trumpetplayer2.CompNetworkProject.Main;
import net.md_5.bungee.api.ChatColor;

public class NetworkPacketCommand implements TabCompleter, CommandExecutor{
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<String>();
        switch(cmd.getName().toLowerCase()) {
        case "networkpacket" :
            completions.add("true");
            completions.add("false");
            break;
        }
        return completions;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	//Check if sender is player. If it is not, Send error and return
        if(!(sender instanceof Player)) {
        	sender.sendMessage("This command must be sent by a player");
        	return true;            	
        }
    	//Switch based on received command. Likely won't do anything aside from "networkpacket"
        switch(cmd.getName().toLowerCase()) {
        //If command is /networkpacket or an alias of it
        case "networkpacket" : 
        	//If args has no entries, send default
        	if(args.length < 1) {
            //Run Send Packet Info and provide player
            sendPacketInfo((Player) sender);
            //Return true as it worked
            return true;
        	}
        	if(args.length > 0) {
        		//Send the packet info with the first argument provided
        		sendPacketInfo((Player)sender, args[0]);
        		//Return True as command executed correctly
        		return true;
        	}
        }
        return false;
    }
    //This will be a toggle for player packet viewing
    public void sendPacketInfo(Player p) {
    	Main instance = Main.getInstance();
    	//If player is not in hashmap, add with true as value
    	if(instance.getPlayerState(p.getUniqueId().toString()) == null) {
    		instance.setPlayerState(p.getUniqueId().toString(), true);
    		//Tell player the current toggle state in gold text
    		p.sendMessage(ChatColor.GOLD + "Toggled packet monitoring to true");
    		return;
    	}
    	//Otherwise toggle the value by negating what was in the hashmap
    	instance.setPlayerState(p.getUniqueId().toString(), 
    			!instance.getPlayerState(p.getUniqueId().toString()));
    	//Tell player the current toggle state in gold text
    	p.sendMessage(ChatColor.GOLD + "Toggled packet monitoring to " + 
    			instance.getPlayerState(p.getUniqueId().toString()).toString());
    }
    //This will be a provided state (true/false)
    public void sendPacketInfo(Player p, String args) {
    	//Boolean for later use
    	boolean toggleState = false;
    	//Check what arg was provided. If it wasnt true or false, return
    	switch(args.toLowerCase()) {
    	case "true", "t":
    		toggleState = true;
    		break;
    	case "false", "f":
    		toggleState = false;
    		break;
    	default:
    		return;
    	}
    	//Set the togglestate to the state
    	Main.getInstance().setPlayerState(p.getUniqueId().toString(), toggleState);
    	//Tell player the current toggle state in gold text
    	p.sendMessage(ChatColor.GOLD + "Toggled packet monitoring to " + toggleState);
    }
}
