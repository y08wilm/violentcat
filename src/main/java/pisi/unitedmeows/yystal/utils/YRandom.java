/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.utils;

import java.util.Random;

public class YRandom {
    public static final YRandom BASIC = new YRandom();
    private final Random random = new Random();

    public int nextInRange(int min, int max) {
        return this.random.nextInt(max - min) + min;
    }

    public byte nextByte() {
        return (byte)this.nextInRange(-127, 127);
    }

    public byte nextByte(int min, int max) {
        return (byte)this.nextInRange(min, max);
    }

    public int nextInt() {
        return this.random.nextInt();
    }

    public int nextInt(int max) {
        return this.random.nextInt(max);
    }
}

