
package org.NineAbyss9.codec;

import org.NineAbyss9.annotation.doc.Building;
import org.NineAbyss9.code.Code;
import org.NineAbyss9.util.IXUtil;
import org.NineAbyss9.util.IXUtilUser;

@Building
public interface Codec<E, C extends Codec<E, C>> extends Decoder, Encoder, IXUtilUser
{
    C field(String st);

    E decode();

    void encode(Code code);

    void decode(Code code);

    void encode(final String code);

    void decode(String code);

    static <E, C extends Codec<E, C>> C newSimple(E e)
    {
        return IXUtil.c.<C>convert(new SimpleCodec<E>(e));
    }
}
