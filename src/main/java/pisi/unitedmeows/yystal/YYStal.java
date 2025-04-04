package pisi.unitedmeows.yystal;

import pisi.unitedmeows.yystal.clazz.function;
import pisi.unitedmeows.yystal.clazz.out;
import pisi.unitedmeows.yystal.clazz.ref;
import pisi.unitedmeows.yystal.clazz.valuelock;
import pisi.unitedmeows.yystal.exception.YBasicTry;
import pisi.unitedmeows.yystal.exception.YEx;
import pisi.unitedmeows.yystal.exception.YExManager;
import pisi.unitedmeows.yystal.exception.YExceptionRunnable;
import pisi.unitedmeows.yystal.exception.impl.YexIO;
import pisi.unitedmeows.yystal.hook.YString;
import pisi.unitedmeows.yystal.logger.impl.YLogger;
import pisi.unitedmeows.yystal.parallel.ITaskPool;
import pisi.unitedmeows.yystal.parallel.pools.BasicTaskPool;
import pisi.unitedmeows.yystal.parallel.repeaters.Repeater;
import pisi.unitedmeows.yystal.parallel.repeaters.RepeaterPool;
import pisi.unitedmeows.yystal.sql.YSQLCommand;
import pisi.unitedmeows.yystal.ui.YUI;
import pisi.unitedmeows.yystal.ui.YWindow;
import pisi.unitedmeows.yystal.utils.IDisposable;
import pisi.unitedmeows.yystal.utils.Pair;
import pisi.unitedmeows.yystal.utils.Stopwatch;
import pisi.unitedmeows.yystal.utils.Types;
import static pisi.unitedmeows.yystal.YSettings.*;

import java.io.Closeable;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

public class YYStal {

    private static ITaskPool currentPool;
    private static final HashMap<Thread, Stack<Stopwatch>> stopWatchMap;
    private static final HashMap<YSettings, Object> settings;
    private static final Thread mainThread;
    private static final HashMap<String, valuelock<?>> valueLocks;

    private static final RepeaterPool repeaterPool;
    private static List<IDisposable> disposables;


    static {
        mainThread = Thread.currentThread();
        valueLocks = new HashMap<>();
        stopWatchMap = new HashMap<>();
        settings = new HashMap<>();
        setCurrentPool(new BasicTaskPool(5, 12));

        settings.put(TASKWORKER_FETCH_DELAY, 1L);
        settings.put(TASKPOOL_CONTROL_CHECK_DELAY, 3L);
        settings.put(TASK_AWAIT_DELAY, 1L);
        settings.put(TCP_CLIENT_QUEUE_CHECK_DELAY, 1L);
        settings.put(TCP_CLIENT_WRITE_DELAY, 12L);
        settings.put(TCP_SERVER_BUFFER_SENT_DELAY, 1L);
        settings.put(TCP_SERVER_BUFFER_SIZE, 32768);
        settings.put(YUI_SHADER_LOCATION, "$");

        repeaterPool = new RepeaterPool();
        disposables = new ArrayList<>();

        disposables.add(repeaterPool);
    }

    public static void setCurrentPool(ITaskPool taskPool) {
        if (currentPool != null) {
            currentPool.unregister();
        }
        currentPool = taskPool;
        currentPool.register();
    }


    public static void pop(YEx ex) {
        YExManager.pop(ex);
    }

    public static void shot(function func, function pop) {
        YExManager.shot(func, pop);
    }

    public static YEx lastEx() {
        return YExManager.lastEx();
    }

    public static void startWatcher() {
        stopWatchMap.computeIfAbsent(Thread.currentThread(), x -> new Stack<>()).push(new Stopwatch());
    }

    public static long stopWatcher() {
        Stack<Stopwatch> stopwatches = stopWatchMap.getOrDefault(Thread.currentThread(), null);
        if (stopwatches != null && !stopwatches.isEmpty()) {
            if (stopwatches.size() == 1)
                stopWatchMap.remove(Thread.currentThread());

            return stopwatches.pop().elapsed();
        }

        return 0L;
    }

    public static YSQLCommand sqlCommand(String command, Object... params) {
        YSQLCommand ysqlCommand = new YSQLCommand(command);
        for (Object param : params) {
            ysqlCommand.put(param);
        }
        return ysqlCommand;
    }

    public static <X> ref<X> reference(X initValue) {
        return new ref<X>(initValue);
    }

    public static <X> ref<X> reference() {
        return new ref<X>();
    }

    public static <F, S> Pair<F, S> pair(F first, S second) {
        return new Pair<>(first, second);
    }

    public static <X> valuelock<X> value_lock(final String name) {
        if (valueLocks.containsKey(name))
            return (valuelock<X>) valueLocks.get(name);

        valuelock<X> lock = new valuelock<>();
        valueLocks.put(name, lock);
        lock.__setup();

        return lock;
    }

    public static int sizeof(Class<?> clazz) {
        return Types.sizeof(clazz);
    }

    public static void times(int count, Runnable runnable) {
        for (int i = 0; i < count; i++) {
            runnable.run();
        }
    }

    public void setShaderLocation(String path) {
        settings.put(YUI_SHADER_LOCATION, path);
    }

    public void useDefaultShaderLocation() {
        setShaderLocation("$");
    }

    public static long took(Runnable runnable) {
        startWatcher();
        runnable.run();
        return stopWatcher();
    }

    public static YBasicTry basicTry(YExceptionRunnable _runnable) {
        return  new YBasicTry(_runnable);
    }

    public static <X extends IDisposable> void  using(X element, Consumer<X> consumer) {
        consumer.accept(element);
        element.close();
    }

    public static <X extends Closeable> boolean using(X element, Consumer<X> consumer) {
        consumer.accept(element);
        try {
            element.close();
        } catch (IOException e) {
            YExManager.pop(new YexIO("Couldn't close the closeable %s", element));
            return false;
        }

        return true;
    }

    public static Repeater repeater(int time) {
        return repeaterPool.createRepeater(time);
    }

    public static YLogger createLogger(Class<?> clazz) {
        return new YLogger(clazz);
    }

    public static YLogger createLogger(Class<?> clazz, final String name) {
        return new YLogger(clazz, name);
    }

    public static YWindow currentWindow() {
        return YUI.currentWindow();
    }

    public static <X> out<X> out() {
        return new out<X>();
    }

    public static <X> X setting(YSettings setting) {
        return (X) settings.get(setting);
    }

    public static YString string(String str) { return new YString(str); }

    public static Thread mainThread() {
        return mainThread;
    }

    public static ITaskPool taskPool() {
        return currentPool;
    }

    public static RepeaterPool repeaterPool() {
        return repeaterPool;
    }

    public static void free() {
        disposables.forEach(IDisposable::close);
        disposables.clear();
    }

}
