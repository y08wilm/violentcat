/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.networking.simple.server.extension.impl;

import java.io.DataInputStream;
import pisi.unitedmeows.yystal.clazz.out;
import pisi.unitedmeows.yystal.clazz.ref;
import pisi.unitedmeows.yystal.networking.simple.server.YSocketClient;
import pisi.unitedmeows.yystal.networking.simple.server.extension.STcpExtension;
import pisi.unitedmeows.yystal.utils.MemoryWriter;

public class STcpFixedSize
extends STcpExtension {
    @Override
    public void onPreDataReceive(YSocketClient client, DataInputStream inputStream, ref<Boolean> cancelDefaultReader, out<byte[]> readData) {
        try {
            int dataSize = inputStream.readInt();
            if (dataSize > 65535) {
                dataSize = 65535;
            }
            byte[] data = new byte[dataSize];
            inputStream.read(data);
            cancelDefaultReader.set(true);
            readData.set(data);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Override
    public void onDataSend(YSocketClient client, ref<byte[]> data, ref<Boolean> send) {
        try (MemoryWriter memoryWriter = new MemoryWriter();){
            byte[] rawData = data.get();
            memoryWriter.writeInt(rawData.length);
            memoryWriter.write(rawData);
            data.set(memoryWriter.getBytes());
            send.set(true);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }
}

