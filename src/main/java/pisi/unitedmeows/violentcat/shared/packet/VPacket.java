/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 */
package pisi.unitedmeows.violentcat.shared.packet;

import com.google.gson.JsonElement;
import pisi.unitedmeows.violentcat.shared.packet.PacketHeaders;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterPacket;

public abstract class VPacket {
    public PacketHeaders header() {
        return this.getClass().getAnnotation(RegisterPacket.class).value();
    }

    public abstract void decode(JsonElement var1);

    public abstract JsonElement encode();
}

