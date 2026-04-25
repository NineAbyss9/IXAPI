
package com.github.NineAbyss9.ix_api.api.mobs.util;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import org.NineAbyss9.util.IXUtil;
import org.NineAbyss9.util.IXUtilUser;

public interface IHaveDifNavigation<N extends PathNavigation>
extends IXUtilUser {
    default N getDifNavigation() {
        return IXUtil.c.convert(((Mob)this).getNavigation());
    }
}
