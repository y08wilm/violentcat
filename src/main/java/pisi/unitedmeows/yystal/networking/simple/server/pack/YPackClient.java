/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.networking.simple.server.pack;

import java.io.IOException;
import pisi.unitedmeows.yystal.clazz.event;
import pisi.unitedmeows.yystal.networking.IPAddress;
import pisi.unitedmeows.yystal.networking.simple.client.YTcpClient;
import pisi.unitedmeows.yystal.networking.simple.events.CDataReceivedEvent;
import pisi.unitedmeows.yystal.networking.simple.server.pack.YSignal;
import pisi.unitedmeows.yystal.networking.simple.server.pack.YSignalBuilder;
import pisi.unitedmeows.yystal.networking.simple.server.pack.events.YCSignalReceived;
import pisi.unitedmeows.yystal.utils.MemoryReader;

public class YPackClient {
    private YTcpClient tcpClient;
    public event<YCSignalReceived> signalReceivedEvent = new event();

    public YPackClient(YTcpClient client) {
        this.tcpClient = client;
        this.tcpClient.dataReceivedEvent.bind(new CDataReceivedEvent(){

            @Override
            public void onDataReceived(byte[] data) {
                try (MemoryReader memoryReader = new MemoryReader(data);){
                    YPackClient.this.signalReceivedEvent.fire(new YSignal(memoryReader));
                }
                catch (IOException iOException) {
                    // empty catch block
                }
            }
        });
    }

    public void connect(IPAddress address, int port) {
        this.tcpClient.connect(address, port);
    }

    public YTcpClient tcpClient() {
        return this.tcpClient;
    }

    public YSignalBuilder newSignal() {
        return YSignalBuilder.builder();
    }

    public void send(YSignal signal) {
        this.tcpClient.send(signal.reader().getBytes());
    }

    public void send(YSignalBuilder builder) {
        this.tcpClient.send(builder.buildBytes());
    }

    public YPackClient() {
        this(new YTcpClient().makeFixed());
    }
}

