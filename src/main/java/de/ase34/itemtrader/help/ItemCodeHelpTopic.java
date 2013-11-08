package de.ase34.itemtrader.help;

import org.bukkit.command.CommandSender;
import org.bukkit.help.HelpTopic;

import de.ase34.itemtrader.ItemTraderPlugin;

public class ItemCodeHelpTopic extends HelpTopic {

    public ItemCodeHelpTopic(ItemTraderPlugin plugin) {
        name = "itemtrader itemcodes";
        shortText = plugin.getLanguageStrings().get("itemcodes-help-short-text");
        fullText = plugin.getLanguageStrings().get("itemcodes-help-full-text");
    }

    @Override
    public boolean canSee(CommandSender player) {
        return true;
    }

}
