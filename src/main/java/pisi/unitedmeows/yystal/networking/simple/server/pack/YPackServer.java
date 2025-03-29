/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.networking.simple.server.pack;

import pisi.unitedmeows.yystal.clazz.event;
import pisi.unitedmeows.yystal.networking.IPAddress;
import pisi.unitedmeows.yystal.networking.simple.events.SDataReceivedEvent;
import pisi.unitedmeows.yystal.networking.simple.server.YSocketClient;
import pisi.unitedmeows.yystal.networking.simple.server.YTcpServer;
import pisi.unitedmeows.yystal.networking.simple.server.pack.YSignal;
import pisi.unitedmeows.yystal.networking.simple.server.pack.events.YSSignalReceived;
import pisi.unitedmeows.yystal.utils.MemoryReader;

public class YPackServer {
    protected YTcpServer tcpServer;
    public event<YSSignalReceived> signalReceivedEvent = new event();

    public YPackServer(IPAddress address, int port) {
        this.tcpServer = new YTcpServer(address, port);
    }

    public YPackServer(int port) {
        this(IPAddress.LOOPBACK, port);
    }

    public int port() {
        return this.tcpServer.port();
    }

    public void listen() {
        this.tcpServer.makeFixed();
        this.tcpServer.listen();
        this.tcpServer.dataReceiveEvent.bind(new SDataReceivedEvent(){

            @Override
            public void onClientDataReceived(YSocketClient client, byte[] data) {
                YPackServer.this.signalReceivedEvent.fire(client, new YSignal(new MemoryReader(data)));
            }
        });
    }

    public YTcpServer tcpServer() {
        return this.tcpServer;
    }
}

