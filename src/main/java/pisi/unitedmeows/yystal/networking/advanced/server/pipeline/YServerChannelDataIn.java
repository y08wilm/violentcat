/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.networking.advanced.server.pipeline;

import java.io.IOException;
import pisi.unitedmeows.yystal.networking.advanced.server.YSocketChannel;
import pisi.unitedmeows.yystal.utils.MemoryReader;

public class YServerChannelDataIn {
    protected YSocketChannel channel;

    @Deprecated
    public void __setup(YSocketChannel _channel) {
        this.channel = _channel;
    }

    public void receiveDataHandler(MemoryReader reader) throws IOException {
    }
}

