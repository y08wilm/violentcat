/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.exception;

import java.util.function.Consumer;
import pisi.unitedmeows.yystal.exception.YEx;
import pisi.unitedmeows.yystal.exception.YExManager;

public class YexTry {
    private Runnable runnable;
    private Consumer<YEx> catcher;
    private Runnable end;

    public YexTry(Runnable _runnable) {
        this.runnable = _runnable;
    }

    public YexTry catcher(Consumer<YEx> _catcher) {
        this.catcher = _catcher;
        return this;
    }

    public YexTry end(Runnable _end) {
        this.end = _end;
        return this;
    }

    public void run() {
        YExManager.startCaching();
        this.runnable.run();
        YEx exception = (YEx)YExManager.lastEx();
        if (exception != null) {
            this.catcher.accept(exception);
        }
        this.end.run();
    }
}

