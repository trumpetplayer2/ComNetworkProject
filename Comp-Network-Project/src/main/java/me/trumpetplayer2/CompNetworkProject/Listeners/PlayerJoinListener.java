package me.trumpetplayer2.CompNetworkProject.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.trumpetplayer2.CompNetworkProject.Main;

public class PlayerJoinListener implements Listener {
	//Ran whenever a player joins
	@EventHandler
	public void PlayerJoin(PlayerJoinEvent e) {
		//Grab instance and UUID of player who joined as a String
		Main instance = Main.getInstance();
		String UUID = e.getPlayer().getUniqueId().toString();
		//Check if player has previously joined since servers last restart
		//If they haven't, add them to the table as a false
		if(instance.getPlayerState(UUID) == null) {
			instance.setPlayerState(UUID, false);
		}
	}
}
