/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.annotations.Expose
 *  com.google.gson.annotations.SerializedName
 */
package pisi.unitedmeows.violentcat.shared.holders.shared.guild;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Arrays;
import java.util.List;
import pisi.unitedmeows.violentcat.shared.holders.shared.channel.BasicChannel;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.Guild;
import pisi.unitedmeows.violentcat.shared.holders.shared.user.GuildMember;
import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;

public class DetailedGuild
extends Guild {
    @Expose
    @SerializedName(value="joined_at")
    protected String joinedAt;
    @Expose
    @SerializedName(value="member_count")
    protected int memberCount;
    @Expose
    protected boolean nsfw;
    @Expose
    protected boolean lazy;
    @Expose
    @SerializedName(value="max_stage_video_channel_users")
    private int maxStageVideoChannelUsers;
    @Expose
    protected boolean large;
    @Expose
    @SerializedName(value="nsfw_level")
    protected int nsfwLevel;
    @Expose
    List<BasicChannel> channels;
    @Expose
    @SerializedName(value="safety_alerts_channel_id")
    protected String safetyAlertsChannelId;
    @Expose
    protected List<GuildMember> members;

    @Override
    @Deprecated
    @OnlyLibCalls
    public void __setup(JsonObject object) {
        super.__setup(object);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("DetailedGuild{");
        sb.append("joinedAt='").append(this.joinedAt).append('\'');
        sb.append(", memberCount=").append(this.memberCount);
        sb.append(", nsfw=").append(this.nsfw);
        sb.append(", lazy=").append(this.lazy);
        sb.append(", maxStageVideoChannelUsers=").append(this.maxStageVideoChannelUsers);
        sb.append(", large=").append(this.large);
        sb.append(", nsfwLevel=").append(this.nsfwLevel);
        sb.append(", channels=").append(this.channels);
        sb.append(", safetyAlertsChannelId='").append(this.safetyAlertsChannelId).append('\'');
        sb.append(", members=").append(this.members);
        sb.append(", id='").append(this.id).append('\'');
        sb.append(", name='").append(this.name).append('\'');
        sb.append(", icon='").append(this.icon).append('\'');
        sb.append(", description='").append(this.description).append('\'');
        sb.append(", splash='").append(this.splash).append('\'');
        sb.append(", discoverySplash='").append(this.discoverySplash).append('\'');
        sb.append(", features=").append(Arrays.toString(this.features));
        sb.append(", banner='").append(this.banner).append('\'');
        sb.append(", ownerId='").append(this.ownerId).append('\'');
        sb.append(", applicationId='").append(this.applicationId).append('\'');
        sb.append(", region='").append(this.region).append('\'');
        sb.append(", afkChannelId='").append(this.afkChannelId).append('\'');
        sb.append(", afkTimeout=").append(this.afkTimeout);
        sb.append(", systemChannelId='").append(this.systemChannelId).append('\'');
        sb.append(", widgetEnabled=").append(this.widgetEnabled);
        sb.append(", widgetChannelId='").append(this.widgetChannelId).append('\'');
        sb.append(", verificationLevel=").append(this.verificationLevel);
        sb.append(", roles=").append(this.roles);
        sb.append(", defaultMessageNotifications=").append(this.defaultMessageNotifications);
        sb.append(", mfaLevel=").append(this.mfaLevel);
        sb.append(", explicitContentFilter=").append(this.explicitContentFilter);
        sb.append(", maxPresences=").append(this.maxPresences);
        sb.append(", maxMembers=").append(this.maxMembers);
        sb.append(", maxVideoChannelUsers=").append(this.maxVideoChannelUsers);
        sb.append(", vanityUrlCode='").append(this.vanityUrlCode).append('\'');
        sb.append(", premiumTier=").append(this.premiumTier);
        sb.append(", premiumSubCount=").append(this.premiumSubCount);
        sb.append(", systemChannelFlags=").append(this.systemChannelFlags);
        sb.append(", preferredLocale='").append(this.preferredLocale).append('\'');
        sb.append(", rulesChannelId='").append(this.rulesChannelId).append('\'');
        sb.append(", publicUpdatesChannelId='").append(this.publicUpdatesChannelId).append('\'');
        sb.append(", approxMemberCount=").append(this.approxMemberCount);
        sb.append(", approxPresenceCount=").append(this.approxPresenceCount);
        sb.append('}');
        return sb.toString();
    }
}

