/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.Expose
 */
package pisi.unitedmeows.violentcat.shared.holders.shared;

import com.google.gson.annotations.Expose;

public class Role {
    @Expose
    private String id;
    @Expose
    private String name;
    @Expose
    private String permissions;
    @Expose
    private int position;
    @Expose
    private int color;
    @Expose
    private boolean hoist;
    @Expose
    private boolean managed;
    @Expose
    private boolean mentionable;

    public String toString() {
        return "Role{id='" + this.id + '\'' + ", name='" + this.name + '\'' + ", permissions='" + this.permissions + '\'' + ", position=" + this.position + ", color=" + this.color + ", hoist=" + this.hoist + ", managed=" + this.managed + ", mentionable=" + this.mentionable + '}';
    }
}

