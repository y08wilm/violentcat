/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package pisi.unitedmeows.violentcat.shared.packet.impl.client;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.shared.packet.PacketHeaders;
import pisi.unitedmeows.violentcat.shared.packet.VPacket;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterPacket;

@RegisterPacket(value=PacketHeaders.IDENTIFY)
public class VIdentifyPacket
extends VPacket {
    private final String token;
    private final int intents;
    private final String os;
    private final String browser;
    private final String device;
    private boolean compress;

    public VIdentifyPacket(String _token, int _intents, boolean _compress, String _os, String _browser, String _device) {
        this.token = _token;
        this.intents = _intents;
        this.compress = _compress;
        this.os = _os;
        this.browser = _browser;
        this.device = _device;
    }

    @Override
    public void decode(JsonElement object) {
    }

    public JsonObject encode() {
        JsonObject d = new JsonObject();
        JsonObject properties = new JsonObject();
        d.addProperty("token", this.token);
        d.addProperty("intents", (Number)this.intents);
        d.addProperty("compress", Boolean.valueOf(this.compress));
        properties.addProperty("os", this.os);
        properties.addProperty("browser", this.browser);
        properties.addProperty("device", this.device);
        d.add("properties", (JsonElement)properties);
        return d;
    }
}

