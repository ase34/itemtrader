package de.ase34.itemtrader;

import net.minecraft.server.v1_7_R1.EntityHuman;
import net.minecraft.server.v1_7_R1.IMerchant;
import net.minecraft.server.v1_7_R1.ItemStack;
import net.minecraft.server.v1_7_R1.MerchantRecipe;
import net.minecraft.server.v1_7_R1.MerchantRecipeList;

import org.bukkit.craftbukkit.v1_7_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.ase34.itemtrader.util.ItemUtils;

public class TradingPlayer implements IMerchant {

    private Player player;
    private Player customer;
    private OfferList offers;
    private ItemTraderPlugin plugin;

    public TradingPlayer(ItemTraderPlugin plugin, Player trader, OfferList offers) {
        this.player = trader;
        this.offers = offers;
        this.plugin = plugin;
    }

    public TradingPlayer(ItemTraderPlugin plugin, Player trader) {
        this(plugin, trader, new OfferList());
    }

    public void setCustomer(Player player) {
        this.customer = player;
        updateOffers();
    }

    public void useOffer(Offer offer) {
        if (customer == null) {
            return;
        }
        
        Inventory traderInventory = player.getInventory();

        traderInventory.removeItem(offer.getProduct());
        traderInventory.addItem(offer.getPrice());

        updateOffers();

        plugin.getLanguageStrings().send(player, "items-traded", customer.getName(),
                ItemUtils.itemToString(offer.getProduct()), ItemUtils.itemToString(offer.getPrice()));
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
        updateOffers();
        return offers.toMerchantRecipeList();
    }

    public void updateOffers() {
        offers.update(player.getInventory());
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

    public Player getPlayer() {
        return player;
    }

    public OfferList getOffers() {
        offers.update(player.getInventory());
        return offers;
    }

    public void setOffers(OfferList offers) {
        this.offers = offers;
        offers.update(player.getInventory());
    }

    public Player getCustomer() {
        return customer;
    }

}
