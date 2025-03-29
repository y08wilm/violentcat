/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.exception;

import java.util.function.Consumer;
import pisi.unitedmeows.yystal.exception.YExceptionRunnable;

public class YBasicTry {
    private YExceptionRunnable runnable;
    private Consumer<Exception> catcher;
    private Runnable end;

    public YBasicTry(YExceptionRunnable _runnable) {
        this.runnable = _runnable;
    }

    public YBasicTry catcher(Consumer<Exception> _catcher) {
        this.catcher = _catcher;
        return this;
    }

    public YBasicTry end(Runnable _end) {
        this.end = _end;
        return this;
    }

    public void run() {
        try {
            this.runnable.run();
        }
        catch (Exception ex) {
            if (this.catcher != null) {
                this.catcher.accept(ex);
            }
        }
        finally {
            if (this.end != null) {
                this.end.run();
            }
        }
    }
}

