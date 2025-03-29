/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.violentcat.shared.packet;

import pisi.unitedmeows.violentcat.shared.packet.VPacketData;

public interface IGateway {
    public VPacketData queue(VPacketData var1);

    public VPacketData await(VPacketData var1);
}

