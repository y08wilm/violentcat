/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.Expose
 *  com.google.gson.annotations.SerializedName
 */
package pisi.unitedmeows.violentcat.shared.holders.shared.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @Expose
    protected String id;
    @Expose
    protected String username;
    @Expose
    protected String discriminator;
    @Expose
    protected String avatar;
    @Expose
    protected boolean bot;
    @Expose
    protected boolean system;
    @Expose
    @SerializedName(value="mfa_enabled")
    protected boolean mfaEnabled;
    @Expose
    protected String banner;
    @Expose
    @SerializedName(value="accent_color")
    protected String accentColor;
    @Expose
    protected String locale;
    @Expose
    protected boolean verified;
    @Expose
    protected String email;
    @Expose
    protected int flags;
    @Expose
    @SerializedName(value="premium_type")
    protected int nitroType;
    @Expose
    @SerializedName(value="public_flags")
    protected int publicFlags;

    public String id() {
        return this.id;
    }

    public String username() {
        return this.username;
    }

    public String discriminator() {
        return this.discriminator;
    }

    public String avatar() {
        return this.avatar;
    }

    public boolean isBot() {
        return this.bot;
    }

    public boolean isSystem() {
        return this.system;
    }

    public boolean isMfaEnabled() {
        return this.mfaEnabled;
    }

    public String banner() {
        return this.banner;
    }

    public String accentColor() {
        return this.accentColor;
    }

    public String locale() {
        return this.locale;
    }

    public boolean isVerified() {
        return this.verified;
    }

    public String email() {
        return this.email;
    }

    public int flags() {
        return this.flags;
    }

    public int nitroType() {
        return this.nitroType;
    }

    public int publicFlags() {
        return this.publicFlags;
    }
}

