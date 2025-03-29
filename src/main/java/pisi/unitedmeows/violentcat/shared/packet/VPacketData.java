/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.violentcat.shared.packet;

import java.util.Optional;
import pisi.unitedmeows.violentcat.shared.packet.IGateway;

public class VPacketData {
    private String packet;
    private boolean sent = false;
    private Optional<Runnable> postTask = Optional.empty();
    private IGateway gateway;

    public VPacketData(String _packet, IGateway _gateway) {
        this.packet = _packet;
        this.gateway = _gateway;
    }

    public String packet() {
        return this.packet;
    }

    public boolean sent() {
        return this.sent;
    }

    public VPacketData then(Runnable _runnable) {
        this.postTask = Optional.of(_runnable);
        return this;
    }

    public VPacketData queue() {
        this.gateway.queue(this);
        return this;
    }

    public VPacketData await() {
        this.gateway.await(this);
        return this;
    }

    public VPacketData mark() {
        this.sent = true;
        this.post();
        return this;
    }

    public void post() {
        this.postTask.ifPresent(Runnable::run);
    }
}

