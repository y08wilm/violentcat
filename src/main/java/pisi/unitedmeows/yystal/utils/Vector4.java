/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.utils;

public class Vector4<F, S, T, Q> {
    private F first;
    private S second;
    private T third;
    private Q fourth;

    public Vector4(F _first, S _second, T _third, Q _fourth) {
        this.first = _first;
        this.second = _second;
        this.third = _third;
        this.fourth = _fourth;
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

    public Q fourth() {
        return this.fourth;
    }
}

