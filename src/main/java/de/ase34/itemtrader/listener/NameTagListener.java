package de.ase34.itemtrader.listener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.kitteh.tag.PlayerReceiveNameTagEvent;

import de.ase34.itemtrader.ItemTraderPlugin;

public class NameTagListener implements Listener {

    private ItemTraderPlugin plugin;

    public NameTagListener(ItemTraderPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerNameTag(PlayerReceiveNameTagEvent ev) {
        if (plugin.getTrandingPlayersManager().isTrading(ev.getNamedPlayer())) {
            String name = ev.isModified() ? ev.getTag() : ev.getPlayer().getName();
            ev.setTag(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("name-prefix")) + name);
        }
    }

}
