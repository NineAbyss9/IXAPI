
package com.github.NineAbyss9.ix_api.api.item;

import com.github.NineAbyss9.ix_api.api.event.UseMedicineEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;

public interface IMedicine {
    default void postMedEvent(LivingEntity pEntity) {
        MinecraftForge.EVENT_BUS.post(new UseMedicineEvent(pEntity, this));
    }
}
