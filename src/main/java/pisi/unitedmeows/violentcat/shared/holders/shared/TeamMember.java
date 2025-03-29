/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonObject
 *  com.google.gson.annotations.Expose
 *  com.google.gson.annotations.SerializedName
 */
package pisi.unitedmeows.violentcat.shared.holders.shared;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Arrays;
import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;

public class TeamMember {
    @Expose
    private String id;
    @Expose
    private String username;
    @SerializedName(value="display_name")
    @Expose
    private String displayName;
    @SerializedName(value="avatar")
    @Expose
    private String avatarId;
    @SerializedName(value="avatar_decoration")
    @Expose
    private String avatarDecoration;
    @SerializedName(value="public_flags")
    @Expose
    private int publicFlags;
    @Expose
    private transient int membershipState;
    @Expose
    private transient String[] permissions;

    @Deprecated
    @OnlyLibCalls
    public void __setup(JsonObject json) {
        this.membershipState = json.get("membership_state").getAsInt();
        JsonArray permArray = json.get("permissions").getAsJsonArray();
        this.permissions = new String[permArray.size()];
        for (int i = 0; i < permArray.size(); ++i) {
            this.permissions[i] = permArray.get(i).getAsString();
        }
    }

    public String toString() {
        return "TeamMember{id='" + this.id + '\'' + ", username='" + this.username + '\'' + ", displayName='" + this.displayName + '\'' + ", avatarId='" + this.avatarId + '\'' + ", avatarDecoration='" + this.avatarDecoration + '\'' + ", publicFlags=" + this.publicFlags + ", membershipState=" + this.membershipState + ", permissions=" + Arrays.toString(this.permissions) + '}';
    }
}

