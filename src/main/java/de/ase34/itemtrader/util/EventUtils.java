package de.ase34.itemtrader.util;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

import de.ase34.itemtrader.ItemTraderPlugin;

public class EventUtils {

    public static void disableShiftClickTrades(ItemTraderPlugin plugin, InventoryClickEvent ev) {
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

        Player player = (Player) ev.getWhoClicked();
        if (plugin.getTrandingPlayersManager().getTradingPlayerByCustomer(player) == null) {
            return;
        }

        player.getInventory().setContents(ev.getView().getBottomInventory().getContents());
        ev.setCancelled(true);
    }

}
