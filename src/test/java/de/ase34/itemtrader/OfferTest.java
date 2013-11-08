package de.ase34.itemtrader;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.Assert;
import org.junit.Test;

public class OfferTest {

    @Test
    public void testOfferSimplification() {
        Offer offer = new Offer(new ItemStack(Material.IRON_INGOT, 3), new ItemStack(Material.LOG, 18));
        offer.simplify();

        Assert.assertTrue(offer.getProduct().getAmount() == 1);
        Assert.assertTrue(offer.getPrice().getAmount() == 6);
    }

}
