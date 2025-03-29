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

@RegisterPacket(value=PacketHeaders.READY)
public class VReadyPacket
extends VPacket {
    @Override
    public void decode(JsonElement object) {
    }

    @Override
    public JsonElement encode() {
        return new JsonObject();
    }
}

