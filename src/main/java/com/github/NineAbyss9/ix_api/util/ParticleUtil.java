
package com.github.NineAbyss9.ix_api.util;

import com.github.NineAbyss9.ix_api.api.annotation.ClientOnly;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.*;
import org.NineAbyss9.annotation.PAMAreNonnullByDefault;
import com.github.NineAbyss9.ix_api.api.annotation.ServerOnly;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.NineAbyss9.math.AbyssMath;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

@PAMAreNonnullByDefault
public record ParticleUtil(Entity entity) {
    private Level level() {
        return this.entity.level();
    }

    @ClientOnly
    public static void sendParticles(Level level, ParticleOptions options,
                                     int count, double x, double y, double z, double xDist, double yDist, double zDist, double maxSpeed)
    {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0;i < count;i++) {
            double d1 = random.nextGaussian() * xDist;
            double d3 = random.nextGaussian() * yDist;
            double d5 = random.nextGaussian() * zDist;
            double d6 = random.nextGaussian() * maxSpeed;
            double d7 = random.nextGaussian() * maxSpeed;
            double d8 = random.nextGaussian() * maxSpeed;
            level.addParticle(options, x + d1,
                    y + d3, z + d5, d6, d7, d8);
        }
    }

    @ClientOnly
    public void addParticle(ParticleOptions pParticleData, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed)
    {
        this.level().addParticle(pParticleData, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);
    }

    @ClientOnly
    public void addParticle(ParticleOptions options, double pXS, double pYS, double pZS) {
        this.addParticle(options, entity.getX(), entity.getY(), entity.getZ(), pXS, pYS, pZS);
    }

    @ClientOnly
    public static void addParticle(Level level, ParticleOptions options, BlockPos pos, double xs, double ys, double zs) {
        level.addParticle(options, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, xs, ys, zs);
    }

    @ClientOnly
    public void randomAddParticle(ParticleOptions options, double xScale, double zScale, double pXS, double pYS, double pZS) {
        this.addParticle(options, this.entity.getRandomX(xScale), this.entity.getRandomY(), this.entity.getRandomZ(zScale),
                pXS, pYS, pZS);
    }

    @ClientOnly
    public void randomAddParticle(ParticleOptions options, double pXS, double pYS, double pZS) {
        this.randomAddParticle(options, 0.8d,0.8d,
                pXS, pYS, pZS);
    }

    @ClientOnly
    public void randomAddParticle(ParticleOptions options) {
        this.randomAddParticle(options, 0, 0, 0);
    }

    @ServerOnly
    public static void addParticleAroundSelf(Entity entity, ParticleOptions options, int count) {
        if (!entity.level().isClientSide) {
            ServerLevel level = (ServerLevel)entity.level();
            double speed = ThreadLocalRandom.current().nextGaussian() * 0.05D;
            level.sendParticles(options, entity.getX(), entity.getRandomY(), entity.getZ(), count,
                    1.5D,  2.0D, 1.5D, speed);
        }
    }

    @ClientOnly
    public static void addFlatParticle(ParticleOptions options, Entity entity, double x, double z)
    {
        double rx = entity.getRandomX(x);
        double y = entity.getRandomY();
        double rz = entity.getRandomZ(z);
        entity.level().addParticle(options, rx, y, rz, 0d, 0d, 0d);
    }

    @ClientOnly
    public static void addRedStoneParticle(Entity entity, double x, double y, double z, double xS, double yS, double zS)
    {
        entity.level().addParticle(DustParticleOptions.REDSTONE, x, y, z, xS, yS, zS);
    }

    @ServerOnly
    public static void sendParticles(ServerLevel level, ParticleOptions particle, Vec3 position, int count, double dx,
                                     double dy, double dz, double speed) {
        level.sendParticles(particle, position.x(), position.y(), position.z(), count, dx, dy, dz, speed);
    }

    public void sendParticles(ParticleOptions options, int count, double dx, double dy, double dz, double speed) {
        sendParticles((ServerLevel)this.entity.level(), options, this.entity.position(), count, dx, dy, dz, speed);
    }

    @ServerOnly
    public static void explode(ServerLevel level, Vec3 position) {
        level.sendParticles(ParticleTypes.EXPLOSION_EMITTER, position.x(), position.y(), position.z(), 1,
                0d, 0d, 0d, 0d);
    }

    @ClientOnly
    public static void addParticle(Level level, ParticleOptions options, Vec3 pos, double dx, double dy, double dz)
    {
        level.addParticle(options, pos.x, pos.y, pos.z, dx, dy, dz);
    }

    @ClientOnly
    public static void addParticle(Level level, ParticleOptions options, Vec3 pos, float[] floats)
    {
        level.addParticle(options, pos.x, pos.y, pos.z, floats[0], floats[1], floats[2]);
    }

    @ServerOnly
    public static void serverAddParticle(ServerLevel serverLevel, ParticleOptions options, Vec3 pos) {
        serverLevel.sendParticles(options, pos.x, pos.y, pos.z,
                1, 0.0D, 0.0D, 0.0D, 0.0D);
    }

    @ServerOnly
    public static void spawnAnim(Entity entity, ParticleOptions options) {
        spawnAnim(entity, options, 20);
    }

    public static void spawnAnim(Entity entity, ParticleOptions options, int count) {
        AABB aabb = entity.getBoundingBox();
        sendParticles((ServerLevel)entity.level(), options, entity.position(), count,
                aabb.getXsize() - 0.2d, aabb.getYsize(), aabb.getZsize() - 0.2d,
                ThreadLocalRandom.current().nextGaussian() * 0.02d);
    }

    @ClientOnly
    public static void spawnAnim(ParticleOptions options, Level level, Entity pos) {
        for (int i = 0;i < 20;i++) {
            level.addParticle(options, pos.getRandomX(1d), pos.getRandomY(), pos.getRandomZ(1d),
                    0d, 0d, 0d);
        }
    }

    @ClientOnly
    public static void spawnAnim(Supplier<ParticleOptions> supplier, Level level, Entity pos) {
        spawnAnim(getFromSupplier(supplier), level, pos);
    }

    @ServerOnly
    public static void spawnAnim(Entity entity) {
        spawnAnim(entity, ParticleTypes.POOF);
    }

    public static void addBlockParticle(Level pLevel, BlockPos pPos, double pX, double pY, double pZ, double xz, double yz,
                                        double zz) {
        pLevel.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, pLevel.getBlockState(pPos)), pX, pY, pZ, xz, yz, zz);
    }

    public static void addBlockParticle(Level pLevel, BlockPos pPos, double pX, double pY, double pZ) {
        addBlockParticle(pLevel, pPos, pX, pY, pZ, AbyssMath.random(0.3D), 0.2D, AbyssMath.random(0.3D));
    }

    public static void addBlockParticle(Level pLevel, BlockPos pPos) {
        addBlockParticle(pLevel, pPos, pPos.getX() + 0.5D, pPos.getY(), pPos.getZ() + 0.5D);
    }

    @ServerOnly
    public static void explodeSmoke(Level pLevel, Vec3 pos, double pSpeed)
    {
        sendParticles((ServerLevel)pLevel, ParticleTypes.LARGE_SMOKE, pos, 30, 0.0D, 0.0D, 0.0D, pSpeed);
    }

    @ServerOnly
    public static void explodeSmoke(Level pLevel, Vec3 pos)
    {
        sendParticles((ServerLevel)pLevel, ParticleTypes.LARGE_SMOKE, pos, 30, 0.0D, 0.0D, 0.0D, 0.15D);
    }

    public static ParticleOptions getItemParticleOption(ItemStack stackIn) {
        return new ItemParticleOption(ParticleTypes.ITEM, stackIn);
    }

    public static BlockParticleOption getBlockParticleOption(Level pLevel, BlockPos pPos)
    {
        return new BlockParticleOption(ParticleTypes.BLOCK, pLevel.getBlockState(pPos));
    }

    public static ParticleOptions getFromSupplier(Supplier<ParticleOptions> supplier) {
        return supplier.get();
    }
}
