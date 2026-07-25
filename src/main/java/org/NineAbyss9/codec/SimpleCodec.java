
package org.NineAbyss9.codec;

import org.NineAbyss9.code.Code;
import org.NineAbyss9.util.IXUtil;

class SimpleCodec<E>
implements Codec<E, SimpleCodec<E>>
{
    final E code;
    String key = "";
    boolean initialized;
    boolean locked;
    public SimpleCodec(final E element) {
        this.code = element;
    }

    public SimpleCodec<E> field(String st) {
        return IXUtil.newUnsupportedOperation();
    }

    public E decode() {
        if (this.locked) return (E)null;
        return code;
    }

    public void encode(Code code) {
        this.decode(code.read());
    }

    public void decode(Code code) {
        this.decode(code.read());
    }

    public void encode(final String code) {
        if (this.initialized) return;
        this.key = code;
        this.initialized = true;
        this.locked = true;
    }

    public void decode(String code) {
        if (key.equals(code)) {
            this.locked = false;
        }
    }
}
