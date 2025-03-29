/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.networking.simple.client.extension.impl;

import java.io.DataInputStream;
import java.io.IOException;
import pisi.unitedmeows.yystal.clazz.out;
import pisi.unitedmeows.yystal.clazz.ref;
import pisi.unitedmeows.yystal.networking.simple.client.extension.CTcpExtension;
import pisi.unitedmeows.yystal.utils.MemoryWriter;

public class CTcpFixedSize
extends CTcpExtension {
    @Override
    public void onDataSend(ref<byte[]> data, ref<Boolean> send) {
        MemoryWriter memoryWriter = new MemoryWriter();
        try {
            memoryWriter.writeInt(data.get().length);
            memoryWriter.write(data.get());
        }
        catch (Exception exception) {
            // empty catch block
        }
        data.set(memoryWriter.getBytes());
        send.set(true);
        try {
            memoryWriter.close();
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    @Override
    public void onPreDataReceive(DataInputStream inputStream, ref<Boolean> cancelDefaultReader, out<byte[]> readData) {
        try {
            int size = inputStream.readInt();
            byte[] received = new byte[size];
            inputStream.read(received);
            readData.set(received);
            cancelDefaultReader.set(true);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }
}

