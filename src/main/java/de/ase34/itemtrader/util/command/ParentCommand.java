package de.ase34.itemtrader.util.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;

public class ParentCommand extends Command {

    private List<SubCommand> commands;
    private String usagePrefix;

    protected ParentCommand(String name, String description, String usageMessage, List<String> aliases) {
        super(name, description, usageMessage, aliases);
        this.commands = new ArrayList<SubCommand>();
    }

    public void registerCommand(SubCommand command) {
        commands.add(command);
        addHelp(command);
        updateUsage();
    }

    private void updateUsage() {
        StringBuilder builder = new StringBuilder();
        builder.append("/");
        builder.append(this.getLabel());
        builder.append(" ");

        Iterator<SubCommand> iterator = commands.iterator();
        while (iterator.hasNext()) {
            builder.append(iterator.next().getLabel());
            if (iterator.hasNext())
                builder.append("|");
        }

        usageMessage = builder.toString();
    }

    private void addHelp(final Command command) {
        Bukkit.getServer().getHelpMap().addTopic(new SubCommandHelpTopic(this, command));
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(usagePrefix + usageMessage);
            return false;
        }

        String label = args[0];
        String[] newArgs = new String[args.length - 1];
        System.arraycopy(args, 1, newArgs, 0, args.length - 1);

        for (Command cmd : commands) {

            if (cmd.getAliases().contains(label) || cmd.getLabel().equals(label)) {
                if (cmd.testPermission(sender)) {
                    if (!cmd.execute(sender, label, newArgs)) {
                        showUsageOfSubCommand(sender, cmd);
                    }
                }

                return true;
            }
        }

        sender.sendMessage(usagePrefix + usageMessage);
        return false;
    }

    public void showUsageOfSubCommand(CommandSender sender, Command command) {
        sender.sendMessage(usagePrefix + "/" + this.getLabel() + " " + command.getLabel() + " " + command.getUsage());
    }

    public List<SubCommand> getCommands() {
        return commands;
    }

    public String getUsagePrefix() {
        return usagePrefix;
    }

    public void setUsagePrefix(String usagePrefix) {
        this.usagePrefix = usagePrefix;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String cmdAlias, String[] args)
            throws IllegalArgumentException {
        if (args.length == 1) {
            List<String> completions = new ArrayList<String>();
            if (args[0].equals("")) {
                for (Command cmd : getCommands()) {
                    completions.add(cmd.getLabel());
                }
            } else {
                String toComplete = args[0].toLowerCase();
                for (Command cmd : getCommands()) {
                    if (StringUtil.startsWithIgnoreCase(cmd.getLabel(), toComplete)) {
                        completions.add(cmd.getLabel().toLowerCase());
                    }
                    for (String alias : cmd.getAliases()) {
                        if (StringUtil.startsWithIgnoreCase(alias.toLowerCase(), toComplete)) {
                            completions.add(alias.toLowerCase());
                        }
                    }
                }
            }
            return completions;
        }
        return new ArrayList<String>();
    }

}
