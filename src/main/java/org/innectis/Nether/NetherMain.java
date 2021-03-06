package org.innectis.Nether;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

/**
 * Innectis Nether plugin class
 * <p/>
 * Features:
 * Single-player style Nether portals
 *
 * @author Innectis
 */
public class NetherMain extends JavaPlugin {
    private final NetherPlayerListener playerListener = new NetherPlayerListener(this);

    public void onEnable() {
        Configuration properties = this.getConfiguration();
        String tempString;

        // Check the config.yml
        tempString = properties.getString("nether-world-name");
        if ((tempString == null) || tempString.isEmpty()) {
            properties.setProperty("nether-world-name", "netherworld");
            properties.setProperty("default-normal-world", "world");
            properties.save();
        }
        //TODO: reload the properties so it's less lame

        // Register events
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_RESPAWN, playerListener, Priority.High, this);

        // Say hi
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println(pdfFile.getName() + " v" + pdfFile.getVersion() + " enabled");
    }

    public void onDisable() {
    }
}
