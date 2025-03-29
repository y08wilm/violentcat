/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.Expose
 *  com.google.gson.annotations.SerializedName
 */
package pisi.unitedmeows.violentcat.shared.holders.shared.message;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Author {
    @Expose
    private String id;
    @Expose
    private String username;
    @Expose
    @SerializedName(value="global_name")
    private String displayName;
    @Expose
    private String avatar;
    @Expose
    @SerializedName(value="avatar_decoration")
    private int avatarDecoration;
    @Expose
    private String discriminator;
    @Expose
    @SerializedName(value="public_flags")
    private int publicFlags;
    @Expose
    private boolean bot;

    public String toString() {
        StringBuilder sb = new StringBuilder("Author{");
        sb.append("id='").append(this.id).append('\'');
        sb.append(", username='").append(this.username).append('\'');
        sb.append(", displayName='").append(this.displayName).append('\'');
        sb.append(", avatar='").append(this.avatar).append('\'');
        sb.append(", avatarDecoration=").append(this.avatarDecoration);
        sb.append(", discriminator='").append(this.discriminator).append('\'');
        sb.append(", publicFlags=").append(this.publicFlags);
        sb.append(", bot=").append(this.bot);
        sb.append('}');
        return sb.toString();
    }

    public String id() {
        return this.id;
    }

    public String username() {
        return this.username;
    }

    public String displayName() {
        return this.displayName;
    }

    public String avatar() {
        return this.avatar;
    }

    public int avatarDecoration() {
        return this.avatarDecoration;
    }

    public String discriminator() {
        return this.discriminator;
    }

    public int publicFlags() {
        return this.publicFlags;
    }

    public boolean isBot() {
        return this.bot;
    }
}

