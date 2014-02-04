package de.ase34.itemtrader.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.ase34.itemtrader.ItemTraderPlugin;
import de.ase34.itemtrader.Offer;
import de.ase34.itemtrader.util.ItemUtils;
import de.ase34.itemtrader.util.NumberUtils;
import de.ase34.itemtrader.util.command.SubCommand;

public class OfferAddCommand extends SubCommand {

    private ItemTraderPlugin plugin;

    protected OfferAddCommand(ItemTraderPlugin plugin) {
        super("addoffer", plugin.getLanguageStrings().get("addoffer-description"),
                "<price> <product> [simple|notsimple]");
        this.getAliases().add("addo");
        this.setPermission("itemtrader.addoffer");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!NumberUtils.isInRange(2, args.length, 3)) {
            return false;
        }

        if (!(sender instanceof Player)) {
            plugin.getLanguageStrings().send(sender, "no-console");
            return true;
        }

        ItemStack price = ItemUtils.getItem((Player) sender, args[0]);
        ItemStack product = ItemUtils.getItem((Player) sender, args[1]);

        if (price == null) {
            plugin.getLanguageStrings().send(sender, "wrong-format", args[0]);
            return true;
        }
        if (product == null) {
            plugin.getLanguageStrings().send(sender, "wrong-format", args[1]);
            return true;
        }

        Offer offer = new Offer(product, price);
        boolean simplify = plugin.getConfig().getBoolean("simplify");
        if (args.length == 3 && args[2].matches("(simple)|(notsimple)")) {
            simplify = args[2].equals("simple");
        }
        if (simplify)
            offer.simplify();

        plugin.getTrandingPlayersManager().getTradingPlayer((Player) sender).getOffers().add(offer);

        plugin.getLanguageStrings().send(sender, "offer-added", ItemUtils.itemToString(price),
                ItemUtils.itemToString(product));
        return true;
    }

}
