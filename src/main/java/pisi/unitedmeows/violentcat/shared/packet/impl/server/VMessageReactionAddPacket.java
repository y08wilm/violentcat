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
import pisi.unitedmeows.violentcat.shared.holders.shared.message.Reaction;
import pisi.unitedmeows.violentcat.shared.packet.PacketHeaders;
import pisi.unitedmeows.violentcat.shared.packet.VPacket;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterPacket;
import pisi.unitedmeows.violentcat.utils.GsonWrap;

@RegisterPacket(value=PacketHeaders.MESSAGE_REACTION_ADD)
public class VMessageReactionAddPacket
extends VPacket {
    private Reaction reaction;

    @Override
    public void decode(JsonElement object) {
        this.reaction = (Reaction)new GsonWrap().convert(Reaction.class, object.getAsJsonObject()).result();
    }

    public JsonObject encode() {
        return null;
    }

    public Reaction reaction() {
        return this.reaction;
    }
}

