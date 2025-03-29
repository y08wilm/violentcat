/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.clazz;

import pisi.unitedmeows.yystal.utils.kThread;

public class mutex {
    private boolean locked;

    public void lock() {
        this.locked = true;
        kThread.sleep_till(() -> this.locked);
    }

    public void unlock() {
        this.locked = false;
    }
}

