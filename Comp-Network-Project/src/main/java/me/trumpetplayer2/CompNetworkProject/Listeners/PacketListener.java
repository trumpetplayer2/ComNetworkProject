package me.trumpetplayer2.CompNetworkProject.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.comphenix.protocol.events.PacketEvent;

import me.trumpetplayer2.CompNetworkProject.Main;

public class PacketListener implements Listener {
	@EventHandler
	public void packetRecieved(PacketEvent e) {
	    //Get player from event
		Player p = e.getPlayer(); 
		//Get instance and check if player is loaded
		Main instance = Main.getInstance();
		//Get if player is listening to packets, if not, return
		if(instance.getPlayerState(p.getUniqueId().toString()) == null) {return;}
		if(!instance.getPlayerState(p.getUniqueId().toString())) {return;}
		//Send packet as a string to the player
		p.sendMessage(e.getPacket().toString());
	}
}
