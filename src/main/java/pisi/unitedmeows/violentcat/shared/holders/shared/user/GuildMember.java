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
import java.util.List;
import pisi.unitedmeows.violentcat.shared.holders.shared.user.BasicUser;

public class GuildMember {
    @Expose
    protected BasicUser user;
    @Expose
    protected List<String> roles;
    @Expose
    @SerializedName(value="premium_since")
    private boolean premiumSince;
    @Expose
    protected boolean pending;
    @Expose
    protected String nick;
    @Expose
    @SerializedName(value="joined_at")
    protected String joinedAt;
    @Expose
    protected boolean deaf;
    @Expose
    @SerializedName(value="communication_disabled_until")
    protected String communicationDisabledUntil;
    @Expose
    private String avatar;

    public String toString() {
        StringBuilder sb = new StringBuilder("GuildMember{");
        sb.append("user=").append(this.user);
        sb.append(", roles=").append(this.roles);
        sb.append(", premiumSince=").append(this.premiumSince);
        sb.append(", pending=").append(this.pending);
        sb.append(", nick='").append(this.nick).append('\'');
        sb.append(", joinedAt='").append(this.joinedAt).append('\'');
        sb.append(", deaf=").append(this.deaf);
        sb.append(", communicationDisabledUntil='").append(this.communicationDisabledUntil).append('\'');
        sb.append(", avatar='").append(this.avatar).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

