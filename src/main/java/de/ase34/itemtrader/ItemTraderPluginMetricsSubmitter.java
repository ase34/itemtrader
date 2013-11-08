package de.ase34.itemtrader;

import java.io.IOException;

import org.mcstats.Metrics;

public class ItemTraderPluginMetricsSubmitter {

    public static void submit(ItemTraderPlugin plugin) {
        try {
            Metrics metrics = new Metrics(plugin);
            if (!plugin.getConfig().getBoolean("development-mode")) {
                metrics.start();
            }
        } catch (IOException e) {
            plugin.getLogger().warning("Plugin statistics could not be submitted!");
            e.printStackTrace();
        }
    }

}
