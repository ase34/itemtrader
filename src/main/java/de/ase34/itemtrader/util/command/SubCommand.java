package de.ase34.itemtrader.util.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;

public abstract class SubCommand extends Command {

    public SubCommand(String name, String description, String usageMessage, List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    public SubCommand(String name, String description, String usageMessage) {
        super(name, description, usageMessage, new ArrayList<String>());
    }

    public SubCommand(String name) {
        super(name);
    }

    @Override
    public boolean isRegistered() {
        return true;
    }

}
