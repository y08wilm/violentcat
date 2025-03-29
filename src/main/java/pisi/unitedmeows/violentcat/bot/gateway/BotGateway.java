/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonNull
 *  com.google.gson.JsonObject
 *  org.java_websocket.client.WebSocketClient
 *  org.java_websocket.handshake.ServerHandshake
 */
package pisi.unitedmeows.violentcat.bot.gateway;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import java.net.URI;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import pisi.unitedmeows.violentcat.bot.DiscordBot;
import pisi.unitedmeows.violentcat.shared.ClientType;
import pisi.unitedmeows.violentcat.shared.packet.IGateway;
import pisi.unitedmeows.violentcat.shared.packet.PacketHeaders;
import pisi.unitedmeows.violentcat.shared.packet.VPacket;
import pisi.unitedmeows.violentcat.shared.packet.VPacketData;
import pisi.unitedmeows.violentcat.shared.packet.VPacketSerializer;
import pisi.unitedmeows.violentcat.shared.packet.impl.client.VHeartbeatAckPacket;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterPacket;
import pisi.unitedmeows.yystal.YSettings;
import pisi.unitedmeows.yystal.YYStal;
import pisi.unitedmeows.yystal.utils.Pair;
import pisi.unitedmeows.yystal.utils.Stopwatch;
import pisi.unitedmeows.yystal.utils.kThread;

public class BotGateway
extends WebSocketClient
implements IGateway {
    private Thread writeThread;
    private DiscordBot discordBot;
    private String beatSequence;
    private int nextHeartbeat;
    private Stopwatch heartbeatWatcher;
    private int beatInterval;
    private int sequence;
    private boolean beated = true;
    private Queue<VPacketData> sendQueue = new ArrayDeque<VPacketData>();

    public BotGateway(DiscordBot _discordBot, URI _uri) {
        super(_uri);
        this.discordBot = _discordBot;
        this.heartbeatWatcher = new Stopwatch();
    }

    public VPacketData prepare(VPacket _vPacket) {
        RegisterPacket registerPacket = _vPacket.getClass().getAnnotation(RegisterPacket.class);
        JsonObject object = new JsonObject();
        object.addProperty("op", (Number)registerPacket.value().opCode);
        JsonElement packetData = _vPacket.encode();
        object.add("d", (JsonElement)(packetData == null ? JsonNull.INSTANCE : packetData));
        String data = object.toString();
        return this.prepareRaw(data);
    }

    public VPacketData prepareRaw(String data) {
        return new VPacketData(data, this);
    }

    public void onOpen(ServerHandshake _handshake) {
        this.writeThread = new Thread(this::_write);
        this.writeThread.start();
    }

    public void _write() {
        while (!this.isClosed()) {
            if (!this.sendQueue.isEmpty()) {
                VPacketData data = this.sendQueue.poll();
                this.send(data.packet());
                data.mark();
            }
            if (!this.beated && this.heartbeatWatcher.isReached(this.nextHeartbeat)) {
                this.prepare(new VHeartbeatAckPacket(this.sequence)).queue();
                System.out.println("sent heartbeat ack " + this.sequence);
                this.beated = true;
            }
            kThread.sleep((Long)YYStal.setting(YSettings.TCP_CLIENT_WRITE_DELAY));
        }
    }

    public void onMessage(String _message) {
        Pair<PacketHeaders, VPacket> packetPair = VPacketSerializer.serialize(_message, ClientType.BOT);
        if (packetPair != null) {
            this.discordBot.networkHandler().process(packetPair.item1(), packetPair.item2());
        }
    }

    public void onClose(int _code, String _reason, boolean _remote) {
        System.out.println(_reason);
        System.exit(-1);
    }

    public void onError(Exception e) {
        e.printStackTrace();
    }

    @Override
    public VPacketData queue(VPacketData data) {
        this.sendQueue.add(data);
        return data;
    }

    @Override
    public VPacketData await(VPacketData data) {
        this.queue(data);
        while (!data.sent()) {
            kThread.sleep((Long)YYStal.setting(YSettings.TASK_AWAIT_DELAY));
        }
        return data;
    }

    public void setupHeartbeat(int _beatInterval) {
        this.beatInterval = _beatInterval;
    }

    public void beatNext() {
        this.nextHeartbeat = (int)((double)this.beatInterval * Math.max(0.7, (double)new Random().nextFloat()));
        this.heartbeatWatcher.reset();
        this.beated = false;
    }

    public BotGateway sequence(int _sequence) {
        this.sequence = _sequence;
        return this;
    }
}

