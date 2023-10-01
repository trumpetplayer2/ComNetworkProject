package me.trumpetplayer2.CompNetworkProject.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class NetworkPacketCommand implements TabCompleter, CommandExecutor{
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<String>();
        switch(cmd.getName().toLowerCase()) {
        case "example" :
            completions.add("example");
            break;
        }
        return completions;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        switch(cmd.getName().toLowerCase()) {
        case "networkpacket" : 
            sender.sendMessage("Example Message");
            return true;
        }
        return false;
    }
}
