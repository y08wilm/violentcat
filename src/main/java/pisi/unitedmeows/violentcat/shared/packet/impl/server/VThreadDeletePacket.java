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
public class VThreadDeletePacket
extends VPacket {
    private int type;
    private String parentId;
    private String id;
    private String guildId;

    @Override
    public void decode(JsonElement _object) {
        JsonObject object = _object.getAsJsonObject();
        System.out.println("VThreadDeletePacket >>>>> " + object);
        this.type = Jsons.getInt(object.get("type"));
        this.parentId = Jsons.getString(object.get("parent_id"));
        this.id = Jsons.getString(object.get("id"));
        this.guildId = Jsons.getString(object.get("guild_id"));
    }

    public JsonObject encode() {
        return new JsonObject();
    }
}

