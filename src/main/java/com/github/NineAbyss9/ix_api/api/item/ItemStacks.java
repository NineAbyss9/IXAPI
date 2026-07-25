
package com.github.NineAbyss9.ix_api.api.item;

import com.github.NineAbyss9.ix_api.util.ApiRandom;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.function.Supplier;

public class ItemStacks {
    private ItemStacks() {
    }

    public static ItemStack ofRanged(ItemLike like, int to) {
        return new ItemStack(like, ApiRandom.nextInt(to));
    }

    /**Creates an empty {@linkplain ItemStack}*/
    public static ItemStack of() {
        return ItemStack.EMPTY;
    }

    public static ItemStack of(Item item) {
        return new ItemStack(item);
    }

    public static ItemStack of(Supplier<Item> item) {
        return of(item.get());
    }

    public static ItemStack of(Item item, int count) {
        return new ItemStack(item, count);
    }

    public static ItemStack of(Supplier<Item> item, int count) {
        return of(item.get(), count);
    }
}
