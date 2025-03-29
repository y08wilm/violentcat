/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonObject
 *  com.google.gson.annotations.Expose
 *  com.google.gson.annotations.SerializedName
 */
package pisi.unitedmeows.violentcat.shared.holders.shared.guild;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Arrays;
import pisi.unitedmeows.violentcat.shared.action.Action;
import pisi.unitedmeows.violentcat.shared.holders.bot.ClientOwned;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.Guild;
import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;

public class GuildPreview
extends ClientOwned {
    @Expose
    private String id;
    @Expose
    private String name;
    @SerializedName(value="icon")
    @Expose
    private String iconId;
    @Expose
    private String splash;
    @SerializedName(value="discovery_splash")
    @Expose
    private String discoverySplash;
    @Expose
    private volatile String[] features;
    @SerializedName(value="approximate_member_count")
    @Expose
    private int approxMemberCount;
    @SerializedName(value="approximate_presence_count")
    @Expose
    private int approxPresenceCount;
    @Expose
    private String description;

    @Deprecated
    @OnlyLibCalls
    public void __setup(JsonObject json) {
        JsonArray array = json.get("features").getAsJsonArray();
        this.features = new String[array.size()];
        for (int i = 0; i < array.size(); ++i) {
            this.features[i] = array.get(i).getAsString();
        }
    }

    public Action<Guild> fullInfo() {
        if (this.botInstance().isBot()) {
            return this.botInstance().guild(this.id);
        }
        return null;
    }

    public String id() {
        return this.id;
    }

    public String name() {
        return this.name;
    }

    public String iconId() {
        return this.iconId;
    }

    public String splash() {
        return this.splash;
    }

    public String discoverySplash() {
        return this.discoverySplash;
    }

    public String[] features() {
        return this.features;
    }

    public int approxMemberCount() {
        return this.approxMemberCount;
    }

    public int approxPresenceCount() {
        return this.approxPresenceCount;
    }

    public String description() {
        return this.description;
    }

    public String toString() {
        return "GuildPreview{id='" + this.id + '\'' + ", name='" + this.name + '\'' + ", iconId='" + this.iconId + '\'' + ", splash='" + this.splash + '\'' + ", discoverySplash='" + this.discoverySplash + '\'' + ", features=" + Arrays.toString(this.features) + ", approxMemberCount=" + this.approxMemberCount + ", approxPresenceCount=" + this.approxPresenceCount + ", description='" + this.description + '\'' + '}';
    }
}

