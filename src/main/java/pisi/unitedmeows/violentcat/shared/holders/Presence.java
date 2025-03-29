/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.violentcat.shared.holders;

import pisi.unitedmeows.violentcat.shared.holders.Availability;
import pisi.unitedmeows.violentcat.shared.holders.Status;

public class Presence {
    private boolean afk;
    private Availability availability;
    private int type;
    private String statusMessage;
    private String special;

    public Presence(Availability _availability, Status status, String _statusMessage, String _special) {
        this.availability = _availability;
        this.statusMessage = _statusMessage;
        this.type = status.id();
        this.special = _special;
    }

    public Presence(Availability _availability, Status status, String _statusMessage) {
        this(_availability, status, _statusMessage, "");
    }

    public Presence afk(boolean _afk) {
        this.afk = this.afk;
        return this;
    }

    public boolean afk() {
        return this.afk;
    }

    public Availability availability() {
        return this.availability;
    }

    public String statusMessage() {
        return this.statusMessage;
    }

    public String special() {
        return this.special;
    }

    public int type() {
        return this.type;
    }
}

