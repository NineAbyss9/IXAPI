
package com.github.NineAbyss9.ix_api.util;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;
import org.NineAbyss9.annotation.doc.Message;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ApiRandom {
    private static final Random staticRandom = new Random();
    private final Random random;
    public ApiRandom(long seed) {
        this.random = new Random(seed);
    }

    public ApiRandom() {
        this(System.currentTimeMillis() * Util.getNanos());
    }

    public static int nextInt(@Message("Value should greater than 0") int i) {
        return ThreadLocalRandom.current().nextInt(i);
    }

    public Vector3f randomVec3f() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        return new Vector3f(rand.nextFloat((float)Integer.MAX_VALUE), rand.nextFloat((float)Integer.MAX_VALUE),
                rand.nextFloat((float)Integer.MAX_VALUE));
    }

    public Vec3 randomPosition() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        return new Vec3(rand.nextDouble((double)Integer.MAX_VALUE), rand.nextDouble((double)Integer.MAX_VALUE),
                rand.nextDouble((double)Integer.MAX_VALUE));
    }

    public BlockPos randomPos() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        return BlockPos.containing(rand.nextInt(), rand.nextInt(), rand.nextInt());
    }
}
