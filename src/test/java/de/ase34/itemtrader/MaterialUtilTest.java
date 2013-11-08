package de.ase34.itemtrader;

import org.junit.Assert;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.support.AbstractTestingBase;
import org.junit.Test;

import de.ase34.itemtrader.util.ItemUtils;

public class MaterialUtilTest extends AbstractTestingBase {

    public static final String string1 = "10x35:4";
    public static final ItemStack stack1 = new ItemStack(35, 10, (short) 4);
    public static final String result = "10x Yellow Wool (35:4)";

    public static final String string2 = "2";
    public static final ItemStack stack2 = new ItemStack(2);

    public static final String string3 = "5xlog:2";
    public static final ItemStack stack3 = new ItemStack(Material.LOG, 5, (short) 2);

    @Test
    public void testMaterialUtil() {
        Assert.assertTrue(ItemUtils.getItem(null, string1).equals(stack1));
        Assert.assertTrue(ItemUtils.getItem(null, string2).equals(stack2));
        Assert.assertTrue(ItemUtils.itemToString(stack1).equals(result));
        Assert.assertTrue(ItemUtils.getItem(null, string3).equals(stack3));
    }

}
