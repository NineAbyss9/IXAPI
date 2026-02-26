
package com.github.NineAbyss9.ix_api.api.mobs;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public interface ApiRangedAttackMob
extends RangedAttackMob{
    AbstractArrow getArrow(ItemStack stack, float fall);

    private LivingEntity rangedSelfSelector() {
        return (LivingEntity)this;
    }

    default boolean isHoldingBow() {
        return this.rangedSelfSelector().isHolding(Items.BOW);
    }

    default void performRangedAttack(LivingEntity pTarget, float pFall) {
        ItemStack itemstack = this.rangedSelfSelector().getProjectile(this.rangedSelfSelector()
                .getItemInHand(ProjectileUtil.getWeaponHoldingHand(
                this.rangedSelfSelector(), (item) -> item instanceof BowItem)));
        AbstractArrow abstractarrow = this.getArrow(itemstack, pFall);
        if (itemstack.getItem() instanceof BowItem bowItem) {
            abstractarrow = bowItem.customArrow(abstractarrow);
        }
        double d0 = pTarget.getX() - this.rangedSelfSelector().getX();
        double d1 = pTarget.getY(0.3333333333333333) - abstractarrow.getY();
        double d2 = pTarget.getZ() - this.rangedSelfSelector().getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        abstractarrow.shoot(d0, d1 + d3 * 0.20000000298023224, d2, 1.6F, 14
                - this.rangedSelfSelector().level().getDifficulty().getId() * 4);
        this.rangedSelfSelector().playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F /
                (this.rangedSelfSelector().getRandom().nextFloat() * 0.4F + 0.8F));
        this.rangedSelfSelector().level().addFreshEntity(abstractarrow);
    }
}
