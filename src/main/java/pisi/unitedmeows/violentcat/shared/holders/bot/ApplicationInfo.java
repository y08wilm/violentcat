/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.annotations.Expose
 *  com.google.gson.annotations.SerializedName
 */
package pisi.unitedmeows.violentcat.shared.holders.bot;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Arrays;
import pisi.unitedmeows.violentcat.shared.holders.shared.DiscordTeam;
import pisi.unitedmeows.violentcat.shared.holders.shared.user.BasicUser;
import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;
import pisi.unitedmeows.violentcat.utils.GsonWrap;

public class ApplicationInfo {
    @Expose
    private String id;
    @Expose
    private String name;
    @Expose
    private String icon;
    @Expose
    private String description;
    @Expose
    private String summary;
    @Expose
    private int type;
    @Expose
    private boolean hook;
    @SerializedName(value="bot_public")
    @Expose
    private boolean botPublic;
    @Expose
    private String[] scopes;
    @SerializedName(value="install_permission")
    @Expose
    private String installPermission;
    @Expose
    private String verifyKey;
    @Expose
    private BasicUser owner;
    @Expose
    private String flags;
    @Expose
    private transient DiscordTeam team;

    @Deprecated
    @OnlyLibCalls
    public void __setup(JsonObject json) {
        JsonObject teamObject = json.get("team").getAsJsonObject();
        this.team = (DiscordTeam)GsonWrap.parse(DiscordTeam.class, teamObject).result();
        this.team.__setup(teamObject);
    }

    public String toString() {
        return "ApplicationInfo{id='" + this.id + '\'' + ", name='" + this.name + '\'' + ", icon='" + this.icon + '\'' + ", description='" + this.description + '\'' + ", summary='" + this.summary + '\'' + ", type=" + this.type + ", hook=" + this.hook + ", botPublic=" + this.botPublic + ", scopes=" + Arrays.toString(this.scopes) + ", installPermission='" + this.installPermission + '\'' + ", verifyKey='" + this.verifyKey + '\'' + ", owner=" + this.owner + ", flags='" + this.flags + '\'' + ", team=" + this.team + '}';
    }
}

