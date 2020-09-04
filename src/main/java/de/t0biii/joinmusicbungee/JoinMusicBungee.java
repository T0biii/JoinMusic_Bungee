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
        out.writeUTF( channel );
        out.writeUTF( data1 );
        out.writeInt( data2 );
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
