package de.ase34.itemtrader.util;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R1.inventory.CraftInventoryCustom;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryUtils {

    public static CraftInventoryCustom copyInventory(Inventory inventory) {
        CraftInventoryCustom copy = new CraftInventoryCustom(inventory.getHolder(), inventory.getSize(),
                inventory.getTitle());
        copy.setMaxStackSize(inventory.getMaxStackSize());
        copy.setContents(inventory.getContents());

        return copy;
    }

    public static int freeSpaceForItem(Inventory inventory, ItemStack stack) {
        int freespace = 0;

        for (ItemStack invstack : inventory) {
            if (invstack == null || invstack.getType() == Material.AIR) {
                freespace += stack.getMaxStackSize();
            } else if (invstack.isSimilar(stack)) {
                freespace += stack.getMaxStackSize() - invstack.getAmount();
            }
        }

        return freespace;
    }

    public static HashMap<Integer, ItemStack> testAdding(Inventory inventory, ItemStack... items) {
        Inventory copy = copyInventory(inventory);
        return copy.addItem(items);
    }

}
