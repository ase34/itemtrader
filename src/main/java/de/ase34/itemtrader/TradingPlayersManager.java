package de.ase34.itemtrader;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class TradingPlayersManager {

    private ArrayList<TradingPlayer> tradingPlayers = new ArrayList<TradingPlayer>();

    public TradingPlayer getTradingPlayer(Player player) {
        for (TradingPlayer tradingPlayer : tradingPlayers) {
            if (tradingPlayer.getPlayer().equals(player)) {
                return tradingPlayer;
            }
        }

        TradingPlayer newPlayer = new TradingPlayer(player);
        tradingPlayers.add(newPlayer);
        return newPlayer;
    }

    public TradingPlayer getTradingPlayerFromCustomer(Player customer) {
        for (TradingPlayer tradingPlayer : tradingPlayers) {
            if (tradingPlayer.getCustomers().contains(customer)) {
                return tradingPlayer;
            }
        }
        return null;
    }

    public void removeTradingPlayer(Player player) {
        for (int i = 0; i < tradingPlayers.size(); i++) {
            TradingPlayer tradingPlayer = tradingPlayers.get(i);
            if (tradingPlayer.getPlayer().equals(player)) {
                tradingPlayers.remove(i);
            }
        }
    }

    public void removeCustomer(Player customer) {
        for (TradingPlayer tradingPlayer : tradingPlayers) {
            tradingPlayer.getCustomers().remove(customer);
        }
    }
}
