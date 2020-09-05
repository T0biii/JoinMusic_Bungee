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

public final class JoinMusicBungee extends Plugin  {

    @Override
    public void onEnable() {
        getProxy().registerChannel( "JoinMusic" );
        getProxy().getPluginManager().registerListener(this, new PlayerJoin(this));

        getLogger().info( "Plugin enabled!" );

    }

    @Override
    public void onDisable() {
        getLogger().info( "Plugin disabled!" );
    }

}
