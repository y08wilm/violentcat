/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.networking.advanced.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import pisi.unitedmeows.yystal.clazz.HookClass;
import pisi.unitedmeows.yystal.exception.YExManager;
import pisi.unitedmeows.yystal.exception.impl.YexIO;
import pisi.unitedmeows.yystal.networking.advanced.server.YServerChannel;
import pisi.unitedmeows.yystal.networking.advanced.server.pipeline.YServerChannelDataIn;
import pisi.unitedmeows.yystal.utils.MemoryReader;
import pisi.unitedmeows.yystal.utils.MemoryWriter;
import pisi.unitedmeows.yystal.utils.kThread;
import pisi.unitedmeows.yystal.utils.list.Pipeline;

public class YSocketChannel
extends HookClass<Socket> {
    protected YServerChannel owner;
    protected Pipeline<String, YServerChannelDataIn> pipeline;
    protected Thread receiveThread;
    protected MemoryReader reader;
    protected MemoryWriter writer;
    public DataOutputStream outputStream;

    public YSocketChannel(YServerChannel _owner, Socket socket) {
        this.owner = _owner;
        this.hooked = socket;
        try {
            this.reader = new MemoryReader(new DataInputStream(socket.getInputStream()));
            this.outputStream = new DataOutputStream(socket.getOutputStream());
            this.writer = new MemoryWriter(this.outputStream);
        }
        catch (IOException e) {
            YExManager.pop(new YexIO("IO Exception on getting socket's inputstream %s", e.getMessage()));
            e.printStackTrace();
            return;
        }
        final YSocketChannel instance = this;
        this.pipeline = new Pipeline<String, YServerChannelDataIn>(){

            @Override
            public void putPost(String key, YServerChannelDataIn value) {
                value.__setup(instance);
            }
        };
        this.receiveThread = new Thread(this::startReceive);
        this.receiveThread.start();
    }

    public void kick() {
        try {
            this.pipeline.clear();
            ((Socket)this.getHooked()).close();
            this.owner.removeClient(this);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void startReceive() {
        while (!((Socket)this.hooked).isClosed()) {
            try {
                for (YServerChannelDataIn pipe : this.pipeline().values()) {
                    try {
                        pipe.receiveDataHandler(this.reader);
                    }
                    catch (SocketException ex) {
                        this.kick();
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            kThread.sleep(1L);
        }
    }

    public MemoryWriter writer() {
        return this.writer;
    }

    public MemoryReader reader() {
        return this.reader;
    }

    public Socket hooked() {
        return (Socket)super.getHooked();
    }

    public Pipeline<String, YServerChannelDataIn> pipeline() {
        return this.pipeline;
    }
}

