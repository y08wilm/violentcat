/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.networking.simple.client;

import java.io.Closeable;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;
import pisi.unitedmeows.yystal.clazz.event;
import pisi.unitedmeows.yystal.exception.YExManager;
import pisi.unitedmeows.yystal.exception.impl.YexIO;
import pisi.unitedmeows.yystal.networking.IPAddress;
import pisi.unitedmeows.yystal.networking.simple.events.CUdpDataReceived;

public class YUdpClient
implements Closeable {
    protected IPAddress connectedHost;
    protected int connectedPort;
    protected DatagramSocket socket;
    private Thread receiveThread;
    private byte[] buffer;
    public event<CUdpDataReceived> onDataReceived = new event();

    public YUdpClient() {
        this(512);
    }

    public YUdpClient(int bufferSize) {
        this.buffer = new byte[bufferSize];
    }

    public boolean connect(IPAddress _address, int _port) {
        if (this.socket != null) {
            this.socket.close();
        }
        try {
            this.socket = new DatagramSocket();
        }
        catch (Exception ex) {
            YExManager.pop(new YexIO(String.format("Couldn't connect to the udp server %s", ex.getMessage()), new Object[0]));
            return false;
        }
        this.connectedHost = _address;
        this.connectedPort = _port;
        if (this.receiveThread != null) {
            try {
                this.receiveThread.stop();
            }
            catch (Exception ex) {
                YExManager.pop(new YexIO(String.format("Couldn't stop last receive thread %s", ex.getMessage()), new Object[0]));
            }
        }
        this.receiveThread = new Thread(this::receive);
        this.receiveThread.start();
        return true;
    }

    public void receive() {
        while (this.isConnected()) {
            DatagramPacket response = new DatagramPacket(this.buffer, this.buffer.length);
            try {
                this.socket.receive(response);
                byte[] received = Arrays.copyOfRange(this.buffer, 0, response.getLength());
                this.onDataReceived.fire(new Object[]{received});
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void send(byte[] data) {
        DatagramPacket request = new DatagramPacket(data, data.length, this.connectedHost.inetAddress.get(), this.connectedPort);
        try {
            this.socket.send(request);
        }
        catch (IOException ex) {
            YExManager.pop(new YexIO(String.format("Couldn't send the data %s", ex.getMessage()), new Object[0]));
        }
    }

    @Override
    public void close() throws IOException {
        this.socket.close();
    }

    public boolean isConnected() {
        return !this.socket.isClosed();
    }
}

