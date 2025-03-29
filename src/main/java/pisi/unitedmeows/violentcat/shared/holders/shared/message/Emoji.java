/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.Expose
 */
package pisi.unitedmeows.violentcat.shared.holders.shared.message;

import com.google.gson.annotations.Expose;
import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;

public class Emoji {
    @Expose
    private String name;

    @Deprecated
    @OnlyLibCalls
    public Emoji() {
    }

    public Emoji(String _name) {
        this.name = _name;
    }

    public String name() {
        return this.name;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Emoji{");
        sb.append("name='").append(this.name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

