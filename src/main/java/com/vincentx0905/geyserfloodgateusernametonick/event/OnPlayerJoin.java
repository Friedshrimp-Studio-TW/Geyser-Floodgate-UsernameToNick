package com.vincentx0905.geyserfloodgateusernametonick.event;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.geysermc.floodgate.api.FloodgateApi;

import java.util.Objects;

public class OnPlayerJoin implements Listener {
    private final JavaPlugin plugin;

    public OnPlayerJoin(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        FileConfiguration configuration = plugin.getConfig();
        if (configuration.getBoolean("Config.Always")){
            if (FloodgateApi.getInstance().isFloodgatePlayer(event.getPlayer().getUniqueId())  && !configuration.getStringList("Config.Bypass-Player").contains(event.getPlayer().getName())) {
                String name = String.format(Objects.requireNonNull(configuration.getString("Config.Nickname")), FloodgateApi.getInstance().getPlayer(event.getPlayer().getUniqueId()).getUsername());
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), String.format("nick %s %s", event.getPlayer().getName(), name));
            }
            if (!event.getPlayer().getScoreboardTags().contains("Bedrock")) event.getPlayer().addScoreboardTag("Bedrock");
        } else {
            if (!event.getPlayer().getScoreboardTags().contains("Bedrock")) {
                if (FloodgateApi.getInstance().isFloodgatePlayer(event.getPlayer().getUniqueId()) && !configuration.getStringList("Config.Bypass-Player").contains(event.getPlayer().getName())) {
                    String name = String.format(Objects.requireNonNull(configuration.getString("Config.Nickname")), FloodgateApi.getInstance().getPlayer(event.getPlayer().getUniqueId()).getUsername());
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), String.format("nick %s %s", event.getPlayer().getName(), name));
                    event.getPlayer().addScoreboardTag("Bedrock");
                }
            }
        }
    }
}
