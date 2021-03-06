package de.ase34.itemtrader.listener;

import java.util.List;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.metadata.MetadataValue;

import de.ase34.itemtrader.ItemTraderPlugin;
import de.ase34.itemtrader.TradingPlayer;

public class PlayerListener implements Listener {

    private ItemTraderPlugin plugin;

    public PlayerListener(ItemTraderPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent ev) {
        if (ev.getRightClicked().getType() == EntityType.PLAYER) {
            TradingPlayer trader = plugin.getTrandingPlayersManager().getTradingPlayer((Player) ev.getRightClicked());
            if (trader == null || !trader.isCurrentlyTrading()) {
                return;
            }

            trader.openWindow(ev.getPlayer());
        }
        return;
    }

    @EventHandler
    public void onPlayerDropItem(final PlayerDropItemEvent ev) {
        if (ev.getPlayer().hasMetadata("itemtrader-exit-timestamp")) {
            List<MetadataValue> timestamps = ev.getPlayer().getMetadata("itemtrader-exit-timestamp");

            long timestamp = Long.MIN_VALUE;
            for (MetadataValue value : timestamps) {
                if (value.getOwningPlugin().equals(plugin))
                    timestamp = value.asLong();
            }
            ev.getPlayer().removeMetadata("itemtrader-exit-timestamp", plugin);

            if (System.currentTimeMillis() <= timestamp + 1000L) {
                ev.setCancelled(true);
                plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                    @SuppressWarnings("deprecation")
                    @Override
                    public void run() {
                        ev.getPlayer().updateInventory();
                    }

                }, 1);
            }
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent ev) {
        if (!plugin.getTrandingPlayersManager().getTradingPlayer(ev.getPlayer()).isCurrentlyTrading()) {
            return;
        }

        if (plugin.getConfig().getDouble("max-movement") < 0) {
            return;
        }

        double distanceSquared = ev.getTo().distanceSquared(ev.getFrom());

        if (Math.pow(plugin.getConfig().getDouble("max-movement"), 2d) < distanceSquared) {
            if (!plugin.getConfig().getBoolean("allow-movement")) {
                ev.getPlayer().teleport(ev.getFrom());
                plugin.getLanguageStrings().send(ev.getPlayer(), "no-movement");
            } else {
                plugin.getLanguageStrings().send(ev.getPlayer(), "max-movement-stop-trading");
                plugin.getTrandingPlayersManager().getTradingPlayer(ev.getPlayer()).stopTrading();
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent ev) {
        plugin.getTrandingPlayersManager().removeTradingPlayer(ev.getPlayer());
        plugin.getTrandingPlayersManager().removeCustomer(ev.getPlayer());
    }

}
