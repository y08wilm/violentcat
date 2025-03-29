/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.violentcat.shared.holders;

public enum Status {
    PLAYING(0),
    STREAMING(1),
    LISTENING(2),
    WATCHING(3),
    COMPETING(5),
    NOTHING(2173);

    private int id;

    private Status(int _id) {
        this.id = _id;
    }

    public int id() {
        return this.id;
    }
}

