
package com.github.NineAbyss9.ix_api.api.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ContainerItem
extends Item {
    private final Item[] items;
    public ContainerItem(Item... itemsIn) {
        super(new Properties());
        this.items = itemsIn;
    }

    public ContainerItem() {
        this(new Item[0]);
    }

    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        p_41404_.shrink(1);
        if (items.length == 0) return;
        if (p_41406_ instanceof Player player) {
            for (var item : items) {
                player.addItem(item.getDefaultInstance());
            }
        }
    }
}
