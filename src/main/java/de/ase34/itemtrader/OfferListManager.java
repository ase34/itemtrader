package de.ase34.itemtrader;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class OfferListManager {

    private Map<String, OfferList> offers = new HashMap<String, OfferList>();

    public void addOffer(Player player, Offer offer) {
        String name = player.getName();

        setupOfferList(name);

        offers.get(name).add(offer);
    }

    public void setupOfferList(String name) {
        if (!offers.containsKey(name)) {
            offers.put(name, new OfferList());
        }
    }

    public void removeOffer(Player player, Offer offer) {
        String name = player.getName();
        setupOfferList(name);
        offers.get(name).remove(offer);
    }

    public OfferList getOfferList(Player player) {
        String name = player.getName();
        setupOfferList(name);
        return offers.get(name);
    }

}
