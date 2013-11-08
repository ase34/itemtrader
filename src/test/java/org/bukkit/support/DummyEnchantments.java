package org.bukkit.support;

import net.minecraft.server.v1_6_R3.Enchantment;

public class DummyEnchantments {
    static {
        Enchantment.byId.getClass();
        org.bukkit.enchantments.Enchantment.stopAcceptingRegistrations();
    }

    public static void setup() {
    }
}
