/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.violentcat.bot.action;

import pisi.unitedmeows.violentcat.bot.DiscordBot;
import pisi.unitedmeows.violentcat.shared.action.ActionPool;
import pisi.unitedmeows.violentcat.shared.action.RateListener;
import pisi.unitedmeows.violentcat.shared.action.Ratelimits;
import pisi.unitedmeows.violentcat.shared.action.VictimRateListener;

public class BotActionPool
extends ActionPool<Ratelimits> {
    private DiscordBot bot;

    public BotActionPool(DiscordBot _bot) {
        this.bot = _bot;
        this.registerRateListener(Ratelimits.APPLICATION, new RateListener<Ratelimits>(this, Ratelimits.APPLICATION, 10, 1000));
        this.registerRateListener(Ratelimits.GUILD, new VictimRateListener<Ratelimits>((ActionPool)this, Ratelimits.GUILD, 5, 1000));
        this.registerRateListener(Ratelimits.MESSAGE_SENT, new VictimRateListener<Ratelimits>((ActionPool)this, Ratelimits.MESSAGE_SENT, 5, 5000));
        this.registerRateListener(Ratelimits.CHANNEL, new VictimRateListener<Ratelimits>((ActionPool)this, Ratelimits.CHANNEL, 5, 5000));
    }
}

