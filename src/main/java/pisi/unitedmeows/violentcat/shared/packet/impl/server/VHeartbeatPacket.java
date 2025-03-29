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

@RegisterPacket(value=PacketHeaders.HEARTBEAT_INTERVAL)
public class VHeartbeatPacket
extends VPacket {
    private long heartbeatInterval;

    @Override
    public void decode(JsonElement object) {
        System.out.println(object);
        this.heartbeatInterval = object.getAsJsonObject().get("heartbeat_interval").getAsLong();
    }

    public JsonObject encode() {
        return null;
    }

    public long heartbeatInterval() {
        return this.heartbeatInterval;
    }
}

