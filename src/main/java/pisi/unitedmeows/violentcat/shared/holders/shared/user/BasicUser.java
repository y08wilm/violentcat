/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.Expose
 */
package pisi.unitedmeows.violentcat.shared.holders.shared.user;

import com.google.gson.annotations.Expose;

public class BasicUser {
    @Expose
    public String id;
    @Expose
    public String username;
    @Expose
    public String displayName;
    @Expose
    public String avatarId;
    @Expose
    public String avatarDecoration;
    @Expose
    public String discriminator;
    @Expose
    public boolean bot;
    @Expose
    public int publicFlags;
    @Expose
    public int flags;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAvatarId() {
        return this.avatarId;
    }

    public void setAvatarId(String avatarId) {
        this.avatarId = avatarId;
    }

    public String getAvatarDecoration() {
        return this.avatarDecoration;
    }

    public void setAvatarDecoration(String avatarDecoration) {
        this.avatarDecoration = avatarDecoration;
    }

    public String getDiscriminator() {
        return this.discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    public boolean isBot() {
        return this.bot;
    }

    public void setBot(boolean bot) {
        this.bot = bot;
    }

    public int getPublicFlags() {
        return this.publicFlags;
    }

    public void setPublicFlags(int publicFlags) {
        this.publicFlags = publicFlags;
    }

    public int getFlags() {
        return this.flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("BasicUser{");
        sb.append("id='").append(this.id).append('\'');
        sb.append(", username='").append(this.username).append('\'');
        sb.append(", displayName='").append(this.displayName).append('\'');
        sb.append(", avatarId='").append(this.avatarId).append('\'');
        sb.append(", avatarDecoration='").append(this.avatarDecoration).append('\'');
        sb.append(", discriminator='").append(this.discriminator).append('\'');
        sb.append(", publicFlags=").append(this.publicFlags);
        sb.append(", flags=").append(this.flags);
        sb.append('}');
        return sb.toString();
    }
}

