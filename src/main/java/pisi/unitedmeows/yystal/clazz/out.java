/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.clazz;

import pisi.unitedmeows.yystal.clazz.HookClass;

public class out<X>
extends HookClass<X> {
    public X get() {
        return (X)this.hooked;
    }

    public void set(X value) {
        this.hooked = value;
    }
}

