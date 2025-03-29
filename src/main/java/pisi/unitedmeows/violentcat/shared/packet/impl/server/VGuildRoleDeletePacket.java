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

@RegisterPacket(value=PacketHeaders.GUILD_ROLE_DELETE)
public class VGuildRoleDeletePacket
extends VPacket {
    private int version;
    private String roleId;
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
        System.out.println("VGuildRoleDelete >>>>> " + object);
        this.version = Jsons.getInt(object.get("version"));
        this.roleId = Jsons.getString(object.get("role_id"));
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

