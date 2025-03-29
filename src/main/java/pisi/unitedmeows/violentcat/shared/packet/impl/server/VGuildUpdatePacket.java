/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package pisi.unitedmeows.violentcat.shared.packet.impl.server;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.shared.packet.PacketHeaders;
import pisi.unitedmeows.violentcat.shared.packet.VPacket;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterPacket;
import pisi.unitedmeows.violentcat.utils.Jsons;

@RegisterPacket(value=PacketHeaders.GUILD_UPDATE)
public class VGuildUpdatePacket
extends VPacket {
    private String id;
    private int verificationLevel;
    private String preferredLocale;
    private String afkChannelId;
    private String vanityUrlCode;
    private String splash;
    private String icon;
    private boolean widget;
    private String rulesChannelId;
    private int premiumTier;
    private String guildId;
    private boolean nsfw;
    private int systemChannelFlags;
    private String systemChannelId;
    private int version;
    private int maxMembers;
    private int afkTimeout;
    private String applicationId;
    private String hubType;
    private String latestOnboardingQuestionId;
    private String banner;
    private int nsfwLevel;
    private int maxStageVideoChannelUsers;
    private int maxVideoChannelUsers;
    private int premiumSubscriptionCount;
    private String region;
    private boolean premiumProgressBar;
    private int hashVersion;
    private String roleHash;
    private String metaDataHash;
    private String channelHash;
    private int guildHashVersion;
    private String guildRoleHash;
    private String guildMetaDataHash;
    private String guildChannelHash;
    private String safetyAlertsChannelId;
    private String description;
    private int roleVersion;
    private String roleUnicodeEmoji;
    private String rolePosition;
    private String rolePermission;
    private String roleName;
    private boolean roleMentionable;
    private boolean roleManaged;
    private String roleId;
    private String roleIcon;
    private boolean roleHoist;
    private int roleFlags;
    private String roleDescription;
    private int roleColor;
    private String ownerId;
    private int emojiVersion;
    private boolean emojiRequireColons;
    private String emojiName;
    private boolean emojiManaged;
    private String emojiId;
    private boolean emojiAvailable;
    private boolean emojiAnimated;
    private String publicUpdatesChannelId;
    private String widgetChannelId;
    private String homeHeader;
    private String discoverySplash;
    private int explicitContentFilter;
    private int defaultMessageNotifications;
    private int mfaLevel;
    private String name;
    private int maxPresences;

    @Override
    public void decode(JsonElement _object) {
        JsonObject object = _object.getAsJsonObject();
        System.out.println("VGuildUpdatePacket >>>>> " + object);
        this.id = Jsons.getString(object.get("id"));
        this.verificationLevel = Jsons.getInt(object.get("verification_level"));
        this.preferredLocale = Jsons.getString(object.get("preferred_locale"));
        this.afkChannelId = Jsons.getString(object.get("afk_channel_id"));
        this.vanityUrlCode = Jsons.getString(object.get("vanity_url_code"));
        this.splash = Jsons.getString(object.get("splash"));
        this.icon = Jsons.getString(object.get("icon"));
        this.widget = Jsons.getBoolean(object.get("widget_enabled"));
        this.rulesChannelId = Jsons.getString(object.get("rules_channel_id"));
        this.premiumTier = Jsons.getInt(object.get("premium_tier"));
        this.guildId = Jsons.getString(object.get("guild_id"));
        this.nsfw = Jsons.getBoolean(object.get("nsfw"));
        this.systemChannelFlags = Jsons.getInt(object.get("system_channel_flags"));
        this.systemChannelId = Jsons.getString(object.get("system_channel_id"));
        this.version = Jsons.getInt(object.get("version"));
        this.maxMembers = Jsons.getInt(object.get("max_members"));
        this.afkTimeout = Jsons.getInt(object.get("afk_timeout"));
        this.applicationId = Jsons.getString(object.get("application_id"));
        this.hubType = Jsons.getString(object.get("hub_type"));
        this.latestOnboardingQuestionId = Jsons.getString(object.get("latest_onboarding_question_id"));
        this.banner = Jsons.getString(object.get("banner"));
        this.nsfwLevel = Jsons.getInt(object.get("nsfw_level"));
        this.maxStageVideoChannelUsers = Jsons.getInt(object.get("max_stage_video_channel_users"));
        this.maxVideoChannelUsers = Jsons.getInt(object.get("max_video_channel_users"));
        this.premiumSubscriptionCount = Jsons.getInt(object.get("premium_subscription_count"));
        this.region = Jsons.getString(object.get("region"));
        JsonArray features = object.getAsJsonArray("features");
        this.premiumProgressBar = Jsons.getBoolean(object.get("premium_progress_bar_enabled"));
        JsonObject hashes = object.getAsJsonObject("hashes");
        this.hashVersion = Jsons.getInt(hashes.get("version"));
        JsonObject roles = hashes.getAsJsonObject("roles");
        this.roleHash = Jsons.getString(roles.get("hash"));
        JsonObject metadata = hashes.getAsJsonObject("metadata");
        this.metaDataHash = Jsons.getString(metadata.get("hash"));
        JsonObject channels = hashes.getAsJsonObject("channels");
        this.channelHash = Jsons.getString(channels.get("hash"));
        JsonObject guildHashes = object.getAsJsonObject("guild_hashes");
        this.guildHashVersion = Jsons.getInt(guildHashes.get("version"));
        JsonObject guildRoles = guildHashes.getAsJsonObject("roles");
        this.guildRoleHash = Jsons.getString(guildRoles.get("hash"));
        JsonObject guildMetadata = guildHashes.getAsJsonObject("metadata");
        this.guildMetaDataHash = Jsons.getString(guildMetadata.get("hash"));
        JsonObject guildChannels = guildHashes.getAsJsonObject("channels");
        this.guildChannelHash = Jsons.getString(guildChannels.get("hash"));
        this.safetyAlertsChannelId = Jsons.getString(object.get("safety_alerts_channel_id"));
        this.description = Jsons.getString(object.get("description"));
        JsonArray rolesArray = object.getAsJsonArray("roles");
        if (!rolesArray.isEmpty()) {
            for (int i = 0; i < rolesArray.size(); ++i) {
                JsonObject rolesObject = rolesArray.get(i).getAsJsonObject();
                this.roleVersion = Jsons.getInt(rolesObject.get("version"));
                this.roleUnicodeEmoji = Jsons.getString(rolesObject.get("unicode_emoji"));
                this.rolePosition = Jsons.getString(rolesObject.get("position"));
                this.rolePermission = Jsons.getString(rolesObject.get("permissions"));
                this.roleName = Jsons.getString(rolesObject.get("name"));
                this.roleMentionable = Jsons.getBoolean(rolesObject.get("mentionable"));
                this.roleManaged = Jsons.getBoolean(rolesObject.get("managed"));
                this.roleId = Jsons.getString(rolesObject.get("id"));
                this.roleIcon = Jsons.getString(rolesObject.get("icon"));
                this.roleHoist = Jsons.getBoolean(rolesObject.get("hoist"));
                this.roleFlags = Jsons.getInt(rolesObject.get("flags"));
                this.roleDescription = Jsons.getString(rolesObject.get("description"));
                this.roleColor = Jsons.getInt(rolesObject.get("color"));
            }
        }
        this.ownerId = Jsons.getString(object.get("owner_id"));
        JsonArray emojis = object.getAsJsonArray("emojis");
        if (!emojis.isEmpty()) {
            for (int i = 0; i < emojis.size(); ++i) {
                JsonObject emoji = emojis.get(i).getAsJsonObject();
                this.emojiVersion = Jsons.getInt(emoji.get("version"));
                JsonArray emojiRoles = emoji.getAsJsonArray("roles");
                this.emojiRequireColons = Jsons.getBoolean(emoji.get("require_colons"));
                this.emojiName = Jsons.getString(emoji.get("name"));
                this.emojiManaged = Jsons.getBoolean(emoji.get("managed"));
                this.emojiId = Jsons.getString(emoji.get("id"));
                this.emojiAvailable = Jsons.getBoolean(emoji.get("available"));
                this.emojiAnimated = Jsons.getBoolean(emoji.get("animated"));
            }
        }
        this.publicUpdatesChannelId = Jsons.getString(object.get("public_updates_channel_id"));
        this.widgetChannelId = Jsons.getString(object.get("widget_channel_id"));
        this.homeHeader = Jsons.getString(object.get("home_header"));
        JsonArray stickers = object.getAsJsonArray("stickers");
        if (!stickers.isEmpty()) {
            for (int i = 0; i < stickers.size(); ++i) {
            }
        }
        this.discoverySplash = Jsons.getString(object.get("discovery_splash"));
        this.explicitContentFilter = Jsons.getInt(object.get("explicit_content_filter"));
        this.defaultMessageNotifications = Jsons.getInt(object.get("default_message_notifications"));
        this.mfaLevel = Jsons.getInt(object.get("mfa_level"));
        this.name = Jsons.getString(object.get("name"));
        this.maxPresences = Jsons.getInt(object.get("max_presences"));
    }

    public JsonObject encode() {
        return new JsonObject();
    }
}

