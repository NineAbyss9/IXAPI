
package com.github.NineAbyss9.ix_api.api.mobs;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public interface IProjectile {
    void onHit(HitResult pResult);

    void onHitEntity(EntityHitResult pResult);

    void onHitBlock(BlockHitResult pResult);

    default boolean canHitEntity(Entity pEntity) {
        return true;
    }
}
