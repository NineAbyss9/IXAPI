
package org.NineAbyss9.codec;

import org.NineAbyss9.code.Code;

public class SimpleCodec<E>
implements Codec<E> {
    final E code;
    String key = "";
    boolean initialized;
    boolean locked;
    public SimpleCodec(final E element) {
        this.code = element;
    }

    public Codec<E> field(String st) {
        return this;
    }

    public E decode() {
        if (this.locked) return (E)null;
        return code;
    }

    public void encode(Code code) {

    }

    public void decode(Code code) {

    }

    public void encode(String code) {
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
