/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonNull
 *  com.google.gson.JsonObject
 */
package pisi.unitedmeows.violentcat.shared.packet.impl.server;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.shared.packet.PacketHeaders;
import pisi.unitedmeows.violentcat.shared.packet.VPacket;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterPacket;

@RegisterPacket(value=PacketHeaders.HEARTBEAT_CONFIRM)
public class VHeartbeatConfirmPacket
extends VPacket {
    private int sequence;

    public JsonObject encode() {
        return null;
    }

    @Override
    public void decode(JsonElement object) {
        this.sequence = object == JsonNull.INSTANCE ? -1 : object.getAsInt();
    }

    public int sequence() {
        return this.sequence;
    }
}

