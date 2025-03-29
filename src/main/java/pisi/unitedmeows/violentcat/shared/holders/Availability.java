/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.violentcat.shared.holders;

public enum Availability {
    ONLINE("online"),
    IDLE("idle"),
    DO_NOT_DISTURB("dnd"),
    INVISIBLE("invisible"),
    OFFLINE("offline");

    private String code;

    private Availability(String _code) {
        this.code = _code;
    }

    public String code() {
        return this.code;
    }
}

