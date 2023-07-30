package com.vincentx0905.geyserfloodgateusernametonick;

import org.bstats.bukkit.Metrics;
import com.vincentx0905.geyserfloodgateusernametonick.event.OnPlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class GeyserFloodgate_UsernameToNick extends JavaPlugin implements Listener {

    private final File configFile = new File(getDataFolder(), "config.yml");

    @Override
    public void onEnable() {
        PluginManager pluginManager = getServer().getPluginManager();
        Plugin geyserfloodgateplugin = pluginManager.getPlugin("floodgate");
        if (geyserfloodgateplugin != null) {
            if (geyserfloodgateplugin.isEnabled()) {
                getLogger().info("Found Geyser-floodgate plugin, Enable plugin!");
                pluginManager.registerEvents(this, this);
                getDataFolder().mkdirs();
                if (!configFile.exists()) saveDefaultConfig();
                else {
                    String configVersion = "V1.0-Alpha";
                    FileConfiguration config = getConfig();
                    if (!configVersion.equals(config.getString("ConfigVersion"))) {
                        saveDefaultConfig();
                        reloadConfig();
                        getServer().getLogger().info("Geyser-UsernameToNick plugin update config file to" + configVersion);
                    }
                }
                OnPlayerJoin onPlayerJoin = new OnPlayerJoin(this);
                getServer().getPluginManager().registerEvents(onPlayerJoin, this);
                Metrics metrics = new Metrics(this, 19304);
                getLogger().info("Geyser-UsernameToNick plugin now Enable!" + " By:VincentX0905(炸蝦)");
            }
        } else {
            getLogger().warning("Not Found Geyser-floodgate plugin, Disable plugin!");
            Bukkit.getPluginManager().disablePlugin(this);
        }

    }

    @Override
    public void onDisable() {
        getLogger().info("Geyser-UsernameToNick plugin now Disable!" + " By:VincentX0905(炸蝦)");
    }
}
