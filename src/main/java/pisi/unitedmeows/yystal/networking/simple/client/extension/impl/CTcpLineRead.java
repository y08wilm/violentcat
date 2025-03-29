/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.networking.simple.client.extension.impl;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import pisi.unitedmeows.yystal.clazz.out;
import pisi.unitedmeows.yystal.clazz.ref;
import pisi.unitedmeows.yystal.networking.simple.client.YTcpClient;
import pisi.unitedmeows.yystal.networking.simple.client.extension.CTcpExtension;

public class CTcpLineRead
extends CTcpExtension {
    protected YTcpClient client;
    protected BufferedReader bufferedReader;

    @Override
    public void setup(YTcpClient tcpClient) {
        this.client = tcpClient;
    }

    @Override
    public void onPreDataReceive(DataInputStream inputStream, ref<Boolean> cancelDefaultReader, out<byte[]> readData) {
        if (this.bufferedReader == null) {
            this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        }
        try {
            String readLine = this.bufferedReader.readLine();
            if (readLine == null) {
                this.client.close();
                cancelDefaultReader.set(true);
                readData.set(null);
                return;
            }
            cancelDefaultReader.set(true);
            readData.set(readLine.getBytes(StandardCharsets.UTF_8));
        }
        catch (Exception exception) {
            // empty catch block
        }
    }
}

