package de.ase34.itemtrader;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R1.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

import de.ase34.itemtrader.command.ItemTraderMainCommand;
import de.ase34.itemtrader.help.ItemTraderHelpTopic;
import de.ase34.itemtrader.listener.InventoryListener;
import de.ase34.itemtrader.listener.ItemTraderListener;
import de.ase34.itemtrader.listener.NameTagListener;
import de.ase34.itemtrader.listener.PlayerListener;
import de.ase34.itemtrader.util.LanguageStrings;
import de.ase34.itemtrader.util.command.ParentCommand;

public class ItemTraderPlugin extends JavaPlugin {

    private TradingPlayersManager manager;
    private LanguageStrings language;
    private ParentCommand command;

    @Override
    public void onDisable() {
        getLogger().info(getDescription().getFullName() + " disabled!");
    }

    @Override
    public void onEnable() {
        manager = new TradingPlayersManager();

        getDataFolder().mkdir();
        saveDefaultConfig();

        language = new LanguageStrings(this);

        command = new ItemTraderMainCommand(this);
        ((CraftServer) getServer()).getCommandMap().register("itemtrader", command);
        getServer().getHelpMap().addTopic(new ItemTraderHelpTopic(this));

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(this), this);
        getServer().getPluginManager().registerEvents(new ItemTraderListener(this), this);
        if (getServer().getPluginManager().getPlugin("TagAPI") != null)
            getServer().getPluginManager().registerEvents(new NameTagListener(this), this);

        ItemTraderPluginMetricsSubmitter.submit(this);
        
        getLogger().info(
                getDescription().getFullName() + " by " + StringUtils.join(getDescription().getAuthors(), ", ")
                        + " enabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        return true;
    }

    public TradingPlayersManager getTrandingPlayersManager() {
        return manager;
    }

    public LanguageStrings getLanguageStrings() {
        return language;
    }

    public ParentCommand getParentCommand() {
        return command;
    }

}
