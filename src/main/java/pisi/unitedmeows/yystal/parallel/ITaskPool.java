/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.parallel;

import pisi.unitedmeows.yystal.parallel.Future;
import pisi.unitedmeows.yystal.parallel.IFunction;
import pisi.unitedmeows.yystal.parallel.Task;
import pisi.unitedmeows.yystal.parallel.TaskWorker;

public interface ITaskPool {
    public Task run(IFunction<?> var1, Future<?> var2);

    public Task run_w(IFunction<?> var1, Future<?> var2, long var3);

    public int workerCount();

    public Task nextTask();

    public void register();

    public void unregister();

    public TaskWorker getWorker();

    public TaskWorker getWorker(Task var1);

    public void stopWorker(TaskWorker var1);

    public void stopWorker(TaskWorker var1, boolean var2);

    public void newWorker();
}

