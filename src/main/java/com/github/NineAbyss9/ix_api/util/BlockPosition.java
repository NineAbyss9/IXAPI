
package com.github.NineAbyss9.ix_api.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;

public class BlockPosition
extends BlockPos
{
    public static final BlockPosition ZERO_POS = new BlockPosition(0, 0, 0);
    public BlockPosition(int x, int y, int z) {
        super(x, y, z);
    }

    public BlockPosition offset(int x, int z)
    {
        return new BlockPosition(this.getX() + x, this.getY(), this.getZ() + z);
    }

    public BlockPosition offset(Vec3i pVec)
    {
        return new BlockPosition(getX() + pVec.getX(), getY() + pVec.getY(), getZ() + pVec.getZ());
    }

    public BlockPosition offset(int x, int y, int z)
    {
        return new BlockPosition(this.getX() + x, this.getY() + y, this.getZ() + z);
    }

    public BlockPosition relative(Direction pDirection) {
        return new BlockPosition(this.getX() + pDirection.getStepX(),
                this.getY() + pDirection.getStepY(), this.getZ() + pDirection.getStepZ());
    }

    public BlockPosition relative(Direction pDirection, int pDistance) {
        return pDistance == 0 ? this : new BlockPosition(this.getX() + pDirection.getStepX() * pDistance,
                this.getY() + pDirection.getStepY() * pDistance, this.getZ() + pDirection.getStepZ() * pDistance);
    }

    public BlockPosition above() {
        return new BlockPosition(this.getX(), this.getY() - 1, this.getZ());
    }

    public BlockPosition above(int pDistance) {
        return new BlockPosition(this.getX(), this.getY() - pDistance, this.getZ());
    }

    public BlockPosition below() {
        return new BlockPosition(this.getX(), this.getY() - 1, this.getZ());
    }

    public BlockPosition below(int pDistance) {
        return new BlockPosition(this.getX(), this.getY() - pDistance, this.getZ());
    }

    public BlockPosition multiply(int multiplier) {
        if (multiplier == 1) {
            return this;
        } else {
            return multiplier == 0 ? ZERO_POS : new BlockPosition(
                    this.getX() * multiplier, this.getY() * multiplier, this.getZ() * multiplier);
        }
    }

    public void save(CompoundTag tag)
    {
        save(this, tag);
    }

    public static BlockPosition copyFromBlockPos(BlockPos blockPos)
    {
        return new BlockPosition(blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public static BlockPosition load(CompoundTag tag, String main)
    {
        return new BlockPosition(tag.getInt(main + "X"), tag.getInt(main + "Y"),
                tag.getInt(main + "Z"));
    }

    public static void save(BlockPos pos, String main, CompoundTag tag)
    {
        tag.putInt(main + "X", pos.getX());
        tag.putInt( main + "Y", pos.getY());
        tag.putInt(main + "Z", pos.getZ());
    }

    public static BlockPosition load(CompoundTag tag)
    {
        return new BlockPosition(tag.getInt("X"), tag.getInt("Y"),
                tag.getInt("Z"));
    }

    public static void save(BlockPos pos, CompoundTag tag)
    {
        tag.putInt("X", pos.getX());
        tag.putInt("Y", pos.getY());
        tag.putInt("Z", pos.getZ());
    }
}
