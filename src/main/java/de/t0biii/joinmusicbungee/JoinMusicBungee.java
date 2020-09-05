package de.t0biii.joinmusicbungee;

import net.md_5.bungee.api.plugin.Plugin;

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
