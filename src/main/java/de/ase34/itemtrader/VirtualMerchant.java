package de.ase34.itemtrader;

import net.minecraft.server.v1_7_R1.EntityHuman;
import net.minecraft.server.v1_7_R1.IMerchant;
import net.minecraft.server.v1_7_R1.ItemStack;
import net.minecraft.server.v1_7_R1.MerchantRecipe;
import net.minecraft.server.v1_7_R1.MerchantRecipeList;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.ase34.itemtrader.event.OfferTradedEvent;

public class VirtualMerchant implements IMerchant {

    private TradingPlayer player;
    private Player customer;

    public VirtualMerchant(TradingPlayer trader, Player customer) {
        this.player = trader;
        this.customer = customer;
    }

    public void useOffer(Offer offer) {
        if (customer == null) {
            return;
        }

        Inventory traderInventory = player.getPlayer().getInventory();

        traderInventory.removeItem(offer.getProduct());
        traderInventory.addItem(offer.getPrice());

        Bukkit.getServer().getPluginManager().callEvent(new OfferTradedEvent(player, customer, offer));
        // TODO log transaction
    }

    // Uses the recipe
    @Override
    @Deprecated
    public void a(MerchantRecipe arg0) {
        Offer offer = Offer.fromMerchantRecipe(arg0);
        useOffer(offer);
    }

    // Sets the customer (unimplemented)
    @Deprecated
    @Override
    public void a_(EntityHuman arg0) {
    }

    // Plays villager sound (unimplemented)
    @Deprecated
    @Override
    public void a_(ItemStack arg0) {
        return;
    }

    // Gets all offers
    @Override
    @Deprecated
    public MerchantRecipeList getOffers(EntityHuman arg0) {
        return player.getOffers().toMerchantRecipeList();
    }

    // Gets the customer
    @Override
    @Deprecated
    public EntityHuman b() {
        if (customer == null) {
            return null;
        }
        return ((CraftPlayer) customer).getHandle();
    }

    public TradingPlayer getPlayer() {
        return player;
    }

    public Player getCustomer() {
        return customer;
    }

}
