
package com.github.NineAbyss9.ix_api.api.crafting;

import net.minecraft.world.inventory.DataSlot;

public class SubDataSlot
extends DataSlot {
    public final int max;
    public SubDataSlot(int pMax) {
        this.max = pMax;
    }

    private int value;

    public int get() {
        return this.value;
    }

    public void set(int p_39429_) {
        this.value = Math.min(max, p_39429_);
    }
}
