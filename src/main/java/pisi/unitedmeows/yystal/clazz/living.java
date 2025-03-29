/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.clazz;

import java.util.function.Supplier;
import pisi.unitedmeows.yystal.parallel.Async;
import pisi.unitedmeows.yystal.parallel.Promise;

public class living<X> {
    private X value;
    private Promise promise;
    private Supplier<X> supplier;
    private final long interval;

    public living(X _value, long _interval) {
        this.interval = _interval;
        this.value = _value;
        this.promise = Async.async_loop_w(() -> {
            this.value = this.supplier.get();
        }, this.interval);
    }

    public living(long _interval) {
        this.interval = _interval;
        this.promise = Async.async_loop(() -> {
            this.value = this.supplier.get();
        }, this.interval);
    }

    public living<X> update(Supplier<X> _supplier) {
        this.supplier = _supplier;
        return this;
    }

    public living<X> pause() {
        this.promise.stop();
        return this;
    }

    public living<X> resume() {
        this.promise = Async.async_loop_w(() -> {
            this.value = this.supplier.get();
        }, this.interval);
        return this;
    }

    public X get() {
        return this.value;
    }
}

