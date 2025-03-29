/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.utils;

public class Vector2<F, S> {
    private F first;
    private S second;

    public Vector2(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F setX(F val) {
        this.first = val;
        return this.first;
    }

    public F getX() {
        return this.first;
    }

    public S getY() {
        return this.second;
    }

    public S setY(S val) {
        this.second = val;
        return this.second;
    }
}

