/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.parallel;

import pisi.unitedmeows.yystal.parallel.Future;
import pisi.unitedmeows.yystal.parallel.IFunction;

public class Task {
    private boolean executed;
    private IFunction<?> function;
    private long took;
    private long startTime;
    private Future<?> future;

    public Task(IFunction<?> function2, Future<?> future) {
        this.function = function2;
        this.future = future;
    }

    public void run() {
        this.startTime = System.currentTimeMillis();
        if (this.future != null) {
            this.future.setResult(this.function.run());
        } else {
            this.function.run();
        }
        this.took = System.currentTimeMillis() - this.startTime;
        this.executed = true;
    }

    public boolean isExecuted() {
        return this.executed;
    }

    public Future<?> future() {
        return this.future;
    }

    public long elapsed() {
        return this.isExecuted() ? this.took() : System.currentTimeMillis() - this.startTime;
    }

    public long took() {
        return this.took;
    }

    public long startTime() {
        return this.startTime;
    }

    @Deprecated
    public Task startTime(long _startTime) {
        this.startTime = _startTime;
        return this;
    }
}

