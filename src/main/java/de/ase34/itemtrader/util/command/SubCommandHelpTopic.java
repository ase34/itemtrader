package de.ase34.itemtrader.util.command;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.help.GenericCommandHelpTopic;

public class SubCommandHelpTopic extends GenericCommandHelpTopic {

    public SubCommandHelpTopic(ParentCommand parent, Command child) {
        super(child);
        name = "/" + parent.getLabel() + " " + child.getLabel();

        StringBuffer sb = new StringBuffer();

        sb.append(ChatColor.GOLD);
        sb.append("Description: ");
        sb.append(ChatColor.WHITE);
        sb.append(command.getDescription());

        sb.append("\n");

        sb.append(ChatColor.GOLD);
        sb.append("Usage: ");
        sb.append(ChatColor.WHITE);
        sb.append(name + " " + command.getUsage());

        if (command.getAliases().size() > 0) {
            sb.append("\n");
            sb.append(ChatColor.GOLD);
            sb.append("Aliases: ");
            sb.append(ChatColor.WHITE);
            sb.append(ChatColor.WHITE + StringUtils.join(command.getAliases(), ", "));
        }
        fullText = sb.toString();
    }

}
