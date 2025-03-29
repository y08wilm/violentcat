/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.networking.simple.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Iterator;
import pisi.unitedmeows.yystal.YSettings;
import pisi.unitedmeows.yystal.YYStal;
import pisi.unitedmeows.yystal.clazz.out;
import pisi.unitedmeows.yystal.clazz.ref;
import pisi.unitedmeows.yystal.exception.YExManager;
import pisi.unitedmeows.yystal.exception.impl.YexIO;
import pisi.unitedmeows.yystal.networking.simple.server.YTcpServer;
import pisi.unitedmeows.yystal.parallel.Async;
import pisi.unitedmeows.yystal.utils.IDisposable;
import pisi.unitedmeows.yystal.utils.MemoryWriter;

public class YSocketClient
implements IDisposable {
    private static byte[] BUFFER = new byte[8192];
    private final Socket socket;
    private YTcpServer connectedServer;
    private Thread receiveThread;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public YSocketClient(Socket _socket, YTcpServer _connectedServer) {
        this.socket = _socket;
        this.connectedServer = _connectedServer;
        try {
            this.inputStream = new DataInputStream(this.socket.getInputStream());
            this.outputStream = new DataOutputStream(this.socket.getOutputStream());
            this.socket.setTcpNoDelay(true);
            this.socket.setReuseAddress(true);
        }
        catch (IOException ex) {
            YExManager.pop(new YexIO(String.format("Couldn't set attributes of connected client %s", ex.getMessage()), new Object[0]));
        }
        this.receiveThread = new Thread(this::receive);
        this.receiveThread.start();
    }

    public void write(byte[] data) {
        if (this.isDisconnected()) {
            return;
        }
        try {
            ref<byte[]> sendData = YYStal.reference(data);
            ref<Boolean> shouldSend = YYStal.reference(true);
            this.connectedServer().extensions().forEach(x -> x.onDataSend(this, sendData, shouldSend));
            if (shouldSend.get().booleanValue()) {
                this.outputStream.write(sendData.get());
                this.outputStream.flush();
            }
        }
        catch (IOException ex) {
            YExManager.pop(new YexIO(String.format("Couldn't send data to client %s", ex.getMessage()), new Object[0]));
        }
    }

    public void _preClose() {
        try {
            this.socket.close();
            this.outputStream.close();
            this.inputStream.close();
            this.connectedServer = null;
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void sendBuffered(byte[] data) {
        MemoryWriter writer = new MemoryWriter();
        try {
            writer.writeInt(data.length);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        byte[] size = writer.getBytes();
        this.write(size);
        ref<Integer> sent = YYStal.reference(0);
        Async.async_loop_i(() -> {
            int currentSize = Math.min((Integer)YYStal.setting(YSettings.TCP_SERVER_BUFFER_SIZE), data.length - (Integer)sent.get());
            if (currentSize <= 0) {
                return false;
            }
            this.write(Arrays.copyOfRange(data, (int)((Integer)sent.get()), (Integer)sent.get() + currentSize));
            sent.set((Integer)sent.get() + currentSize);
            return true;
        }, (Long)YYStal.setting(YSettings.TCP_SERVER_BUFFER_SENT_DELAY));
    }

    @Override
    public void close() {
        try {
            this.socket.close();
            this.outputStream.close();
            this.inputStream.close();
            Iterator<YSocketClient> socketClientIterator = this.connectedServer().connectedClients().iterator();
            while (socketClientIterator.hasNext()) {
                if (socketClientIterator.next() != this) continue;
                socketClientIterator.remove();
                break;
            }
            this.connectedServer().extensions().forEach(x -> x.onClientDisconnect(this));
        }
        catch (IOException ex) {
            YExManager.pop(new YexIO(String.format("Error while closing the client's socket %s", ex.getMessage()), new Object[0]));
        }
    }

    public YTcpServer connectedServer() {
        return this.connectedServer;
    }

    public boolean isDisconnected() {
        return this.socket == null || !this.socket.isConnected() || this.socket.isClosed();
    }

    protected void receive() {
        while (this.socket.isConnected() && !this.socket.isClosed()) {
            try {
                byte[] data;
                ref<Boolean> cancelDefaultReader = YYStal.reference(false);
                out receiveData = YYStal.out();
                this.connectedServer().extensions().forEach(extension -> extension.onPreDataReceive(this, this.inputStream, cancelDefaultReader, receiveData));
                if (cancelDefaultReader.get().booleanValue()) {
                    data = (byte[])receiveData.get();
                } else {
                    int size = this.inputStream.read(BUFFER);
                    if (size >= 0) {
                        data = Arrays.copyOf(BUFFER, size);
                    } else {
                        data = new byte[]{};
                        this.close();
                    }
                }
                this.connectedServer().dataReceiveEvent.fire(this, data);
                ref<byte[]> refData = YYStal.reference(data);
                this.connectedServer().extensions().forEach(extension -> extension.onPostDataReceive(this, refData));
            }
            catch (IOException ex) {
                YExManager.pop(new YexIO(String.format("Couldn't receive data from the client %s", ex.getMessage()), new Object[0]));
            }
        }
    }
}

