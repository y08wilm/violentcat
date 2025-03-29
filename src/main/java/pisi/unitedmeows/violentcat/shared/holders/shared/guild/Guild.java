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
import pisi.unitedmeows.violentcat.shared.action.Action;
import pisi.unitedmeows.violentcat.shared.holders.bot.ClientOwned;
import pisi.unitedmeows.violentcat.shared.holders.shared.Role;
import pisi.unitedmeows.violentcat.shared.holders.shared.channel.Channel;
import pisi.unitedmeows.violentcat.shared.holders.shared.channel.ChannelBuilder;
import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;

public class Guild
extends ClientOwned {
    @Expose
    protected String id;
    @Expose
    protected String name;
    @Expose
    protected String icon;
    @Expose
    protected String description;
    @Expose
    protected String splash;
    @SerializedName(value="discovery_splash")
    @Expose
    protected String discoverySplash;
    @Expose
    protected String[] features;
    @Expose
    protected String banner;
    @SerializedName(value="owner_id")
    @Expose
    protected String ownerId;
    @SerializedName(value="application_id")
    @Expose
    protected String applicationId;
    @Expose
    protected String region;
    @SerializedName(value="afk_channel_id")
    @Expose
    protected String afkChannelId;
    @SerializedName(value="afk_timeout")
    @Expose
    protected int afkTimeout;
    @SerializedName(value="system_channel_id")
    @Expose
    protected String systemChannelId;
    @SerializedName(value="widget_enabled")
    @Expose
    protected boolean widgetEnabled;
    @SerializedName(value="widget_channel_id")
    @Expose
    protected String widgetChannelId;
    @SerializedName(value="verification_level")
    @Expose
    protected int verificationLevel;
    @Expose
    protected List<Role> roles;
    @SerializedName(value="default_message_notifications")
    @Expose
    protected int defaultMessageNotifications;
    @SerializedName(value="mfa_level")
    @Expose
    protected int mfaLevel;
    @SerializedName(value="explicit_content_filter")
    @Expose
    protected int explicitContentFilter;
    @SerializedName(value="max_presences")
    @Expose
    protected int maxPresences;
    @SerializedName(value="max_members")
    @Expose
    protected int maxMembers;
    @SerializedName(value="max_video_channel_users")
    @Expose
    protected int maxVideoChannelUsers;
    @SerializedName(value="vanity_url_code")
    @Expose
    protected String vanityUrlCode;
    @SerializedName(value="premium_tier")
    @Expose
    protected int premiumTier;
    @SerializedName(value="premium_subscription_count")
    @Expose
    protected int premiumSubCount;
    @SerializedName(value="system_channel_flags")
    @Expose
    protected int systemChannelFlags;
    @SerializedName(value="preferred_locale")
    @Expose
    protected String preferredLocale;
    @SerializedName(value="rules_channel_id")
    @Expose
    protected String rulesChannelId;
    @SerializedName(value="public_updates_channel_id")
    @Expose
    protected String publicUpdatesChannelId;
    @SerializedName(value="approximate_member_count")
    @Expose
    protected int approxMemberCount;
    @SerializedName(value="approximate_presence_count")
    @Expose
    protected int approxPresenceCount;

    public Action<List<Channel>> channels() {
        if (this.botInstance().isBot()) {
            return this.botInstance().guildChannels(this.id);
        }
        return null;
    }

    @Deprecated
    @OnlyLibCalls
    public void __setup(JsonObject object) {
    }

    public String id() {
        return this.id;
    }

    public Action<Channel> createChannel(ChannelBuilder channelBuilder) {
        if (this.owner().isBot()) {
            return this.botInstance().createChannel(this.id, channelBuilder);
        }
        return null;
    }

    public String toString() {
        return "Guild{id='" + this.id + '\'' + ", name='" + this.name + '\'' + ", icon='" + this.icon + '\'' + ", description='" + this.description + '\'' + ", splash='" + this.splash + '\'' + ", discoverySplash='" + this.discoverySplash + '\'' + ", features=" + Arrays.toString(this.features) + ", banner='" + this.banner + '\'' + ", ownerId='" + this.ownerId + '\'' + ", applicationId='" + this.applicationId + '\'' + ", region='" + this.region + '\'' + ", afkChannelId='" + this.afkChannelId + '\'' + ", afkTimeout=" + this.afkTimeout + ", systemChannelId='" + this.systemChannelId + '\'' + ", widgetEnabled=" + this.widgetEnabled + ", widgetChannelId='" + this.widgetChannelId + '\'' + ", verificationLevel=" + this.verificationLevel + ", roles=" + this.roles + ", defaultMessageNotifications=" + this.defaultMessageNotifications + ", mfaLevel=" + this.mfaLevel + ", explicitContentFilter=" + this.explicitContentFilter + ", maxPresences=" + this.maxPresences + ", maxMembers=" + this.maxMembers + ", maxVideoChannelUsers=" + this.maxVideoChannelUsers + ", vanityUrlCode='" + this.vanityUrlCode + '\'' + ", premiumTier=" + this.premiumTier + ", premiumSubCount=" + this.premiumSubCount + ", systemChannelFlags=" + this.systemChannelFlags + ", preferredLocale='" + this.preferredLocale + '\'' + ", rulesChannelId='" + this.rulesChannelId + '\'' + ", publicUpdatesChannelId='" + this.publicUpdatesChannelId + '\'' + ", approxMemberCount=" + this.approxMemberCount + ", approxPresenceCount=" + this.approxPresenceCount + '}';
    }
}

