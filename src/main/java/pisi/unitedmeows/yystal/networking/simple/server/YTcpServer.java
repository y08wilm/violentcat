/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.networking.simple.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import pisi.unitedmeows.yystal.YYStal;
import pisi.unitedmeows.yystal.clazz.event;
import pisi.unitedmeows.yystal.clazz.ref;
import pisi.unitedmeows.yystal.exception.YExManager;
import pisi.unitedmeows.yystal.exception.impl.YexIO;
import pisi.unitedmeows.yystal.networking.IPAddress;
import pisi.unitedmeows.yystal.networking.simple.events.SConnectionReceivedEvent;
import pisi.unitedmeows.yystal.networking.simple.events.SDataReceivedEvent;
import pisi.unitedmeows.yystal.networking.simple.server.YSocketClient;
import pisi.unitedmeows.yystal.networking.simple.server.extension.STcpExtension;
import pisi.unitedmeows.yystal.networking.simple.server.extension.impl.STcpFixedSize;
import pisi.unitedmeows.yystal.networking.simple.server.extension.impl.STcpLineRead;
import pisi.unitedmeows.yystal.utils.IDisposable;

public class YTcpServer
implements IDisposable {
    private IPAddress listening;
    private int port;
    private List<YSocketClient> connectedClients;
    public event<SDataReceivedEvent> dataReceiveEvent = new event();
    public event<SConnectionReceivedEvent> connectionReceivedEvent = new event();
    private Thread connectionThread;
    private ServerSocket serverSocket;
    private List<STcpExtension> extensions;

    public YTcpServer(IPAddress _listenIp, int _listenPort) {
        this.listening = _listenIp;
        this.port = _listenPort;
        this.extensions = new ArrayList<STcpExtension>();
    }

    public YTcpServer(ServerSocket _serverSocket, IPAddress _listenIp, int _listenPort) {
        this(_listenIp, _listenPort);
        this.serverSocket = _serverSocket;
    }

    public void registerExtension(STcpExtension extension) {
        this.extensions().add(extension);
        extension.setup(this);
    }

    public boolean listen() {
        if (this.serverSocket == null) {
            try {
                this.serverSocket = new ServerSocket(this.port, 50, this.listening.inetAddress.get());
            }
            catch (IOException e) {
                YExManager.pop(new YexIO(String.format("Couldn't start the server %s", e.getMessage()), new Object[0]));
                return false;
            }
        }
        this.connectedClients = new ArrayList<YSocketClient>();
        this.connectionThread = new Thread(this::startListening);
        this.connectionThread.start();
        return true;
    }

    private void startListening() {
        while (!this.serverSocket.isClosed()) {
            try {
                Socket socket = this.serverSocket.accept();
                ref<Boolean> accepted = YYStal.reference(true);
                this.extensions.forEach(x -> x.onConnectionRequest(socket, accepted));
                if (accepted.get().booleanValue()) {
                    YSocketClient ySocketClient = new YSocketClient(socket, this);
                    this.connectedClients.add(ySocketClient);
                    this.connectionReceivedEvent.fire(ySocketClient);
                    continue;
                }
                socket.close();
            }
            catch (IOException ex) {
                YExManager.pop(new YexIO(String.format("Server couldn't accept the client %s", ex.getMessage()), new Object[0]));
            }
        }
    }

    @Override
    public void close() {
        try {
            this.kickAll();
            this.connectedClients.clear();
            this.serverSocket.close();
            this.dataReceiveEvent = null;
            this.connectionReceivedEvent = null;
            this.listening = null;
            this.extensions.clear();
            this.extensions = null;
        }
        catch (IOException e) {
            YExManager.pop(new YexIO("Received an error while closing the server binded on %s -> %s", this.port, e.getMessage()));
        }
    }

    public void kickAll() {
        for (YSocketClient client : this.connectedClients()) {
            client._preClose();
        }
    }

    public YTcpServer makeFixed() {
        this.extensions().add(new STcpFixedSize());
        return this;
    }

    public YTcpServer makeLineBased() {
        this.extensions.add(new STcpLineRead());
        return this;
    }

    public List<STcpExtension> extensions() {
        return this.extensions;
    }

    public List<YSocketClient> connectedClients() {
        return this.connectedClients;
    }

    public int port() {
        return this.port;
    }
}

