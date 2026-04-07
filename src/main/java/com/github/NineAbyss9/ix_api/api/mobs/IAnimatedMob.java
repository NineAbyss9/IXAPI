
package com.github.NineAbyss9.ix_api.api.mobs;

import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;

import java.util.List;

public interface IAnimatedMob
{
    List<AnimationState> getAllAnimations();

    default AnimationState getAnimation(int pAni) {
        return this.getAllAnimations().get(pAni);
    }

    default void stopAllAnimations() {
        for (AnimationState state : this.getAllAnimations()) {
            state.stop();
        }
    }

    default void startAfterStop(AnimationState state)
    {
        this.stopAllAnimations();
        state.startIfStopped(((Entity)this).tickCount);
    }
}
