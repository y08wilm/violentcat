/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.utils;

import java.util.HashMap;

public class Types {
    protected static HashMap<Class<?>, Integer> typeLengths = new HashMap<Class<?>, Integer>(){
        private static final long serialVersionUID = -2173024138972832406L;
        {
            this.put(Integer.class, 4);
            this.put(Byte.class, 1);
            this.put(Long.class, 8);
            this.put(Short.class, 2);
            this.put(Boolean.class, 1);
            this.put(Double.class, 8);
            this.put(Character.class, 2);
        }
    };

    public static int sizeof(Class<?> clazz) {
        return typeLengths.getOrDefault(clazz, 0);
    }
}

