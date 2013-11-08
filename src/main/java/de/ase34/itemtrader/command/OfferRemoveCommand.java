package de.ase34.itemtrader.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.ase34.itemtrader.ItemTraderPlugin;
import de.ase34.itemtrader.OfferList;
import de.ase34.itemtrader.util.NumberUtils;
import de.ase34.itemtrader.util.command.SubCommand;

public class OfferRemoveCommand extends SubCommand {

    private ItemTraderPlugin plugin;

    protected OfferRemoveCommand(ItemTraderPlugin plugin) {
        super("removeoffer", plugin.getLanguageStrings().get("removeoffer-description"), "<number>|all");
        this.getAliases().add("rmo");
        this.setPermission("itemtrader.removeoffer");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!NumberUtils.isInRange(1, args.length, 1)) {
            return false;
        }

        if (!(sender instanceof Player)) {
            plugin.getLanguageStrings().send(sender, "no-console");
            return true;
        }

        OfferList list = plugin.getOfferListManager().getOfferList((Player) sender);
        if (args[0].equalsIgnoreCase("all")) {
            list.clear();
            plugin.getLanguageStrings().send(sender, "cleared-offers");
            return true;
        } else {
            int index = Integer.parseInt(args[0]) - 1;
            if (index >= list.size()) {
                plugin.getLanguageStrings().send(sender, "not-in-range", index + 1, list.size());
                return true;
            }
            list.remove(Integer.parseInt(args[0]) - 1);

            plugin.getLanguageStrings().send(sender, "removed-offer", index + 1);
            return true;
        }
    }

}
