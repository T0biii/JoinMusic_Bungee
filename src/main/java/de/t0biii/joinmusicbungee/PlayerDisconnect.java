package de.t0biii.joinmusicbungee;

import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerDisconnect implements Listener {

    @EventHandler
    public void onLeave(PlayerDisconnectEvent event){
        Utils.sendCustomData(event.getPlayer(), "Disconnect", "Data");
    }

}
