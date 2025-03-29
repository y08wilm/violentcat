/*
 * Decompiled with CFR 0.152.
 */
package stelix.xfile;

public class SxfElementPack<X, O> {
    private final X element;
    private final O owner;
    private String comment;

    public SxfElementPack(X _element, O _owner) {
        this.element = _element;
        this.owner = _owner;
    }

    public SxfElementPack<X, O> comment(String _comment) {
        this.comment = _comment;
        return this;
    }

    public String comment() {
        return this.comment;
    }

    public X down() {
        return this.element;
    }

    public O build() {
        return this.owner;
    }
}

