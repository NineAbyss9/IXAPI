
package com.github.NineAbyss9.ix_api.common.item;

import com.github.NineAbyss9.ix_api.api.mobs.IFlagMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class FlagSelector
extends Item
{
    public FlagSelector() {
        super(new Properties().stacksTo(1));
    }

    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity)
    {
        if (entity instanceof IFlagMob mob)
        {
            mob.nextFlag();
            return true;
        }
        return super.onLeftClickEntity(stack, player, entity);
    }
}
