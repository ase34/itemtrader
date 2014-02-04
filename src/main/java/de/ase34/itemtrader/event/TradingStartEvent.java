package de.ase34.itemtrader.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import de.ase34.itemtrader.TradingPlayer;

public class TradingStartEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private TradingPlayer trader;
    
    public static HandlerList getHandlerList() {
        return handlers;
    }
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public TradingStartEvent(TradingPlayer trader) {
        this.trader = trader;
    }

    public TradingPlayer getTrader() {
        return trader;
    }
    
}