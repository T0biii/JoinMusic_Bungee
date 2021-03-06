package de.t0biii.joinmusicbungee;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.concurrent.TimeUnit;

public class PlayerJoin implements Listener {

    JoinMusicBungee plugin;
    public PlayerJoin(JoinMusicBungee joinMusicBungee) {
        this.plugin = joinMusicBungee;
    }

    @EventHandler
    public void onPostLogin(PostLoginEvent event) {

        ProxyServer.getInstance().getScheduler().schedule(plugin, new Runnable() {
            @Override
            public void run() {
                Utils.sendCustomData(event.getPlayer(), "Join", event.getPlayer().getName());
            }
        }, 3, TimeUnit.SECONDS);

    }

}
