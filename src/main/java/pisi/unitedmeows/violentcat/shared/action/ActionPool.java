/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.violentcat.shared.action;

import java.util.HashMap;
import java.util.Map;
import pisi.unitedmeows.violentcat.shared.action.RateListener;
import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;
import pisi.unitedmeows.yystal.parallel.Async;
import pisi.unitedmeows.yystal.parallel.Promise;
import pisi.unitedmeows.yystal.utils.Stopwatch;

public class ActionPool<ActionTypes> {
    protected Map<ActionTypes, RateListener> rateListenerMap;
    private Promise loopPromise;
    protected int globalLimit = 50;
    protected int currentRequestCount;
    private Stopwatch globalLimitListener = new Stopwatch();

    public ActionPool() {
        this.rateListenerMap = new HashMap<ActionTypes, RateListener>();
    }

    @OnlyLibCalls
    protected void registerRateListener(ActionTypes type, RateListener listener) {
        this.rateListenerMap.put(type, listener);
    }

    public RateListener rateListener(ActionTypes type) {
        return this.rateListenerMap.getOrDefault(type, null);
    }

    @OnlyLibCalls
    public void start() {
        this.loopPromise = Async.async_loop(this::tick, 25L);
    }

    @OnlyLibCalls
    public void tick() {
        for (RateListener listener : this.rateListenerMap.values()) {
            listener.tick();
        }
        if (this.globalLimitListener.isReached(1000L)) {
            if (this.currentRequestCount >= this.globalLimit) {
                System.out.println("YOU ARE EXCEEDING THE GLOBAL DISCORD LIMIT");
            }
            this.currentRequestCount = 0;
            this.globalLimitListener.reset();
        }
    }
}

