/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.violentcat.shared;

import pisi.unitedmeows.eventapi.system.BasicEventSystem;
import pisi.unitedmeows.violentcat.shared.ClientType;
import pisi.unitedmeows.violentcat.shared.action.ActionPool;
import pisi.unitedmeows.violentcat.shared.action.Ratelimits;
import pisi.unitedmeows.yystal.web.client.YSimpleWebClient;
import pisi.unitedmeows.yystal.web.client.YWebClient;

public abstract class DiscordClient {
    private ClientType type;

    public DiscordClient(ClientType _type) {
        this.type = _type;
    }

    public abstract YSimpleWebClient simpleWebClient();

    public abstract YWebClient webClient();

    public abstract ActionPool<Ratelimits> actionPool();

    public abstract BasicEventSystem eventSystem();

    public boolean isBot() {
        return this.type == ClientType.BOT;
    }

    public boolean isSelf() {
        return this.type == ClientType.SELF;
    }

    public ClientType type() {
        return this.type;
    }
}

