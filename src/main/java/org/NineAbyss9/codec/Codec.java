
package org.NineAbyss9.codec;

import org.NineAbyss9.annotation.doc.Building;
import org.NineAbyss9.code.Code;

@Building
public interface Codec<E> extends Decoder, Encoder {
    Codec<E> field(String st);

    E decode();

    void encode(Code code);

    void decode(Code code);

    void encode(final String code);

    void decode(String code);
}
