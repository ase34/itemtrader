package de.ase34.itemtrader;

import net.minecraft.server.v1_6_R3.MerchantRecipe;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.bukkit.craftbukkit.v1_6_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import de.ase34.itemtrader.util.NumberUtils;

public class Offer {

    private ItemStack product;
    private ItemStack price;

    private boolean disabled;

    public Offer(ItemStack product, ItemStack price) {
        this.product = product;
        this.price = price;
        this.disabled = false;
    }

    public ItemStack getProduct() {
        return product;
    }

    public void setProduct(ItemStack product) {
        this.product = product;
    }

    public ItemStack getPrice() {
        return price;
    }

    public void setPrice(ItemStack price) {
        this.price = price;
    }

    public MerchantRecipe toMerchantRecipe() {
        MerchantRecipe recipe = new MerchantRecipe(CraftItemStack.asNMSCopy(price), CraftItemStack.asNMSCopy(product));
        if (disabled) {
            recipe.a(-7);
        }
        return recipe;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        if (obj.getClass() != getClass()) {
            return false;
        }

        Offer offer = (Offer) obj;
        return new EqualsBuilder().append(product, offer.product).append(price, offer.price)
                .append(disabled, offer.disabled).isEquals();
    }

    public static Offer fromMerchantRecipe(MerchantRecipe recipe) {
        return new Offer(CraftItemStack.asBukkitCopy(recipe.getBuyItem3()), CraftItemStack.asBukkitCopy(recipe
                .getBuyItem1()));
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void simplify() {
        int productAmount = product.getAmount();
        int priceAmount = price.getAmount();

        int gcd = NumberUtils.greatestCommonDivisor(productAmount, priceAmount);

        product.setAmount(productAmount / gcd);
        price.setAmount(priceAmount / gcd);
    }

}
