/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.networking.advanced.server;

import pisi.unitedmeows.yystal.networking.advanced.server.YServerChannel;

public class YServerChannelBuilder {
    protected int backlog = 7;

    protected YServerChannelBuilder() {
    }

    public YServerChannelBuilder backlog(int _backlog) {
        this.backlog = _backlog;
        return this;
    }

    protected int backlog() {
        return this.backlog;
    }

    public static YServerChannelBuilder create() {
        return new YServerChannelBuilder();
    }

    public YServerChannel build() {
        return new YServerChannel(this);
    }
}

