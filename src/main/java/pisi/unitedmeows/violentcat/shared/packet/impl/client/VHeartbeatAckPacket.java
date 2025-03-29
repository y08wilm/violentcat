/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonPrimitive
 */
package pisi.unitedmeows.violentcat.shared.packet.impl.client;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import pisi.unitedmeows.violentcat.shared.packet.PacketHeaders;
import pisi.unitedmeows.violentcat.shared.packet.VPacket;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterPacket;

@RegisterPacket(value=PacketHeaders.HEARTBEAT)
public class VHeartbeatAckPacket
extends VPacket {
    private int sequence;

    public VHeartbeatAckPacket(int _sequence) {
        this.sequence = _sequence;
    }

    public VHeartbeatAckPacket() {
        this(-1);
    }

    @Override
    public void decode(JsonElement object) {
    }

    @Override
    public JsonElement encode() {
        if (this.sequence == -1) {
            return null;
        }
        return new JsonPrimitive((Number)this.sequence);
    }
}

