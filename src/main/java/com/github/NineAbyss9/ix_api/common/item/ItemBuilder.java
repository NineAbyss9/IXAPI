
package com.github.NineAbyss9.ix_api.common.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

public class ItemBuilder {
    public static Set<Supplier<? extends Item>> SPAWN_EGGS
            = new LinkedHashSet<>();
    public static Set<Supplier<? extends Item>> BLOCKS
            = new LinkedHashSet<>();
    public static Set<Supplier<? extends Item>> UTILS
            = new LinkedHashSet<>();
    public Supplier<Item> item;
    public boolean mainCreative;
    public boolean spawnEggs;
    public boolean blocks;
    public boolean utils;
    public boolean food;
    public String en;
    public String zh;
    public ItemBuilder() {
    }

    public ItemBuilder item(Supplier<Item> blockSupplier) {
        this.item = blockSupplier;
        return this;
    }

    public ItemBuilder addToCreativeTab() {
        this.mainCreative = true;
        return this;
    }

    public ItemBuilder spawnEggs() {
        this.spawnEggs = true;
        return this;
    }

    public ItemBuilder food() {
        this.food = true;
        return this;
    }

    public ItemBuilder blocks() {
        this.blocks = true;
        return this;
    }

    public ItemBuilder utils() {
        this.utils = true;
        return this;
    }

    public ItemBuilder name(String name)
    {
        this.en = name;
        return this;
    }

    public ItemBuilder translate(String en, String zh) {
        this.en = en;
        this.zh = zh;
        return this;
    }

    public Function<ItemBuilder, RegistryObject<? extends Item>> func;

    public ItemBuilder how(Function<ItemBuilder, RegistryObject<? extends Item>> action) {
        this.func = action;
        return this;
    }

    public RegistryObject<? extends Item> build() {
        RegistryObject<? extends Item> obj = func.apply(this);
        if (spawnEggs) SPAWN_EGGS.add(obj);
        if (blocks) BLOCKS.add(obj);
        if (utils) UTILS.add(obj);
        return obj;
    }
}
