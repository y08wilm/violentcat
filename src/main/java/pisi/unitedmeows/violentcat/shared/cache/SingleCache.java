/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.violentcat.shared.cache;

import pisi.unitedmeows.yystal.utils.Stopwatch;

public class SingleCache<X> {
    private X value;
    private Stopwatch stopwatch = new Stopwatch();
    private int timeout;

    public SingleCache(int _timeout) {
        this.timeout = _timeout;
    }

    public boolean shouldUpdate() {
        return this.stopwatch.elapsed() > (long)this.timeout;
    }

    public SingleCache<X> value(X _value) {
        this.value = _value;
        return this;
    }

    public X value() {
        return this.value;
    }
}

