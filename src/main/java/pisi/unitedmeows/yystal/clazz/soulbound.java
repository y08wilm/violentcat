/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.clazz;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;
import pisi.unitedmeows.yystal.parallel.Async;
import pisi.unitedmeows.yystal.parallel.Promise;

public abstract class soulbound<I, X> {
    protected HashMap<I, X> boundMap;
    protected Promise promise;
    private long checkTimer;

    public soulbound(long _checkTimer) {
        this.checkTimer = _checkTimer;
        this.boundMap = new HashMap();
        this.promise = Async.async_loop(() -> {
            Iterator<Map.Entry<I, X>> entryIterator = this.boundMap.entrySet().iterator();
            while (entryIterator.hasNext()) {
                Map.Entry<I, X> entry = entryIterator.next();
                if (!this.breakBound(entry.getKey(), entry.getValue())) continue;
                this.onRemove(entry.getKey(), entry.getValue());
                entryIterator.remove();
            }
        }, _checkTimer);
    }

    public soulbound() {
        this(1000L);
    }

    public X get(I instance) {
        return this.boundMap.get(instance);
    }

    public void add(I instance, X value) {
        this.boundMap.put(instance, value);
    }

    public void remove(I instance) {
        this.boundMap.remove(instance);
    }

    public void onRemove(I instance, X value) {
    }

    public X computeIfAbsent(I instance, Function<I, X> function2) {
        return this.boundMap.computeIfAbsent(instance, function2);
    }

    public void stop() {
        this.promise.stop();
        this.promise = null;
    }

    public void start() {
        this.promise = Async.async_loop(() -> this.boundMap.entrySet().removeIf(entry -> this.breakBound(entry.getKey(), entry.getValue())), this.checkTimer);
    }

    public abstract boolean breakBound(I var1, X var2);
}

