/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.clazz;

import pisi.unitedmeows.yystal.clazz.HookClass;
import pisi.unitedmeows.yystal.utils.kThread;

public class valuelock<X>
extends HookClass<X> {
    private boolean locked;

    public void __setup() {
        this.locked = true;
        kThread.sleep_till(() -> this.locked);
    }

    public void free(X _value) {
        this.hooked = _value;
        this.locked = false;
    }

    public X get() {
        return (X)this.hooked;
    }
}

