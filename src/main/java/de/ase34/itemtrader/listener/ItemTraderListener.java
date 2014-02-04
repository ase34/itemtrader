package de.ase34.itemtrader.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.kitteh.tag.TagAPI;

import de.ase34.itemtrader.ItemTraderPlugin;
import de.ase34.itemtrader.event.OfferTradedEvent;
import de.ase34.itemtrader.event.TradingStartEvent;
import de.ase34.itemtrader.event.TradingStopEvent;
import de.ase34.itemtrader.util.ItemUtils;

public class ItemTraderListener implements Listener {
    
    private ItemTraderPlugin plugin;
    
    public ItemTraderListener(ItemTraderPlugin plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onTrade(OfferTradedEvent ev) {
        plugin.getLanguageStrings().send(ev.getTrader().getPlayer(), "items-traded", ev.getCustomer().getName(), ItemUtils.itemToString(ev.getOffer().getProduct()), ItemUtils.itemToString(ev.getOffer().getPrice()));
    }
    
    @EventHandler
    public void onStopTrading(TradingStopEvent ev) {
        if (plugin.getServer().getPluginManager().getPlugin("TagAPI") != null) {
            TagAPI.refreshPlayer(ev.getTrader().getPlayer());
        }
    }
    
    @EventHandler
    public void onStartTrading(TradingStartEvent ev) {
        if (plugin.getServer().getPluginManager().getPlugin("TagAPI") != null) {
            TagAPI.refreshPlayer(ev.getTrader().getPlayer());
        }
    }

}
