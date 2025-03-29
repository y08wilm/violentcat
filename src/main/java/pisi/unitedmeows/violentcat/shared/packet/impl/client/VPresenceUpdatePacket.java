/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package pisi.unitedmeows.violentcat.shared.packet.impl.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.shared.holders.Presence;
import pisi.unitedmeows.violentcat.shared.holders.Status;
import pisi.unitedmeows.violentcat.shared.packet.PacketHeaders;
import pisi.unitedmeows.violentcat.shared.packet.VPacket;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterPacket;

@RegisterPacket(value=PacketHeaders.PRESENCE_UPDATE)
public class VPresenceUpdatePacket
extends VPacket {
    private Presence presence;

    public VPresenceUpdatePacket(Presence _presence) {
        this.presence = _presence;
    }

    @Override
    public void decode(JsonElement object) {
    }

    public JsonObject encode() {
        JsonObject d = new JsonObject();
        JsonArray activities = new JsonArray();
        JsonObject activitiesData = new JsonObject();
        d.addProperty("since", (Number)99999);
        activitiesData.addProperty("name", this.presence.statusMessage());
        if (this.presence.type() != Status.NOTHING.id()) {
            activitiesData.addProperty("type", (Number)this.presence.type());
            if (this.presence.special() != null && !this.presence.special().isEmpty()) {
                activitiesData.addProperty("url", this.presence.special());
            }
        }
        activities.add((JsonElement)activitiesData);
        d.add("activities", (JsonElement)activities);
        d.addProperty("status", this.presence.availability().code());
        d.addProperty("afk", Boolean.valueOf(this.presence.afk()));
        return d;
    }
}

