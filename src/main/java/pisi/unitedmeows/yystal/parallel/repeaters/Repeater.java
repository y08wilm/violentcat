/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.parallel.repeaters;

import pisi.unitedmeows.yystal.YYStal;

public class Repeater {
    private boolean alive;
    private Runnable tick;
    private final int interval;

    @Deprecated
    public Repeater(int _interval) {
        this.interval = _interval;
    }

    public Repeater onTick(Runnable _tick) {
        this.tick = _tick;
        return this;
    }

    public Repeater alive(boolean _alive) {
        this.alive = _alive;
        return this;
    }

    @Deprecated
    public void _tick() {
        this.tick.run();
    }

    public boolean isAlive() {
        return this.alive;
    }

    public Repeater pause() {
        this.alive = false;
        YYStal.repeaterPool().unregisterRepeater(this.interval(), this);
        return this;
    }

    public Repeater resume() {
        this.alive = true;
        YYStal.repeaterPool().registerRepeater(this.interval(), this);
        return this;
    }

    public int interval() {
        return this.interval;
    }
}

