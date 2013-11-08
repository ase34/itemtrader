package de.ase34.itemtrader.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class LanguageStrings {

    public static final char COLOR_CHAR = '&';

    private Plugin plugin;
    private Configuration language;

    public LanguageStrings(Plugin plugin) {
        this.plugin = plugin;
        loadLanguageStrings();
    }

    public String get(String key) {
        if (!language.contains(key)) {
            return key;
        }

        return ChatColor.translateAlternateColorCodes(COLOR_CHAR, language.getString(key));
    }

    public void send(CommandSender sender, String key) {
        sender.sendMessage(get(key));
    }

    private void loadLanguageStrings() {
        File languageFile = new File(plugin.getDataFolder(), "language.yml");
        InputStream resource = plugin.getResource("language.yml");

        if (!languageFile.exists()) {
            try {
                IOUtils.copy(resource, new FileOutputStream(languageFile));
            } catch (Exception e) {
                plugin.getLogger()
                        .warning("Default language file could not be written, unexpected results may appear!");
                e.printStackTrace();
            }
        }

        language = YamlConfiguration.loadConfiguration(languageFile);
        language.setDefaults(YamlConfiguration.loadConfiguration(resource));
    }

    public void send(CommandSender sender, String key, Object... args) {
        sender.sendMessage(String.format(get(key), args));
    }
}
