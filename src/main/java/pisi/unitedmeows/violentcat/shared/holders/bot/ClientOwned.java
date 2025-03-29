/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.violentcat.shared.holders.bot;

import pisi.unitedmeows.violentcat.bot.DiscordBot;
import pisi.unitedmeows.violentcat.shared.DiscordClient;

public class ClientOwned {
    private DiscordClient owner;

    public ClientOwned bind(DiscordBot _owner) {
        this.owner = _owner;
        return this;
    }

    public DiscordClient owner() {
        return this.owner;
    }

    public DiscordBot botInstance() {
        return (DiscordBot)this.owner;
    }
}

