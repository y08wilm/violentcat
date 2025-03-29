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
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.DetailedGuild;
import pisi.unitedmeows.violentcat.shared.packet.PacketHeaders;
import pisi.unitedmeows.violentcat.shared.packet.VPacket;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterPacket;
import pisi.unitedmeows.violentcat.utils.GsonWrap;

@RegisterPacket(value=PacketHeaders.GUILD_CREATE)
public class VGuildCreatePacket
extends VPacket {
    private DetailedGuild guild;

    @Override
    public void decode(JsonElement object) {
        this.guild = (DetailedGuild)new GsonWrap().convert(DetailedGuild.class, object.getAsJsonObject()).result();
    }

    public JsonObject encode() {
        return null;
    }

    public DetailedGuild guild() {
        return this.guild;
    }
}

