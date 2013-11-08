package de.ase34.itemtrader.help;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.help.HelpTopic;
import org.bukkit.help.IndexHelpTopic;

import de.ase34.itemtrader.ItemTraderPlugin;
import de.ase34.itemtrader.util.command.ParentCommand;
import de.ase34.itemtrader.util.command.SubCommand;

public class ItemTraderHelpTopic extends IndexHelpTopic {

    public ItemTraderHelpTopic(ItemTraderPlugin plugin) {
        super("itemtrader", plugin.getLanguageStrings().get("help-short-text"), (String) null,
                new ArrayList<HelpTopic>(), plugin.getLanguageStrings().get("help-preamble"));

        List<HelpTopic> topics = new ArrayList<HelpTopic>();
        ParentCommand parent = plugin.getParentCommand();
        for (SubCommand cmd : parent.getCommands()) {
            topics.add(plugin.getServer().getHelpMap().getHelpTopic("/" + parent.getLabel() + " " + cmd.getLabel()));
        }

        ItemCodeHelpTopic itemcodehelp = new ItemCodeHelpTopic(plugin);
        plugin.getServer().getHelpMap().addTopic(itemcodehelp);

        topics.add(itemcodehelp);
        setTopicsCollection(topics);
    }

}
