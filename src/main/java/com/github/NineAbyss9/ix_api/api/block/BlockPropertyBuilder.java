
package com.github.NineAbyss9.ix_api.api.block;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.ToIntFunction;

@SuppressWarnings({"deprecation", "OptionalAsField"})
public class BlockPropertyBuilder {
    Function<BlockState, MapColor> mapColor = (p_284884_) -> {
        return MapColor.NONE;
    };
    boolean hasCollision = true;
    SoundType soundType = SoundType.STONE;
    ToIntFunction<BlockState> lightEmission = (p_60929_) -> {
        return 0;
    };
    float explosionResistance;
    float destroyTime;
    boolean requiresCorrectToolForDrops;
    boolean isRandomlyTicking;
    float friction = 0.6F;
    float speedFactor = 1.0F;
    float jumpFactor = 1.0F;
    /** Sets loot table information */
    ResourceLocation drops;
    boolean canOcclude = true;
    boolean isAir;
    boolean ignitedByLava;
    /** @deprecated */
    @Deprecated
    boolean liquid;
    /** @deprecated */
    @Deprecated
    boolean forceSolidOff;
    boolean forceSolidOn;
    PushReaction pushReaction = PushReaction.NORMAL;
    boolean spawnParticlesOnBreak = true;
    NoteBlockInstrument instrument = NoteBlockInstrument.HARP;
    boolean replaceable;
    private java.util.function.Supplier<ResourceLocation> lootTableSupplier;
    BlockBehaviour.StateArgumentPredicate<EntityType<?>> isValidSpawn = (p_284893_, p_284894_, p_284895_, p_284896_) -> {
        return p_284893_.isFaceSturdy(p_284894_, p_284895_, Direction.UP) && p_284893_.getLightEmission(p_284894_, p_284895_) < 14;
    };
    BlockBehaviour.StatePredicate isRedstoneConductor = BlockBehaviour.BlockStateBase::isCollisionShapeFullBlock;
    BlockBehaviour.StatePredicate isSuffocating = (p_284885_, p_284886_, p_284887_) ->
            p_284885_.blocksMotion() && p_284885_.isCollisionShapeFullBlock(p_284886_, p_284887_);
    /** If it blocks vision on the client side. */
    BlockBehaviour.StatePredicate isViewBlocking = this.isSuffocating;
    BlockBehaviour.StatePredicate hasPostProcess = (p_60963_, p_60964_, p_60965_) -> {
        return false;
    };
    BlockBehaviour.StatePredicate emissiveRendering = (p_60931_, p_60932_, p_60933_) -> {
        return false;
    };
    boolean dynamicShape;
    FeatureFlagSet requiredFeatures = FeatureFlags.VANILLA_SET;
    Optional<BlockBehaviour.OffsetFunction> offsetFunction = Optional.empty();

    public static BlockPropertyBuilder of() {
        return new BlockPropertyBuilder();
    }

    /*public static BlockPropertyBuilder copy(Block pBlockBehaviour) {
        BlockPropertyBuilder builder = new BlockPropertyBuilder();
        builder.destroyTime = pBlockBehaviour.properties.destroyTime;
        builder.explosionResistance = pBlockBehaviour.properties.explosionResistance;
        builder.hasCollision = pBlockBehaviour.properties.hasCollision;
        builder.isRandomlyTicking = pBlockBehaviour.properties.isRandomlyTicking;
        builder.lightEmission = pBlockBehaviour.properties.lightEmission;
        builder.mapColor = pBlockBehaviour.properties.mapColor;
        builder.soundType = pBlockBehaviour.properties.soundType;
        builder.friction = pBlockBehaviour.properties.friction;
        builder.speedFactor = pBlockBehaviour.properties.speedFactor;
        builder.dynamicShape = pBlockBehaviour.properties.dynamicShape;
        builder.canOcclude = pBlockBehaviour.properties.canOcclude;
        builder.isAir = pBlockBehaviour.properties.isAir;
        builder.ignitedByLava = pBlockBehaviour.properties.ignitedByLava;
        builder.liquid = pBlockBehaviour.properties.liquid;
        builder.forceSolidOff = pBlockBehaviour.properties.forceSolidOff;
        builder.forceSolidOn = pBlockBehaviour.properties.forceSolidOn;
        builder.pushReaction = pBlockBehaviour.properties.pushReaction;
        builder.requiresCorrectToolForDrops = pBlockBehaviour.properties.requiresCorrectToolForDrops;
        builder.offsetFunction = pBlockBehaviour.properties.offsetFunction;
        builder.spawnParticlesOnBreak = pBlockBehaviour.properties.spawnParticlesOnBreak;
        builder.requiredFeatures = pBlockBehaviour.properties.requiredFeatures;
        builder.emissiveRendering = pBlockBehaviour.properties.emissiveRendering;
        builder.instrument = pBlockBehaviour.properties.instrument;
        builder.replaceable = pBlockBehaviour.properties.replaceable;
        return builder;
    }*/

