
package com.github.NineAbyss9.ix_api.api;

import org.NineAbyss9.annotation.Unused;

@Unused
public interface ISync {
    Synchronizer getSynchronizer();

    void loadWithSynchronizer(Synchronizer synchronizer);

    void saveSynchronizer();
}
