package de.ase34.itemtrader.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.ase34.itemtrader.ItemTraderPlugin;
import de.ase34.itemtrader.Offer;
import de.ase34.itemtrader.OfferList;
import de.ase34.itemtrader.util.ItemUtils;
import de.ase34.itemtrader.util.command.SubCommand;

public class OfferListCommand extends SubCommand {

    private ItemTraderPlugin plugin;

    protected OfferListCommand(ItemTraderPlugin plugin) {
        super("list", plugin.getLanguageStrings().get("list-description"), "");
        this.setPermission("itemtrader.listoffers");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            plugin.getLanguageStrings().send(sender, "no-console");
            return true;
        }

        OfferList list = plugin.getOfferListManager().getOfferList((Player) sender);

        plugin.getLanguageStrings().send(sender, "offer-list-header", list.size());
        for (int i = 0; i < list.size(); i++) {
            Offer offer = list.get(i);
            ItemStack price = offer.getPrice();
            ItemStack product = offer.getProduct();
            plugin.getLanguageStrings().send(sender, "offer-list-element", i + 1, ItemUtils.itemToString(product),
                    ItemUtils.itemToString(price));
        }

        return true;
    }

}
