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

@RegisterPacket(value=PacketHeaders.THREAD_CREATE)
public class VThreadCreatePacket
extends VPacket {
    private int type;
    private int totalMessageSent;
    private boolean metadataLocked;
    private String metadataCreateTimestamp;
    private int metadataAutoArchiveDuration;
    private boolean metadataArchived;
    private String metadataArchiveTimestamp;
    private int rateLimitPerUser;
    private String parentId;
    private String ownerId;
    private boolean newlyCreated;
    private String name;
    private int messageCount;
    private int memberCount;
    private String lastMessageId;
    private String id;
    private String guildId;
    private int flags;

    @Override
    public void decode(JsonElement _object) {
        JsonObject object = _object.getAsJsonObject();
        System.out.println("VThreadCreatePacket >>>>> " + object);
        this.type = Jsons.getInt(object.get("type"));
        this.totalMessageSent = Jsons.getInt(object.get("total_message_sent"));
        JsonObject threadMetadata = object.getAsJsonObject("thread_metadata");
        this.metadataLocked = Jsons.getBoolean(threadMetadata.get("locked"));
        this.metadataCreateTimestamp = Jsons.getString(threadMetadata.get("create_timestamp"));
        this.metadataAutoArchiveDuration = Jsons.getInt(threadMetadata.get("auto_archive_duration"));
        this.metadataArchived = Jsons.getBoolean(threadMetadata.get("archived"));
        this.metadataArchiveTimestamp = Jsons.getString(threadMetadata.get("archive_timestamp"));
        this.rateLimitPerUser = Jsons.getInt(object.get("rate_limit_per_user"));
        this.parentId = Jsons.getString(object.get("parent_id"));
        this.ownerId = Jsons.getString(object.get("owner_id"));
        this.newlyCreated = Jsons.getBoolean(object.get("newly_created"));
        this.name = Jsons.getString(object.get("name"));
        this.messageCount = Jsons.getInt(object.get("message_count"));
        this.memberCount = Jsons.getInt(object.get("member_count"));
        this.lastMessageId = Jsons.getString(object.get("last_message_id"));
        this.id = Jsons.getString(object.get("id"));
        this.guildId = Jsons.getString(object.get("guild_id"));
        this.flags = Jsons.getInt(object.get("flags"));
    }

    public JsonObject encode() {
        return new JsonObject();
    }
}

