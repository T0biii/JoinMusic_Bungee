package de.t0biii.joinmusicbungee;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

public final class JoinMusicBungee extends Plugin implements Listener {

    @Override
    public void onEnable() {
        getProxy().registerChannel( "JoinMusic" );
        getProxy().getPluginManager().registerListener(this, this);

        getLogger().info( "Plugin enabled!" );

    }

    @Override
    public void onDisable() {
        getLogger().info( "Plugin disabled!" );
    }

    public void sendCustomData(ProxiedPlayer player,String channel ,String data1, int data2) {
        Collection<ProxiedPlayer> networkPlayers = ProxyServer.getInstance().getPlayers();
        // perform a check to see if globally are no players
        if ( networkPlayers == null || networkPlayers.isEmpty()) {
            return;
        }
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF( channel ); // the channel could be whatever you want
        out.writeUTF( data1 ); // this data could be whatever you want
        out.writeInt( data2 ); // this data could be whatever you want

        // we send the data to the server
        // using ServerInfo the packet is being queued if there are no players in the server
        // using only the server to send data the packet will be lost if no players are in it
        player.getServer().getInfo().sendData( "t0biii:joinmusic", out.toByteArray() );
    }

    @EventHandler
    public void onPostLogin(PostLoginEvent event) {

    ProxyServer.getInstance().getScheduler().schedule(this, new Runnable() {
        @Override
        public void run() {
            sendCustomData(event.getPlayer(), "Join", "Data", 3);
        }
    }, 3, TimeUnit.SECONDS);

    }

    @EventHandler
    public void onLeave(PlayerDisconnectEvent event){
        sendCustomData(event.getPlayer(), "Disconnect", "Data", 3);
    }


}
