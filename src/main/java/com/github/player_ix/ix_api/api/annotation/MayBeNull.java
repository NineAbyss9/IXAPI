
package com.github.player_ix.ix_api.api.annotation;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;

@Nonnull(when = When.MAYBE)
public @interface MayBeNull {
}
