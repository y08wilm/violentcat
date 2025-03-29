/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.exception;

import pisi.unitedmeows.yystal.exception.YBasicTry;
import pisi.unitedmeows.yystal.exception.YExceptionRunnable;
import pisi.unitedmeows.yystal.exception.YexTry;

public class YTry {
    public static YBasicTry basicTry(YExceptionRunnable _runnable) {
        return new YBasicTry(_runnable);
    }

    public static YexTry create(Runnable _runnable) {
        return new YexTry(_runnable);
    }
}

