package de.ase34.itemtrader;

import java.util.Arrays;

import org.junit.Assert;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_6_R3.inventory.CraftInventoryCustom;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.support.AbstractTestingBase;
import org.junit.Test;

public class OfferListTest extends AbstractTestingBase {

    @Test
    public void testOfferList() {
        OfferList offerList = new OfferList();

        Offer[] offers = new Offer[] {
                new Offer(new ItemStack(Material.COBBLESTONE, 10), new ItemStack(Material.IRON_INGOT)),
                new Offer(new ItemStack(Material.WOOD, 64), new ItemStack(Material.GOLD_INGOT)),
                new Offer(new ItemStack(Material.WOOL, 64), new ItemStack(Material.DIAMOND)) };

        offerList.addAll(Arrays.asList(offers));

        Inventory inventory = new CraftInventoryCustom(null, InventoryType.PLAYER);

        for (int i = 0; i < 33; i++) {
            inventory.addItem(new ItemStack(Material.COBBLESTONE, 64));
        }
        inventory.addItem(new ItemStack(Material.WOOL, 64));
        inventory.addItem(new ItemStack(Material.GOLD_INGOT, 10));
        inventory.addItem(new ItemStack(Material.IRON_INGOT, 10));

        offerList.update(inventory);

        for (Offer offer : offerList) {
            if (offer.equals(offers[0])) {
                Assert.assertTrue(!offer.isDisabled());
            } else if (offer.equals(offers[1])) {
                Assert.assertTrue(offer.isDisabled());
            } else if (offer.equals(offers[2])) {
                Assert.assertTrue(offer.isDisabled());
            }
        }
    }

}
