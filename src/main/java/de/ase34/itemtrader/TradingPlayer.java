package de.ase34.itemtrader;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import de.ase34.itemtrader.event.TradingStartEvent;
import de.ase34.itemtrader.event.TradingStopEvent;

public class TradingPlayer {
    
    private Player player;
    private List<Player> customers;
    private OfferList offers;
    private boolean currentlyTrading = false;
    
    public TradingPlayer(Player player, List<Player> customers, OfferList offers) {
        this.player = player;
        this.customers = customers;
        this.offers = offers;
    }

    public TradingPlayer(Player player) {
        this(player, new ArrayList<Player>(), new OfferList());
    }

    public List<Player> getCustomers() {
        return customers;
    }

    public OfferList getOffers() {
        offers.update(player.getInventory());
        return offers;
    }

    public void openWindow(Player customer) {
        customers.add(customer);
        
        CraftPlayer ccustomer = (CraftPlayer) customer;
        ccustomer.getHandle().openTrade(new VirtualMerchant(this, customer), player.getName());
    }

    public boolean isCurrentlyTrading() {
        return currentlyTrading;
    }

    public void setCurrentlyTrading(boolean currentlyTrading) {
        this.currentlyTrading = currentlyTrading;
    }

    public Player getPlayer() {
        return player;
    }

    public void startTrading() {
        currentlyTrading = true;
        Bukkit.getServer().getPluginManager().callEvent(new TradingStartEvent(this));
    }

    public void stopTrading() {
        currentlyTrading = false;
        Bukkit.getServer().getPluginManager().callEvent(new TradingStopEvent(this));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((player == null) ? 0 : player.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof TradingPlayer)) {
            return false;
        }
        TradingPlayer other = (TradingPlayer) obj;
        if (player == null) {
            if (other.player != null) {
                return false;
            }
        } else if (!player.equals(other.player)) {
            return false;
        }
        return true;
    }

}
