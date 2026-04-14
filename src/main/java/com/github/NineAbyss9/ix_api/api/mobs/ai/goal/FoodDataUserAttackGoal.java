
package com.github.NineAbyss9.ix_api.api.mobs.ai.goal;

import com.github.NineAbyss9.ix_api.api.mobs.FoodDataUser;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class FoodDataUserAttackGoal<E extends Mob & FoodDataUser, T extends LivingEntity>
extends NearestAttackableTargetGoal<T>
implements org.NineAbyss9.util.IXUtilUser {
    public FoodDataUserAttackGoal(E pMob, Class<T> pTarget, boolean pMustSee) {
        super(pMob, pTarget, pMustSee);
    }

    public FoodDataUserAttackGoal(E p_199891_, Class<T> p_199892_, boolean p_199893_, Predicate<LivingEntity> p_199894_) {
        super(p_199891_, p_199892_, p_199893_, p_199894_);
    }

    public FoodDataUserAttackGoal(E p_26064_, Class<T> p_26065_, boolean p_26066_, boolean p_26067_) {
        super(p_26064_, p_26065_, p_26066_, p_26067_);
    }

    public FoodDataUserAttackGoal(E p_26053_, Class<T> p_26054_, int p_26055_, boolean p_26056_, boolean p_26057_, @Nullable Predicate<LivingEntity> p_26058_) {
        super(p_26053_, p_26054_, p_26055_, p_26056_, p_26057_, p_26058_);
    }

    public E getMob() {
        return org.NineAbyss9.util.IXUtil.c.convert(mob);
    }

    public boolean canUse() {
        return this.getMob().foodData().needsFood() && super.canUse();
    }
}
