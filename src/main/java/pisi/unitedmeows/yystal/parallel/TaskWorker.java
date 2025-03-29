/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.parallel;

import pisi.unitedmeows.yystal.YSettings;
import pisi.unitedmeows.yystal.YYStal;
import pisi.unitedmeows.yystal.parallel.Task;
import pisi.unitedmeows.yystal.utils.Stopwatch;
import pisi.unitedmeows.yystal.utils.kThread;

public class TaskWorker
extends Thread {
    private boolean busy;
    private Stopwatch stopwatch = new Stopwatch();
    private long elapsed;
    private long lastFinish;
    private Task currentTask;
    private boolean running;

    @Override
    public void run() {
        this.running = true;
        while (this.isRunning()) {
            this.currentTask = YYStal.taskPool().nextTask();
            if (this.currentTask != null) {
                this.busy = true;
                this.stopwatch.reset();
                this.currentTask.run();
                this.elapsed = this.stopwatch.elapsed();
                this.lastFinish = System.currentTimeMillis();
                this.busy = false;
            }
            kThread.sleep((Long)YYStal.setting(YSettings.TASKWORKER_FETCH_DELAY));
        }
    }

    public void stopWorker() {
        this.running = false;
    }

    public void abortWorker() {
        this.stopWorker();
        try {
            this.stop();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public Task currentTask() {
        return this.currentTask;
    }

    public long lastTaskFinish() {
        return this.elapsed;
    }

    public boolean isRunning() {
        return this.running;
    }

    public boolean isBusy() {
        return this.busy;
    }

    public long busyTime() {
        return this.isBusy() ? this.stopwatch.elapsed() : 0L;
    }
}

