
package org.NineAbyss9.codec;

import org.NineAbyss9.annotation.doc.Building;
import org.NineAbyss9.code.Code;

@Building
public interface Decoder {
    /**Decode a {@linkplain Code}*/
    void decode(Code code);

    /**Decode a prase of {@linkplain String}*/
    void decode(String code);
}
