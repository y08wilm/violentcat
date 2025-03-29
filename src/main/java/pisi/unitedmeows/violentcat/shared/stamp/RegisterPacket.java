/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.violentcat.shared.stamp;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import pisi.unitedmeows.violentcat.shared.packet.PacketHeaders;

@Retention(value=RetentionPolicy.RUNTIME)
public @interface RegisterPacket {
    public PacketHeaders value();
}

