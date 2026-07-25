
package com.github.NineAbyss9.ix_api.api.mobs;

import org.slf4j.Logger;

public interface IFlagMob
{
    int getFlag();

    void setFlag(int flag);

    default void nextFlag()
    {
        this.setFlag(this.getFlag() + 1);
    }

    default int getAniTick()
    {
        return 0;
    }

    default void setAniTick(int aniTick) {}

    default boolean isFlag(int flag)
    {
        return this.getFlag() == flag;
    }

    default void resetFlag()
    {
        this.setFlag(0);
    }

    default void resetState() {
        this.resetAniTick();
        this.resetFlag();
    }

    default void increaseAniTick()
    {
        this.setAniTick(this.getAniTick() + 1);
    }

    default boolean aniTickEquals(int pAttackTick)
    {
        return this.getAniTick() == pAttackTick;
    }

    default boolean aniTick(int pAttackTick)
    {
        return this.getAniTick() >= pAttackTick;
    }

    default void resetAniTick()
    {
        this.setAniTick(0);
    }

    default void sendMesAndReset(Logger logger)
    {
        logger.warn("Can't handle synched data in {}, resetting to 0", this.getClass().getSimpleName());
        this.resetFlag();
    }
}
