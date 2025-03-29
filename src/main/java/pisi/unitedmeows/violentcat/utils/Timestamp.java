/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.violentcat.utils;

import java.util.Date;

public class Timestamp {
    private long time;
    private Type type = Type.RELATIVE;

    protected Timestamp(long _time) {
        this.time = _time / 1000L;
    }

    protected Timestamp(long _time, Type _type) {
        this(_time);
        this.type = _type;
    }

    protected Timestamp(Date _date) {
        this(_date.getTime());
    }

    protected Timestamp(Date _date, Type _type) {
        this(_date);
        this.type = _type;
    }

    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Timestamp from(long _time, Type _type) {
        return new Timestamp(_time, _type);
    }

    public static Timestamp from(Date _date) {
        return new Timestamp(_date);
    }

    public static Timestamp from(Date _date, Type _type) {
        return new Timestamp(_date, _type);
    }

    public static Timestamp from(long _time) {
        return new Timestamp(_time);
    }

    public Timestamp addMilliseconds(long ms) {
        this.addSeconds(ms / 1000L);
        return this;
    }

    public Timestamp addSeconds(long seconds) {
        this.time += seconds;
        return this;
    }

    public Timestamp addDays(long days) {
        this.addSeconds(days * 24L * 60L * 60L);
        return this;
    }

    public Timestamp addYear(long year) {
        this.addDays(year * 365L);
        return this;
    }

    protected Timestamp type(Type _type) {
        this.type = _type;
        return this;
    }

    public String toString() {
        return "<t:" + this.time + ":" + this.type().code + ">";
    }

    public Type type() {
        return this.type;
    }

    public static enum Type {
        SHORT_TIME("t"),
        SHORT_DATE("d"),
        LONG_TIME("T"),
        LONG_DATE("D"),
        LONG_DATETIME("f"),
        LONG_FULL("F"),
        RELATIVE("R");

        String code;

        private Type(String _code) {
            this.code = _code;
        }

        public String code() {
            return this.code;
        }
    }
}

