package de.ase34.itemtrader.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.metadata.FixedMetadataValue;

import de.ase34.itemtrader.ItemTraderPlugin;
import de.ase34.itemtrader.TradingPlayer;

public class InventoryListener implements Listener {

    private ItemTraderPlugin plugin;

    public InventoryListener(ItemTraderPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerShiftClick(InventoryClickEvent ev) {
        if (ev.getInventory().getType() != InventoryType.MERCHANT) {
            return;
        }
        if (ev.getClick() != ClickType.SHIFT_LEFT) {
            return;
        }
        if (ev.getRawSlot() != 2) {
            return;
        }
        if (ev.getView().getItem(2) == null) {
            return;
        }

        final Player player = (Player) ev.getWhoClicked();
        if (plugin.getTrandingPlayersManager().getTradingPlayerByCustomer(player) == null) {
            return;
        }
        
        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            
            @SuppressWarnings("deprecation")
            @Override
            public void run() {
                player.updateInventory();
            }
        }, 1);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent ev) {
        if (ev.getInventory().getType() != InventoryType.MERCHANT) {
            return;
        }

        Player player = (Player) ev.getPlayer();
        TradingPlayer trader = plugin.getTrandingPlayersManager().getTradingPlayerByCustomer(player);
        if (trader == null) {
            return;
        }

        trader.setCustomer(null);
        player.setMetadata("itemtrader-exit-timestamp", new FixedMetadataValue(plugin, System.currentTimeMillis()));
    }

}
