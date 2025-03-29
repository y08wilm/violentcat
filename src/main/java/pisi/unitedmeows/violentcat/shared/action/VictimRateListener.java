/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.violentcat.shared.action;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import pisi.unitedmeows.violentcat.shared.action.Action;
import pisi.unitedmeows.violentcat.shared.action.ActionPool;
import pisi.unitedmeows.violentcat.shared.action.IAction;
import pisi.unitedmeows.violentcat.shared.action.RateListener;
import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;
import pisi.unitedmeows.yystal.parallel.Async;
import pisi.unitedmeows.yystal.utils.Stopwatch;

public class VictimRateListener<MajorType extends Enum<?>>
extends RateListener<MajorType> {
    private Map<String, VictimRate> victimMap = new HashMap<String, VictimRate>();

    public VictimRateListener(ActionPool _owner, MajorType _type, int _maxRate, int _resetInterval) {
        super(_owner, _type, _maxRate, _resetInterval);
    }

    @Override
    public void tick() {
        this.victimMap.entrySet().removeIf(e -> !((VictimRate)e.getValue()).isRateLimited() && ((VictimRate)e.getValue()).actionQueue.isEmpty());
        this.victimMap.forEach((key, value) -> {
            if (value.stopwatch.isReached(this.resetInterval)) {
                value.rate = 0;
                value.stopwatch.reset();
            }
        });
        for (VictimRate victim : this.victimMap.values()) {
            Action<?> action;
            if (victim.isRateLimited() || (action = victim.poll()) == null) continue;
            ++victim.rate;
            Async.async(action);
        }
    }

    @Override
    public Action<?> queue(String victim, final IAction<?> functionalAction) {
        Action action = new Action(victim){

            @Override
            public void run() {
                this.end(functionalAction.run());
                VictimRateListener.this.increaseRequestCount();
            }
        };
        if (!action.victim.isEmpty()) {
            VictimRate rate = this.victimMap.computeIfAbsent(action.victim, k -> new VictimRate(this));
            rate.queue(action);
            return action;
        }
        return Action.BLANK_ACTION;
    }

    @Override
    public Action<?> queue(IAction<?> action) {
        return Action.BLANK_ACTION;
    }

    @OnlyLibCalls
    public class VictimRate {
        int rate;
        Stopwatch stopwatch;
        LinkedBlockingQueue<Action<?>> actionQueue;
        VictimRateListener owner;

        public VictimRate(VictimRateListener _owner) {
            this.owner = _owner;
            this.rate = 0;
            this.stopwatch = new Stopwatch();
            this.actionQueue = new LinkedBlockingQueue();
        }

        boolean isRateLimited() {
            return this.rate >= VictimRateListener.this.maxRate;
        }

        public void queue(Action<?> action) {
            this.actionQueue.add(action);
        }

        public Action<?> poll() {
            return this.actionQueue.poll();
        }
    }
}

