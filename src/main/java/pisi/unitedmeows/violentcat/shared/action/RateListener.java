/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.violentcat.shared.action;

import java.util.ArrayDeque;
import java.util.Queue;
import pisi.unitedmeows.violentcat.shared.action.Action;
import pisi.unitedmeows.violentcat.shared.action.ActionPool;
import pisi.unitedmeows.violentcat.shared.action.IAction;
import pisi.unitedmeows.yystal.parallel.Async;
import pisi.unitedmeows.yystal.utils.Stopwatch;

public class RateListener<ActionType extends Enum<?>> {
    protected int resetInterval;
    private ActionType type;
    protected int maxRate;
    protected int currentRate;
    protected Queue<Action<?>> actionQueue;
    private Stopwatch stopwatch;
    private ActionPool owner;

    public RateListener(ActionPool _owner, ActionType _type, int _maxRate, int _resetInterval) {
        this.owner = _owner;
        this.type = _type;
        this.resetInterval = _resetInterval;
        this.maxRate = _maxRate;
        this.actionQueue = new ArrayDeque();
        this.stopwatch = new Stopwatch();
    }

    public Action<?> queue(final IAction<?> function2) {
        Action action = new Action(){

            @Override
            public void run() {
                this.end(function2.run());
                RateListener.this.increaseRequestCount();
            }
        };
        this.actionQueue.add(action);
        return action;
    }

    public Action<?> queue(String victim, final IAction<?> function2) {
        Action action = new Action(victim){

            @Override
            public void run() {
                this.end(function2.run());
                RateListener.this.increaseRequestCount();
            }
        };
        this.actionQueue.add(action);
        return action;
    }

    protected void increaseRequestCount() {
        ++this.owner.currentRequestCount;
    }

    public void tick() {
        if (this.stopwatch.isReached(this.resetInterval)) {
            this.stopwatch.reset();
            this.currentRate = 0;
        }
        while (!this.actionQueue.isEmpty() && !this.isRateLimited()) {
            Async.async(this.actionQueue.poll());
            ++this.currentRate;
        }
    }

    private boolean isRateLimited() {
        return this.currentRate >= this.maxRate;
    }
}

