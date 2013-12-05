package de.ase34.itemtrader;

import java.util.ArrayList;
import java.util.Collection;

import net.minecraft.server.v1_7_R1.MerchantRecipeList;

import org.bukkit.inventory.Inventory;

import de.ase34.itemtrader.util.InventoryUtils;

@SuppressWarnings("serial")
public class OfferList extends ArrayList<Offer> {

    public OfferList() {
        super();
    }

    public OfferList(Collection<? extends Offer> c) {
        super(c);
    }

    public OfferList(int initialCapacity) {
        super(initialCapacity);
    }

    @SuppressWarnings("unchecked")
    public MerchantRecipeList toMerchantRecipeList() {
        MerchantRecipeList list = new MerchantRecipeList();
        for (Offer offer : this) {
            list.add(offer.toMerchantRecipe());
        }

        return list;
    }

    public void update(Inventory inventory) {
        for (Offer offer : this) {
            if (!inventory.containsAtLeast(offer.getProduct(), offer.getProduct().getAmount())) {
                offer.setDisabled(true);
            } else if (InventoryUtils.freeSpaceForItem(inventory, offer.getPrice()) <= 0) {
                offer.setDisabled(true);
            } else {
                offer.setDisabled(false);
            }
        }
    }

    public int findIndex(Offer offer) {
        int index = 0;
        for (Offer o : this) {
            if (o.equals(offer)) {
                return index;
            }
            index++;
        }
        return -1;
    }
}
