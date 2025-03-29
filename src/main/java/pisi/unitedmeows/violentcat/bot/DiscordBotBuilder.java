/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.violentcat.bot;

import pisi.unitedmeows.violentcat.bot.DiscordBot;
import pisi.unitedmeows.violentcat.shared.stamp.NotImplemented;
import pisi.unitedmeows.violentcat.shared.stamp.RISKY;

public class DiscordBotBuilder {
    private int intents = 4609;
    private String token;
    private boolean onMobile;
    @NotImplemented
    private boolean compression;

    protected DiscordBotBuilder() {
    }

    public static DiscordBotBuilder create() {
        return new DiscordBotBuilder();
    }

    public DiscordBot build() {
        return new DiscordBot(this);
    }

    String token() {
        return this.token;
    }

    public DiscordBotBuilder token(String _token) {
        this.token = _token;
        return this;
    }

    @RISKY
    public DiscordBotBuilder onMobile(boolean _onMobile) {
        this.onMobile = _onMobile;
        return this;
    }

    public DiscordBotBuilder intents(int _intents) {
        this.intents = _intents;
        return this;
    }

    public boolean onMobile() {
        return this.onMobile;
    }

    int intents() {
        return this.intents;
    }
}

