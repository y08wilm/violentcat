/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.utils;

public class Vector3<F, S, T> {
    private F first;
    private S second;
    private T third;

    public Vector3(F _first, S _second, T _third) {
        this.first = _first;
        this.second = _second;
        this.third = _third;
    }

    public F first() {
        return this.first;
    }

    public S second() {
        return this.second;
    }

    public T third() {
        return this.third;
    }
}

