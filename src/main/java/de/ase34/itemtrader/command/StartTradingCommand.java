package de.ase34.itemtrader.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.ase34.itemtrader.ItemTraderPlugin;
import de.ase34.itemtrader.util.command.SubCommand;

public class StartTradingCommand extends SubCommand {

    private ItemTraderPlugin plugin;

    protected StartTradingCommand(ItemTraderPlugin plugin) {
        super("start", plugin.getLanguageStrings().get("start-description"), "");
        this.setPermission("itemtrader.starttrading");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            plugin.getLanguageStrings().send(sender, "no-console");
            return true;
        }

        Player player = (Player) sender;
        if (plugin.getTrandingPlayersManager().startTrading(player, plugin.getOfferListManager().getOfferList(player))) {
            plugin.getLanguageStrings().send(sender, "start-trading");
        } else {
            plugin.getLanguageStrings().send(sender, "already-trading");
        }

        return true;
    }

}
