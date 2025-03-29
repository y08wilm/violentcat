/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.networking.simple.client.extension;

import java.io.DataInputStream;
import pisi.unitedmeows.yystal.clazz.out;
import pisi.unitedmeows.yystal.clazz.ref;
import pisi.unitedmeows.yystal.networking.IPAddress;
import pisi.unitedmeows.yystal.networking.simple.client.YTcpClient;

public class CTcpExtension {
    protected YTcpClient client;

    public void setup(YTcpClient tcpClient) {
        this.client = tcpClient;
    }

    public void onConnection(ref<IPAddress> connectionIp, ref<Integer> connectionPort, ref<Boolean> accepted) {
    }

    public void onPostDataReceive(ref<byte[]> data) {
    }

    public void onPreDataReceive(DataInputStream inputStream, ref<Boolean> cancelDefaultReader, out<byte[]> readData) {
    }

    public void onDataSend(ref<byte[]> data, ref<Boolean> send) {
    }

    public void onSocketClose(ref<Boolean> cancel) {
    }
}

