/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.clazz;

public class prop<X> {
    protected X value;

    public prop() {
    }

    public prop(X _value) {
        this.value = _value;
    }

    public void set(X newValue) {
        this.value = newValue;
    }

    public X get() {
        return this.value;
    }
}

