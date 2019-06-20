package me.kevinnovak.mavennmstutorial;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class MavenNMSTutorial extends JavaPlugin {
    static InternalsProvider internals;

    @Override
    public void onEnable() {
        super.onEnable();
        getServer().getLogger().info("Starting");
        try {
            String packageName = MavenNMSTutorial.class.getPackage().getName();
            String internalsName = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
            internals = (InternalsProvider) Class.forName(packageName + "." + internalsName).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ClassCastException exception) {
            Bukkit.getLogger().log(Level.SEVERE, "MavenNMSTutorial could not find a valid implementation for this server version.");
        }

        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            internals.sendActionbar(player, "Hello world!");
        }
    }

    @Override
    public void onDisable() {
        getServer().getLogger().info("Stopping");
    }
}