    public BlockPropertyBuilder mapColor(DyeColor pMapColor) {
        this.mapColor = (p_284892_) -> pMapColor.getMapColor();
        return this;
    }

    public BlockPropertyBuilder mapColor(MapColor pMapColor) {
        this.mapColor = (p_222988_) -> pMapColor;
        return this;
    }

    public BlockPropertyBuilder mapColor(Function<BlockState, MapColor> pMapColor) {
        this.mapColor = pMapColor;
        return this;
    }

    public BlockPropertyBuilder noCollision() {
        this.hasCollision = false;
        this.canOcclude = false;
        return this;
    }

    public BlockPropertyBuilder noOcclusion() {
        this.canOcclude = false;
        return this;
    }

    public BlockPropertyBuilder friction(float pFriction) {
        this.friction = pFriction;
        return this;
    }

    public BlockPropertyBuilder speedFactor(float pSpeedFactor) {
        this.speedFactor = pSpeedFactor;
        return this;
    }

    public BlockPropertyBuilder jumpFactor(float pJumpFactor) {
        this.jumpFactor = pJumpFactor;
        return this;
    }

    public BlockPropertyBuilder sound(SoundType pSoundType) {
        this.soundType = pSoundType;
        return this;
    }

    public BlockPropertyBuilder lightLevel(ToIntFunction<BlockState> pLightEmission) {
        this.lightEmission = pLightEmission;
        return this;
    }

    public BlockPropertyBuilder strength(float pDestroyTime, float pExplosionResistance) {
        return this.destroyTime(pDestroyTime).explosionResistance(pExplosionResistance);
    }

    public BlockPropertyBuilder instabreak() {
        return this.strength(0.0F);
    }

    public BlockPropertyBuilder strength(float pStrength) {
        this.strength(pStrength, pStrength);
        return this;
    }

    public BlockPropertyBuilder randomTicks() {
        this.isRandomlyTicking = true;
        return this;
    }

    public BlockPropertyBuilder dynamicShape() {
        this.dynamicShape = true;
        return this;
    }

    public BlockPropertyBuilder noLootTable() {
        this.drops = BuiltInLootTables.EMPTY;
        return this;
    }

    @Deprecated // FORGE: Use the variant that takes a Supplier below
    public BlockPropertyBuilder dropsLike(Block pBlock) {
        this.lootTableSupplier = () -> net.minecraftforge.registries.ForgeRegistries.BLOCKS.getDelegateOrThrow(pBlock).get()
                .getLootTable();
        return this;
    }

    public BlockPropertyBuilder lootFrom(java.util.function.Supplier<? extends Block> blockIn) {
        this.lootTableSupplier = () -> blockIn.get().getLootTable();
        return this;
    }

    public BlockPropertyBuilder ignitedByLava() {
        this.ignitedByLava = true;
        return this;
    }

    public BlockPropertyBuilder liquid() {
        this.liquid = true;
        return this;
    }

    public BlockPropertyBuilder forceSolidOn() {
        this.forceSolidOn = true;
        return this;
    }

    /** @deprecated */
    @Deprecated
    public BlockPropertyBuilder forceSolidOff() {
        this.forceSolidOff = true;
        return this;
    }

    public BlockPropertyBuilder pushReaction(PushReaction pPushReaction) {
        this.pushReaction = pPushReaction;
        return this;
    }

    public BlockPropertyBuilder air() {
        this.isAir = true;
        return this;
    }

