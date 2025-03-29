/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.networking.advanced.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import pisi.unitedmeows.yystal.YYStal;
import pisi.unitedmeows.yystal.clazz.ref;
import pisi.unitedmeows.yystal.exception.YExManager;
import pisi.unitedmeows.yystal.exception.impl.YexIO;
import pisi.unitedmeows.yystal.networking.IPAddress;
import pisi.unitedmeows.yystal.networking.advanced.server.YServerChannelBuilder;
import pisi.unitedmeows.yystal.networking.advanced.server.YServerNetworkHandler;
import pisi.unitedmeows.yystal.networking.advanced.server.YSocketChannel;

public class YServerChannel {
    private ServerSocket serverSocket;
    private IPAddress address;
    private int port;
    private List<YSocketChannel> connectedClients;
    private YServerNetworkHandler handler;
    private YServerChannelBuilder builder;
    private Thread connectionListenerThread;

    public YServerChannel(YServerChannelBuilder _builder) {
        this.builder = _builder;
    }

    public YServerChannel bind(IPAddress _address, int _port) {
        this.connectedClients = new ArrayList<YSocketChannel>();
        try {
            this.serverSocket = new ServerSocket(_port, 3, _address.inetAddress.get());
            this.address = _address;
            this.port = _port;
            this.connectionListenerThread = new Thread(this::listenConnections);
            this.connectionListenerThread.start();
        }
        catch (IOException e) {
            YExManager.pop(new YexIO(String.format("Couldn't start the server %s", e.getMessage()), new Object[0]));
            e.printStackTrace();
        }
        return this;
    }

    protected void listenConnections() {
        while (!this.serverSocket.isClosed()) {
            try {
                YSocketChannel socket = new YSocketChannel(this, this.serverSocket.accept());
                ref<Boolean> allowed = YYStal.reference(true);
                if (this.handler != null) {
                    this.handler.channelInitialize(socket, allowed);
                }
                if (allowed.get().booleanValue()) {
                    this.connectedClients.add(socket);
                    continue;
                }
                socket.kick();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected boolean removeClient(YSocketChannel channel) {
        return this.connectedClients.remove(channel);
    }

    public YServerChannel handler(YServerNetworkHandler _handler) {
        this.handler = _handler;
        return this;
    }

    public YServerChannel bind(int port) {
        return this.bind(IPAddress.ANY, port);
    }

    public List<YSocketChannel> connectedClients() {
        return this.connectedClients;
    }
}

