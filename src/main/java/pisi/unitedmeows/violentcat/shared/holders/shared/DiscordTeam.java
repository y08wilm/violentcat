/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonObject
 *  com.google.gson.annotations.SerializedName
 */
package pisi.unitedmeows.violentcat.shared.holders.shared;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import pisi.unitedmeows.violentcat.shared.holders.shared.TeamMember;
import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;
import pisi.unitedmeows.violentcat.utils.GsonWrap;

public class DiscordTeam {
    private String id;
    private String icon;
    private String name;
    @SerializedName(value="owner_user_id")
    private String ownerId;
    private transient List<TeamMember> members = new ArrayList<TeamMember>();

    @Deprecated
    @OnlyLibCalls
    public void __setup(JsonObject json) {
        JsonArray membersArray = json.get("members").getAsJsonArray();
        for (int i = 0; i < membersArray.size(); ++i) {
            JsonObject userWrap = membersArray.get(i).getAsJsonObject();
            JsonObject user = userWrap.get("user").getAsJsonObject();
            TeamMember member = (TeamMember)GsonWrap.parse(TeamMember.class, user).result();
            member.__setup(userWrap);
            this.members.add(member);
        }
    }

    public String toString() {
        return "DiscordTeam{id='" + this.id + '\'' + ", icon='" + this.icon + '\'' + ", name='" + this.name + '\'' + ", ownerId='" + this.ownerId + '\'' + ", members=" + this.members + '}';
    }
}

