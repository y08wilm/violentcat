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

@RegisterPacket(value=PacketHeaders.CHANNEL_CREATE)
public class VChannelCreatePacket
extends VPacket {
    private int version;
    private int type;
    private String topic;
    private int rateLimitPerUser;
    private int position;
    private int permissionType;
    private String permissionId;
    private String permissionDeny;
    private String permissionAllow;
    private String parentId;
    private boolean nsfw;
    private String name;
    private String lastMessageId;
    private String id;
    private int hashVersion;
    private String roleHash;
    private String metaDataHash;
    private String channelHash;
    private String guildId;
    private int guildHashVersion;
    private String guildRoleHash;
    private String guildMetaDataHash;
    private String guildChannelHash;
    private int flags;
    private int userLimit;
    private String rtcRegion;
    private int bitrate;

    @Override
    public void decode(JsonElement _object) {
        JsonObject object = _object.getAsJsonObject();
        System.out.println("VChannelCreatePacket >>>>> " + object);
        this.version = Jsons.getInt(object.get("version"));
        this.type = Jsons.getInt(object.get("type"));
        if (this.type == 2) {
            this.userLimit = Jsons.getInt(object.get("user_limit"));
            this.rtcRegion = Jsons.getString(object.get("rtc_region"));
            this.bitrate = Jsons.getInt(object.get("bitrate"));
        } else {
            this.topic = Jsons.getString(object.get("topic"));
            this.rateLimitPerUser = Jsons.getInt(object.get("rate_limit_per_user"));
            this.position = Jsons.getInt(object.get("position"));
            JsonArray permissionOverwrites = object.getAsJsonArray("permission_overwrites");
            if (!permissionOverwrites.isEmpty()) {
                for (int i = 0; i < permissionOverwrites.size(); ++i) {
                    this.permissionType = Jsons.getInt(permissionOverwrites.get(i).getAsJsonObject().get("type"));
                    this.permissionId = Jsons.getString(permissionOverwrites.get(i).getAsJsonObject().get("id"));
                    this.permissionDeny = Jsons.getString(permissionOverwrites.get(i).getAsJsonObject().get("deny"));
                    this.permissionAllow = Jsons.getString(permissionOverwrites.get(i).getAsJsonObject().get("allow"));
                }
            }
            this.parentId = Jsons.getString(object.get("parent_id"));
            this.nsfw = Jsons.getBoolean(object.get("nsfw"));
            this.name = Jsons.getString(object.get("name"));
            this.lastMessageId = Jsons.getString(object.get("last_message_id"));
            this.id = Jsons.getString(object.get("id"));
            JsonObject hashes = object.getAsJsonObject("hashes");
            this.hashVersion = Jsons.getInt(hashes.get("version"));
            JsonObject roles = hashes.getAsJsonObject("roles");
            this.roleHash = Jsons.getString(roles.get("hash"));
            JsonObject metadata = hashes.getAsJsonObject("metadata");
            this.metaDataHash = Jsons.getString(metadata.get("hash"));
            JsonObject channels = hashes.getAsJsonObject("channels");
            this.channelHash = Jsons.getString(channels.get("hash"));
            this.guildId = Jsons.getString(object.get("guild_id"));
            JsonObject guildHashes = object.getAsJsonObject("guild_hashes");
            this.guildHashVersion = Jsons.getInt(guildHashes.get("version"));
            JsonObject guildRoles = guildHashes.getAsJsonObject("roles");
            this.guildRoleHash = Jsons.getString(guildRoles.get("hash"));
            JsonObject guildMetadata = guildHashes.getAsJsonObject("metadata");
            this.guildMetaDataHash = Jsons.getString(guildMetadata.get("hash"));
            JsonObject guildChannels = guildHashes.getAsJsonObject("channels");
            this.guildChannelHash = Jsons.getString(guildChannels.get("hash"));
            this.flags = Jsons.getInt(object.get("flags"));
        }
    }

    public JsonObject encode() {
        return new JsonObject();
    }
}

