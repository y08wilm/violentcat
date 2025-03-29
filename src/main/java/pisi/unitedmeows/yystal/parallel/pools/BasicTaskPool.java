/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.parallel.pools;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import pisi.unitedmeows.yystal.YSettings;
import pisi.unitedmeows.yystal.YYStal;
import pisi.unitedmeows.yystal.parallel.Future;
import pisi.unitedmeows.yystal.parallel.IFunction;
import pisi.unitedmeows.yystal.parallel.ITaskPool;
import pisi.unitedmeows.yystal.parallel.Task;
import pisi.unitedmeows.yystal.parallel.TaskWorker;
import pisi.unitedmeows.yystal.utils.kThread;

public class BasicTaskPool
implements ITaskPool {
    private List<TaskWorker> taskWorkers;
    private Thread controlThread;
    private int minWorkers;
    private int maxWorkers;
    private ConcurrentLinkedQueue<Task> taskQueue;
    private List<Task> waitingTasks;
    private boolean running;

    public BasicTaskPool(int minWorkers, int maxWorkers) {
        this.minWorkers = minWorkers;
        this.maxWorkers = maxWorkers;
        this.taskWorkers = new CopyOnWriteArrayList<TaskWorker>();
        this.waitingTasks = new LinkedList<Task>();
        this.taskQueue = new ConcurrentLinkedQueue();
    }

    @Override
    public void register() {
        this.running = true;
        this.controlThread = new Thread(this::control);
        for (int i = 0; i < this.minWorkers; ++i) {
            TaskWorker taskWorker = new TaskWorker();
            this.taskWorkers.add(taskWorker);
            taskWorker.start();
        }
        this.controlThread.start();
    }

    @Override
    public void unregister() {
        for (int i = 0; i < this.taskWorkers.size(); ++i) {
            TaskWorker taskWorker = this.taskWorkers.get(i);
            taskWorker.stopWorker();
        }
        this.taskWorkers.clear();
        this.taskQueue.clear();
        this.running = false;
    }

    public void control() {
        while (this.running) {
            int i;
            boolean allBusy = true;
            for (int i2 = 0; i2 < this.taskWorkers.size(); ++i2) {
                TaskWorker taskWorker = this.taskWorkers.get(i2);
                if (taskWorker.isBusy()) continue;
                allBusy = false;
                break;
            }
            if (allBusy && this.workerCount() < this.maxWorkers) {
                TaskWorker taskWorker = new TaskWorker();
                this.taskWorkers.add(taskWorker);
                taskWorker.start();
            }
            if (!this.waitingTasks.isEmpty()) {
                try {
                    long time = System.currentTimeMillis();
                    for (i = 0; i < this.waitingTasks.size(); ++i) {
                        try {
                            Task task = this.waitingTasks.get(i);
                            if (task.startTime() >= time) continue;
                            this.taskQueue.add(task);
                            this.waitingTasks.remove(task);
                            --i;
                            continue;
                        }
                        catch (Exception task) {
                            // empty catch block
                        }
                    }
                }
                catch (ConcurrentModificationException e) {
                    e.printStackTrace();
                }
            }
            if (!allBusy && this.workerCount() > this.minWorkers) {
                long time = System.currentTimeMillis() - 500L;
                for (i = 0; i < this.taskWorkers.size(); ++i) {
                    TaskWorker taskWorker;
                    if (this.workerCount() <= this.minWorkers || (taskWorker = this.taskWorkers.get(i)).lastTaskFinish() >= time) continue;
                    this.taskWorkers.remove(i);
                    --i;
                }
            }
            kThread.sleep((Long)YYStal.setting(YSettings.TASKPOOL_CONTROL_CHECK_DELAY));
        }
    }

    @Override
    public Task run(IFunction<?> function2, Future<?> future) {
        Task task = new Task(function2, future);
        this.taskQueue.add(task);
        return task;
    }

    @Override
    public Task run_w(IFunction<?> function2, Future<?> future, long after) {
        if (after <= 0L) {
            return this.run(function2, future);
        }
        Task task = new Task(function2, future);
        task.startTime(System.currentTimeMillis() + after);
        this.waitingTasks.add(task);
        return task;
    }

    @Override
    public TaskWorker getWorker() {
        Thread thread = Thread.currentThread();
        for (TaskWorker taskWorker : this.taskWorkers) {
            if (taskWorker != thread) continue;
            return taskWorker;
        }
        return null;
    }

    @Override
    public TaskWorker getWorker(Task task) {
        for (TaskWorker taskWorker : this.taskWorkers) {
            if (taskWorker.currentTask() != task) continue;
            return taskWorker;
        }
        return null;
    }

    @Override
    public void stopWorker(TaskWorker worker, boolean abort) {
        Iterator<TaskWorker> taskWorkerIterator = this.taskWorkers.iterator();
        while (taskWorkerIterator.hasNext()) {
            TaskWorker taskWorker = taskWorkerIterator.next();
            if (taskWorker != worker) continue;
            taskWorkerIterator.remove();
            break;
        }
        if (abort) {
            worker.abortWorker();
        } else {
            worker.stopWorker();
        }
    }

    @Override
    public void stopWorker(TaskWorker worker) {
        this.stopWorker(worker, false);
    }

    @Override
    public void newWorker() {
        TaskWorker taskWorker = new TaskWorker();
        this.taskWorkers.add(taskWorker);
        taskWorker.start();
    }

    @Override
    public int workerCount() {
        return this.taskWorkers.size();
    }

    @Override
    public Task nextTask() {
        return this.taskQueue.poll();
    }
}

