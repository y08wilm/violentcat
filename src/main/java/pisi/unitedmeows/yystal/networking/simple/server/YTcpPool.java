/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.networking.simple.server;

import java.net.ServerSocket;
import pisi.unitedmeows.yystal.networking.IPAddress;
import pisi.unitedmeows.yystal.networking.simple.events.SDataReceivedEvent;
import pisi.unitedmeows.yystal.networking.simple.server.YSocketClient;
import pisi.unitedmeows.yystal.networking.simple.server.YTcpServer;

public class YTcpPool
extends YTcpServer {
    public YTcpPool(IPAddress _listenIp, int _listenPort) {
        super(_listenIp, _listenPort);
    }

    public YTcpPool(ServerSocket _serverSocket, IPAddress _listenIp, int _listenPort) {
        super(_serverSocket, _listenIp, _listenPort);
    }

    @Override
    public boolean listen() {
        boolean result = super.listen();
        if (result) {
            this.dataReceiveEvent.bind(new SDataReceivedEvent(){

                @Override
                public void onClientDataReceived(YSocketClient client, byte[] data) {
                    for (YSocketClient otherSocket : YTcpPool.this.connectedClients()) {
                        if (otherSocket == client) continue;
                        otherSocket.write(data);
                    }
                }
            });
        }
        return result;
    }

    @Override
    public YTcpPool makeFixed() {
        return (YTcpPool)super.makeFixed();
    }
}

