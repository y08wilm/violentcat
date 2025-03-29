/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.violentcat.shared.action;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;
import pisi.unitedmeows.yystal.YSettings;
import pisi.unitedmeows.yystal.YYStal;
import pisi.unitedmeows.yystal.utils.Stopwatch;
import pisi.unitedmeows.yystal.utils.kThread;

public abstract class Action<Result>
implements Runnable {
    public static Action<?> BLANK_ACTION = new Action<Object>(){

        @Override
        public void run() {
        }
    };
    public static final int TASK_COMPLETED = 1;
    public static final int TASK_FAILED = -1;
    public static final int TASK_UNKNOWN_RESULT = -1;
    static final int DEFAULT_TIMEOUT = 10000;
    protected Stopwatch stopwatch;
    protected long took;
    protected boolean finished;
    protected Result result;
    protected String victim;
    protected List<Consumer<Result>> postTasks;

    public Action() {
        this("");
    }

    public Action(String _victim) {
        this.victim = _victim;
        this.postTasks = new ArrayList<Consumer<Result>>(0);
        this.stopwatch = new Stopwatch();
    }

    @Deprecated
    @OnlyLibCalls
    public void end(Result _result) {
        this.result = _result;
        this.took = this.stopwatch.elapsed();
        this.finished = true;
        this.stopwatch.reset();
        for (Consumer<Result> task : this.postTasks) {
            task.accept(this.result);
        }
    }

    public Result await() {
        while (!this.finished) {
            kThread.sleep((Long)YYStal.setting(YSettings.TASK_AWAIT_DELAY));
        }
        return this.result;
    }

    public Action<Result> then(Consumer<Result> result) {
        this.postTasks.add(result);
        return this;
    }

    public static <X> Action<X> completedTask(final Object obj) {
        return new Action(){

            @Override
            public void run() {
                this.end(obj);
            }
        };
    }

    static {
        Action.BLANK_ACTION.result = null;
        Action.BLANK_ACTION.finished = true;
    }
}

