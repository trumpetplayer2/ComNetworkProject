package me.trumpetplayer2.CompNetworkProject;

import java.util.HashMap;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;

import me.trumpetplayer2.CompNetworkProject.Commands.NetworkPacketCommand;
import me.trumpetplayer2.CompNetworkProject.Listeners.*;

public class Main extends JavaPlugin{
    //Instance variable allows linking back to main plugin later on
	private static Main instance;
	private ProtocolManager protocolManager;
	//Hashmap that contains reference to players UUID as 
	//well as a boolean for if packet feed is enabled
	HashMap<String, Boolean> playerState = new HashMap<String, Boolean>();
	//Get Instance allows retrieving private variable instance
    public static Main getInstance() {
        //Retrieve instance
        return instance;
    }
    //On enable is a default method required. This is run on server start and on /reload.
    //It is recommended to never use /reload.
    //We must override JavaPlguins built in method
    @Override
    public void onEnable() {
    	//Define Instance
        instance = this;
        //Register command and the tab completion
        getCommand("networkpacket").setExecutor(new NetworkPacketCommand());
        getCommand("networkpacket").setTabCompleter(new NetworkPacketCommand());
        //Run register listener function. This is used for organization purposes
        registerListeners();
        //Register ProtocolManager
        protocolManager = ProtocolLibrary.getProtocolManager();
        enablePacketListeners();
    }
    //Default Disable. This is ran if Server stopped,
    //Plugin failed to load, or reload occurs
    @Override
    public void onDisable() {
    	//Nullify instance since its not needed and will cause memory leaks if left
        instance = null;
    }
    
    public void registerListeners() {
    	//Register listeners here. Follow format if needed to use
    	this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), instance);
    }
    
    //Get player state based on their UUID.
    //Defaults to false if UUID is invalid
    @Nullable
    public Boolean getPlayerState(String UUID) {
    	//If Player is not in the table return NULL
    	//If used, make sure null error detection
    	if(!playerState.containsKey(UUID)) {
    		return null;
    	}
    	//Return current state since not null
    	return playerState.get(UUID);
    }
    //Simple Set function to assign player state
    public void setPlayerState(String UUID, Boolean State) {
    	//Null detection to prevent errors
    	if(State == null) {return;}
    	//Set the Hashmap if State is valid
    	playerState.put(UUID, State);
    }
    
    public void enablePacketListeners() {
        //Add the packet Listener to watch the event
        protocolManager.addPacketListener(new PacketAdapter(
                this, //Connect to this plugin
                ListenerPriority.NORMAL, //Listener Priority is Normal, as it isn't important when it happens
                PacketType.Play.Server.REL_ENTITY_MOVE
                ) {
            @Override
            public void onPacketSending(PacketEvent e) {
                PacketContainer packet = e.getPacket();
                Player p = e.getPlayer();
                Boolean pState = getPlayerState(p.getUniqueId().toString());
                if(pState == null) {return;}
                if(!pState) {return;}
                //Lets get the packets, this event sends:
                //Entity ID - VarInt[0]
                //Delta X - Short[0]
                //Delta Y - Short[1]
                //Delta Z - Short[2]
                //OnGround - Boolean[0]
                p.sendMessage("Entity ID: " + packet.getIntegers().getValues().get(0) + 
                        " Delta X: " + packet.getShorts().getValues().get(0) + 
                        " Delta Y: " + packet.getShorts().getValues().get(1) +
                        " Delta Z: " + packet.getShorts().getValues().get(2));
            }
        });
    }
}
