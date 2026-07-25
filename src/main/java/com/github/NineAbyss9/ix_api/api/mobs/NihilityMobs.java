
package com.github.NineAbyss9.ix_api.api.mobs;

import com.github.NineAbyss9.ix_api.IXApi;
import com.github.NineAbyss9.ix_api.util.ResourceLocations;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.concurrent.ThreadLocalRandom;

public abstract class NihilityMobs
extends OwnableMob
implements Nihilistic {
    protected NihilityMobs(EntityType<? extends NihilityMobs> e, Level l) {
        super(e, l);
    }

    public boolean removeWhenFarAway(double d) {
        return d > 50 && this.getOwner() == null;
    }

    public boolean canAttack(LivingEntity pTarget) {
        if (pTarget instanceof Nihilistic) {
            return false;
        }
        return super.canAttack(pTarget);
    }

    protected void dropCustomDeathLoot(DamageSource p_21385_, int p_21386_, boolean p_21387_) {
        if (IXApi.isNoIXApiLoaded() && ThreadLocalRandom.current().nextFloat() < 0.5F) {
            this.spawnAtLocation(ForgeRegistries.ITEMS.getValue(ResourceLocations.fromNamespaceAndPath
                    (IXApi.NOIXMODAPI, "nihilistic_ash")));
        }
        super.dropCustomDeathLoot(p_21385_, p_21386_, p_21387_);
    }

    public boolean addEffect(MobEffectInstance p_147208_, @Nullable Entity p_147209_) {
        return false;
    }

    public boolean fireImmune() {
        return true;
    }

    public MobType getMobType() {
        return ApiMobType.NIHILISTIC;
    }
}
