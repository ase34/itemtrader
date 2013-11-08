package de.ase34.itemtrader;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.kitteh.tag.TagAPI;

public class TradingPlayersManager {

    private List<TradingPlayer> tradingPlayers;
    private ItemTraderPlugin plugin;

    public TradingPlayersManager(ItemTraderPlugin plugin) {
        tradingPlayers = new ArrayList<TradingPlayer>();
        this.plugin = plugin;
    }

    public boolean startTrading(Player player, OfferList offers) {
        updatePlayerName(player);
        return tradingPlayers.add(new TradingPlayer(plugin, player, offers));
    }

    public boolean stopTrading(Player player) {
        updatePlayerName(player);
        TradingPlayer trader = getTradingPlayer(player);
        return trader != null ? tradingPlayers.remove(trader) : false;
    }

    public TradingPlayer getTradingPlayer(Player player) {
        for (TradingPlayer trader : tradingPlayers) {
            if (trader.getPlayer().equals(player)) {
                return trader;
            }
        }
        return null;
    }

    public TradingPlayer getTradingPlayer(String playername) {
        for (TradingPlayer trader : tradingPlayers) {
            if (trader.getPlayer().getName().equals(playername)) {
                return trader;
            }
        }
        return null;
    }

    public boolean isTrading(Player player) {
        return getTradingPlayer(player) != null;
    }

    public boolean isTrading(String playername) {
        return getTradingPlayer(playername) != null;
    }

    public TradingPlayer getTradingPlayerByCustomer(Player customer) {
        for (TradingPlayer trader : tradingPlayers) {
            if (trader.getCustomer().equals(customer)) {
                return trader;
            }
        }
        return null;
    }

    private void updatePlayerName(Player player) {
        if (plugin.getServer().getPluginManager().getPlugin("TagAPI") != null)
            TagAPI.refreshPlayer(player);
    }

}
