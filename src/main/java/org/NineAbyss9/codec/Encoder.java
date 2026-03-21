
package org.NineAbyss9.codec;

import org.NineAbyss9.annotation.doc.Building;
import org.NineAbyss9.code.Code;

@Building
public interface Encoder {
    /**Encode a {@linkplain Code}*/
    void encode(Code code);

    /**Encode a prase of {@linkplain String}*/
    void encode(String code);
}
