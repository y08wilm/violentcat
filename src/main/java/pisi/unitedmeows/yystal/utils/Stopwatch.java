/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.utils;

public class Stopwatch {
    private long ms;

    public Stopwatch() {
        this.reset();
    }

    public boolean isReached(long milliseconds) {
        return this.getTime() - this.ms >= milliseconds;
    }

    private long getTime() {
        return System.nanoTime() / 1000000L;
    }

    public void reset() {
        this.ms = this.getTime();
    }

    public Stopwatch resetBack(long time) {
        this.ms = this.getTime() - time;
        return this;
    }

    public long elapsed() {
        return this.getTime() - this.ms;
    }
}

