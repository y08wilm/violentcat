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
import pisi.unitedmeows.violentcat.shared.holders.shared.message.Message;
import pisi.unitedmeows.violentcat.shared.packet.PacketHeaders;
import pisi.unitedmeows.violentcat.shared.packet.VPacket;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterPacket;
import pisi.unitedmeows.violentcat.utils.GsonWrap;

@RegisterPacket(value=PacketHeaders.MESSAGE_UPDATE)
public class VMessageUpdatePacket
extends VPacket {
    private Message message;

    @Override
    public void decode(JsonElement object) {
        System.out.println(object.getAsJsonObject().toString());
        this.message = (Message)new GsonWrap().convert(Message.class, object.getAsJsonObject()).result();
    }

    public JsonObject encode() {
        return null;
    }

    public Message message() {
        return this.message;
    }
}

