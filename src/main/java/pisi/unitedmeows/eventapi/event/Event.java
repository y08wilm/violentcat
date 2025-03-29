/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.eventapi.event;

public class Event {
    private boolean canceled;
    private boolean stopped;

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public boolean isCanceled() {
        return this.canceled;
    }

    public void stop() {
        this.stopped = true;
    }

    public boolean isStopped() {
        return this.stopped;
    }

    public static enum Weight {
        MASTER(10),
        HIGHEST(5),
        MEDIUM(3),
        LOW(2),
        LOWEST(1),
        SLAVE(-9),
        MONITOR(-10);

        private int weight;

        private Weight(int _weight) {
            this.weight = _weight;
        }

        public int value() {
            return this.weight;
        }
    }

    public static enum Time {
        BEFORE,
        ON,
        AFTER;

    }
}