    public BlockPropertyBuilder isValidSpawn(BlockBehaviour.StateArgumentPredicate<EntityType<?>> pIsValidSpawn) {
        this.isValidSpawn = pIsValidSpawn;
        return this;
    }

    public BlockPropertyBuilder isRedstoneConductor(BlockBehaviour.StatePredicate pIsRedstoneConductor) {
        this.isRedstoneConductor = pIsRedstoneConductor;
        return this;
    }

    public BlockPropertyBuilder isSuffocating(BlockBehaviour.StatePredicate pIsSuffocating) {
        this.isSuffocating = pIsSuffocating;
        return this;
    }

    /**
     * If it blocks vision on the client side.
     */
    public BlockPropertyBuilder isViewBlocking(BlockBehaviour.StatePredicate pIsViewBlocking) {
        this.isViewBlocking = pIsViewBlocking;
        return this;
    }

    public BlockPropertyBuilder hasPostProcess(BlockBehaviour.StatePredicate pHasPostProcess) {
        this.hasPostProcess = pHasPostProcess;
        return this;
    }

    public BlockPropertyBuilder emissiveRendering(BlockBehaviour.StatePredicate pEmissiveRendering) {
        this.emissiveRendering = pEmissiveRendering;
        return this;
    }

    public BlockPropertyBuilder requiresCorrectToolForDrops() {
        this.requiresCorrectToolForDrops = true;
        return this;
    }

    public BlockPropertyBuilder destroyTime(float pDestroyTime) {
        this.destroyTime = pDestroyTime;
        return this;
    }

    public BlockPropertyBuilder explosionResistance(float pExplosionResistance) {
        this.explosionResistance = Math.max(0.0F, pExplosionResistance);
        return this;
    }

    public BlockPropertyBuilder offsetType(BlockBehaviour.OffsetType pOffsetType) {
        switch (pOffsetType) {
            case XYZ:
                this.offsetFunction = Optional.of((p_272562_, p_272563_, p_272564_) -> {
                    Block block = p_272562_.getBlock();
                    long i = Mth.getSeed(p_272564_.getX(), 0, p_272564_.getZ());
                    double d0 = ((double)((float)(i >> 4 & 15L) / 15.0F) - 1.0D) * (double)block.getMaxVerticalOffset();
                    float f = block.getMaxHorizontalOffset();
                    double d1 = Mth.clamp(((double)((float)(i & 15L) / 15.0F) - 0.5D) * 0.5D, (double)(-f), (double)f);
                    double d2 = Mth.clamp(((double)((float)(i >> 8 & 15L) / 15.0F) - 0.5D) * 0.5D, (double)(-f), (double)f);
                    return new Vec3(d1, d0, d2);
                });
                break;
            case XZ:
                this.offsetFunction = Optional.of((p_272565_, p_272566_, p_272567_) -> {
                    Block block = p_272565_.getBlock();
                    long i = Mth.getSeed(p_272567_.getX(), 0, p_272567_.getZ());
                    float f = block.getMaxHorizontalOffset();
                    double d0 = Mth.clamp(((double)((float)(i & 15L) / 15.0F) - 0.5D) * 0.5D, (double)(-f), (double)f);
                    double d1 = Mth.clamp(((double)((float)(i >> 8 & 15L) / 15.0F) - 0.5D) * 0.5D, (double)(-f), (double)f);
                    return new Vec3(d0, 0.0D, d1);
                });
                break;
            default:
                this.offsetFunction = Optional.empty();
        }

        return this;
    }

    public BlockPropertyBuilder noParticlesOnBreak() {
        this.spawnParticlesOnBreak = false;
        return this;
    }

    public BlockPropertyBuilder requiredFeatures(FeatureFlag... pRequiredFeatures) {
        this.requiredFeatures = FeatureFlags.REGISTRY.subset(pRequiredFeatures);
        return this;
    }

    public BlockPropertyBuilder instrument(NoteBlockInstrument pInstrument) {
        this.instrument = pInstrument;
        return this;
    }

    public BlockPropertyBuilder replaceable() {
        this.replaceable = true;
        return this;
    }

    public BlockBehaviour.Properties build() {
        return BlockBehaviour.Properties.of();
    }
}
