/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.parallel.repeaters;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import pisi.unitedmeows.yystal.YYStal;
import pisi.unitedmeows.yystal.clazz.ref;
import pisi.unitedmeows.yystal.parallel.Async;
import pisi.unitedmeows.yystal.parallel.Promise;
import pisi.unitedmeows.yystal.parallel.repeaters.Repeater;
import pisi.unitedmeows.yystal.utils.IDisposable;

public class RepeaterPool
implements IDisposable {
    private Map<Integer, List<Repeater>> repeaterMap = new HashMap<Integer, List<Repeater>>();
    private List<Promise> promiseList = new LinkedList<Promise>();

    public Repeater createRepeater(int interval) {
        return this.registerRepeater(interval, new Repeater(interval));
    }

    public Repeater registerRepeater(int interval, Repeater repeater) {
        this.repeaterMap.computeIfAbsent(interval, k -> {
            LinkedList<Repeater> list = new LinkedList<Repeater>();
            this.createAsyncPool(list, interval);
            return list;
        }).add(repeater);
        return repeater;
    }

    public boolean unregisterRepeater(int interval, Repeater repeater) {
        List repeaters = this.repeaterMap.getOrDefault(interval, null);
        return repeaters.remove(repeater);
    }

    private void createAsyncPool(LinkedList<Repeater> repeaters, int interval) {
        ref<Promise> promise = YYStal.reference();
        promise.set(Async.async_loop_w(() -> {
            repeaters.forEach(Repeater::_tick);
            if (repeaters.isEmpty()) {
                ((Promise)promise.get()).stop();
            }
        }, interval));
        this.promiseList.add((Promise)promise.get());
    }

    @Override
    public void close() {
        this.promiseList.forEach(Promise::stop);
        this.repeaterMap.values().forEach(x -> x.forEach(Repeater::pause));
        this.repeaterMap.clear();
    }
}

