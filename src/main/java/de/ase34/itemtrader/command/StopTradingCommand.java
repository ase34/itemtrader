package de.ase34.itemtrader.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.ase34.itemtrader.ItemTraderPlugin;
import de.ase34.itemtrader.util.command.SubCommand;

public class StopTradingCommand extends SubCommand {

    private ItemTraderPlugin plugin;

    protected StopTradingCommand(ItemTraderPlugin plugin) {
        super("stop", plugin.getLanguageStrings().get("stop-description"), "");
        this.setPermission("itemtrader.stoptrading");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            plugin.getLanguageStrings().send(sender, "no-console");
            return true;
        }

        Player player = (Player) sender;
        plugin.getTrandingPlayersManager().stopTrading(player);

        plugin.getLanguageStrings().send(sender, "stop-trading");
        return true;
    }

}
