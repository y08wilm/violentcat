/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.parallel.utils;

import pisi.unitedmeows.yystal.parallel.Async;
import pisi.unitedmeows.yystal.parallel.Promise;

public abstract class YTimer {
    private Promise promise;
    private long interval;

    public YTimer(long _interval) {
        this.setInterval(_interval);
    }

    public abstract void tick();

    public YTimer start() {
        if (this.promise == null || !this.promise.isValid()) {
            this.promise = Async.async_loop_w(this::tick, this.interval);
        }
        return this;
    }

    public YTimer stop() {
        this.promise.stop();
        return this;
    }

    public YTimer setInterval(long _interval) {
        this.interval = _interval;
        if (this.promise != null && this.promise.isValid()) {
            this.promise.stop();
            this.promise = Async.async_loop_w(this::tick, this.interval);
        }
        return this;
    }
}

