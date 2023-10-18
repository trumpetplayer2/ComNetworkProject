package me.trumpetplayer2.CompNetworkProject.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.comphenix.protocol.events.PacketEvent;

public class PacketListener implements Listener {
	@EventHandler
	public void packetRecieved(PacketEvent e) {
		Player p = e.getPlayer(); 
	}
}
