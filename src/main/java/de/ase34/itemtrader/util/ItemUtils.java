package de.ase34.itemtrader.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_6_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("deprecation")
public class ItemUtils {

    public static final Pattern CODE_PATTERN = Pattern.compile("(\\d+x)?((#(current|\\d))|\\d+|[a-zA-z_-]+)(:(\\d+))?");
    public static final Pattern ID_PATTERN = Pattern.compile("^(\\d+x)?(\\d+)(:.*)?$");
    public static final Pattern NAME_PATTERN = Pattern.compile("^(\\d+x)?([a-zA-z_-]+)(:.*)?$");
    public static final Pattern SLOT_PATTERN = Pattern.compile("^(\\d+x)?#([^:]+)");
    public static final Pattern DAMAGE_PATTERN = Pattern.compile(":(\\d+)$");
    public static final Pattern AMOUNT_PATTERN = Pattern.compile("^(\\d+)x");

    public static ItemStack getItem(Player player, String str) {
        if (!matchesPattern(str))
            return null;

        if (usesSlot(str)) {
            ItemStack slot = getSlot(player, str).clone();
            slot.setDurability(getDamage(str, (short) slot.getDurability()));
            slot.setAmount(getAmount(str, slot.getMaxStackSize()));
            return slot;
        } else {
            int id = getId(str, player);
            if (id == -1)
                return null;
            short damage = getDamage(str, (short) 0);
            int amount = 64;
            Material material = Material.getMaterial(id);
            amount = getAmount(str, material.getMaxStackSize());
            return new ItemStack(id, amount, damage);
        }
    }

    public static boolean matchesPattern(String str) {
        return CODE_PATTERN.matcher(str).find();
    }

    public static int getId(String str, Player player) {
        Matcher m;

        m = ID_PATTERN.matcher(str);
        if (m.find()) {
            return Integer.parseInt(m.group(2));
        }

        m = NAME_PATTERN.matcher(str);
        if (m.find()) {
            Material material = getMaterial(m.group(2));
            if (material != null)
                return material.getId();
        }

        return -1;
    }

    public static Material getMaterial(String name) {
        String formatted = name.replaceAll("-|_", "").toUpperCase();

        Material material = Material.matchMaterial(name);
        if (material != null) {
            return material;
        }

        for (Material mat : Material.values()) {
            String matName = mat.name().replace("_", "");
            if (matName.equals(formatted))
                return mat;
        }

        return null;
    }

    public static ItemStack getSlot(Player player, String str) {
        Matcher m = SLOT_PATTERN.matcher(str);
        if (m.find()) {
            String slot = m.group(2);
            if (slot.equalsIgnoreCase("current"))
                return player.getInventory().getItemInHand();
            else
                return player.getInventory().getItem(Integer.parseInt(slot) - 1);
        } else {
            return null;
        }
    }

    public static boolean usesSlot(String str) {
        return SLOT_PATTERN.matcher(str).find();
    }

    public static short getDamage(String str, short def) {
        Matcher m = DAMAGE_PATTERN.matcher(str);
        if (m.find()) {
            return Short.parseShort(m.group(1));
        } else {
            return def;
        }
    }

    public static int getAmount(String str, int max) {
        Matcher m = AMOUNT_PATTERN.matcher(str);
        if (m.find())
            return Math.min(max, Integer.parseInt(m.group(1)));
        else
            return 1;
    }

    public static String itemToString(ItemStack stack) {
        net.minecraft.server.v1_6_R3.ItemStack nmsstack = CraftItemStack.asNMSCopy(stack);
        return String.format("%1$dx %4$s (%2$d:%3$d)", stack.getAmount(), stack.getTypeId(), stack.getDurability(),
                nmsstack.getName());
    }

}
