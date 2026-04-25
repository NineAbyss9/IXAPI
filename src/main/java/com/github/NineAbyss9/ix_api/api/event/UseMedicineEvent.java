
package com.github.NineAbyss9.ix_api.api.event;

import com.github.NineAbyss9.ix_api.api.item.IMedicine;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Event.HasResult
@Cancelable
public class UseMedicineEvent
extends LivingEvent {
    private IMedicine medicine;
    public UseMedicineEvent(LivingEntity entity, IMedicine medicineIn) {
        super(entity);
        this.medicine = medicineIn;
    }

    public IMedicine getMedicine() {
        return medicine;
    }

    public void setMedicine(IMedicine medicineIn) {
        this.medicine = medicineIn;
    }
}
