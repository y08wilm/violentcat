/*
 * Decompiled with CFR 0.152.
 */
package stelix.xfile;

public class Variable {
    private final String name;
    private final Object value;

    public Variable(String _name, Object _value) {
        this.name = _name;
        this.value = _value;
    }

    public String name() {
        return this.name;
    }

    public Object value() {
        return this.value;
    }
}

