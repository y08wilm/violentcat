/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.clazz;

import java.util.HashMap;
import pisi.unitedmeows.yystal.utils.YRandom;

public class shared<X> {
    private final HashMap<Byte, X> values = new HashMap();

    public X get(byte data) {
        return this.values.getOrDefault(data, null);
    }

    public byte put(X obj) {
        byte identifier;
        while (this.values.containsKey(identifier = YRandom.BASIC.nextByte())) {
        }
        this.values.put(identifier, obj);
        return identifier;
    }

    public void remove(byte identifier) {
        this.values.remove(identifier);
    }
}

