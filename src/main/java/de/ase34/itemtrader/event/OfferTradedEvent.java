package de.ase34.itemtrader.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import de.ase34.itemtrader.Offer;
import de.ase34.itemtrader.TradingPlayer;

public class OfferTradedEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private TradingPlayer trader;
    private Player customer;
    private Offer offer;
    
    public static HandlerList getHandlerList() {
        return handlers;
    }
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public OfferTradedEvent(TradingPlayer trader, Player customer, Offer offer) {
        this.trader = trader;
        this.customer = customer;
        this.offer = offer;
    }

    public TradingPlayer getTrader() {
        return trader;
    }

    public Player getCustomer() {
        return customer;
    }

    public Offer getOffer() {
        return offer;
    }

}
