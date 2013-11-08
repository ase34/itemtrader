package de.ase34.itemtrader.command;

import java.util.ArrayList;

import de.ase34.itemtrader.ItemTraderPlugin;
import de.ase34.itemtrader.util.command.ParentCommand;

public class ItemTraderMainCommand extends ParentCommand {

    public ItemTraderMainCommand(ItemTraderPlugin plugin) {
        super("itemtrader", plugin.getLanguageStrings().get("itemtrader-description"), "/itemtrader",
                new ArrayList<String>());
        setUsagePrefix(plugin.getLanguageStrings().get("usage-prefix"));
        registerCommand(new OfferAddCommand(plugin));
        registerCommand(new OfferRemoveCommand(plugin));
        registerCommand(new OfferListCommand(plugin));
        registerCommand(new StartTradingCommand(plugin));
        registerCommand(new StopTradingCommand(plugin));
    }

}
