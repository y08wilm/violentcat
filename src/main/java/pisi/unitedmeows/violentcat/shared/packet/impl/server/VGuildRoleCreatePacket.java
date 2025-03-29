/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package pisi.unitedmeows.violentcat.shared.packet.impl.server;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.shared.packet.PacketHeaders;
import pisi.unitedmeows.violentcat.shared.packet.VPacket;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterPacket;
import pisi.unitedmeows.violentcat.utils.Jsons;

@RegisterPacket(value=PacketHeaders.GUILD_ROLE_CREATE)
public class VGuildRoleCreatePacket
extends VPacket {
    private int version;
    private String unicodeEmoji;
    private int position;
    private String permissions;
    private String name;
    private boolean mentionable;
    private boolean managed;
    private String id;
    private String icon;
    private boolean hoist;
    private int flags;
    private String description;
    private int color;
    private int hashVersion;
    private String roleHash;
    private String metaDataHash;
    private String channelHash;
    private String guildId;
    private int guildHashVersion;
    private String guildRoleHash;
    private String guildMetaDataHash;
    private String guildChannelHash;

    @Override
    public void decode(JsonElement _object) {
        JsonObject object = _object.getAsJsonObject();
        System.out.println("VGuildRoleCreate >>>>> " + object);
        JsonObject role = object.getAsJsonObject("role");
        this.version = Jsons.getInt(role.get("version"));
        this.unicodeEmoji = Jsons.getString(role.get("unicode_emoji"));
        this.position = Jsons.getInt(role.get("position"));
        this.permissions = Jsons.getString(role.get("permissions"));
        this.name = Jsons.getString(role.get("name"));
        this.mentionable = Jsons.getBoolean(role.get("mentionable"));
        this.managed = Jsons.getBoolean(role.get("managed"));
        this.id = Jsons.getString(role.get("id"));
        this.icon = Jsons.getString(role.get("icon"));
        this.hoist = Jsons.getBoolean(role.get("hoist"));
        this.flags = Jsons.getInt(role.get("flags"));
        this.description = Jsons.getString(role.get("description"));
        this.color = Jsons.getInt(role.get("color"));
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
    }

    public JsonObject encode() {
        return new JsonObject();
    }
}

