/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.violentcat.shared.holders.shared.channel.etc;

public enum VideoQuality {
    AUTO(1),
    FULL(2);

    int id;

    private VideoQuality(int _id) {
        this.id = _id;
    }

    public int id() {
        return this.id;
    }
}

