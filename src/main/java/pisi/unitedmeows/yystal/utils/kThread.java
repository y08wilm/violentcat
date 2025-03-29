/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.utils;

import pisi.unitedmeows.yystal.parallel.IState;

public class kThread {
    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        }
        catch (InterruptedException interruptedException) {
            // empty catch block
        }
    }

    public static void sleep_till(IState state) {
        while (state.check()) {
            kThread.sleep(1L);
        }
    }

    public static void sleep_untill(IState state) {
        while (!state.check()) {
            kThread.sleep(1L);
        }
    }
}

