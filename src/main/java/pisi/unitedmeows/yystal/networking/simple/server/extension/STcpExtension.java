/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.networking.simple.server.extension;

import java.io.DataInputStream;
import java.net.Socket;
import pisi.unitedmeows.yystal.clazz.out;
import pisi.unitedmeows.yystal.clazz.ref;
import pisi.unitedmeows.yystal.networking.simple.server.YSocketClient;
import pisi.unitedmeows.yystal.networking.simple.server.YTcpServer;

public class STcpExtension {
    protected YTcpServer server;

    public void setup(YTcpServer _server) {
        this.server = _server;
    }

    public void onConnectionRequest(Socket socket, ref<Boolean> accepted) {
    }

    public void onClientDisconnect(YSocketClient client) {
    }

    public void onPostDataReceive(YSocketClient client, ref<byte[]> data) {
    }

    public void onPreDataReceive(YSocketClient client, DataInputStream inputStream, ref<Boolean> cancelDefaultReader, out<byte[]> readData) {
    }

    public void onDataSend(YSocketClient client, ref<byte[]> data, ref<Boolean> send) {
    }
}

