/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.benmanes.caffeine.cache.Cache
 *  com.github.benmanes.caffeine.cache.Caffeine
 */
package pisi.unitedmeows.violentcat.shared.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.time.Duration;
import java.util.List;
import pisi.unitedmeows.violentcat.shared.cache.SingleCache;
import pisi.unitedmeows.violentcat.shared.holders.bot.ApplicationInfo;
import pisi.unitedmeows.violentcat.shared.holders.shared.channel.Channel;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.Guild;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.GuildPreview;

public class BotCache {
    private boolean ignoreNext;
    private Cache<String, List<Channel>> GUILD_CHANNELS = Caffeine.newBuilder().maximumSize(1000L).expireAfterWrite(Duration.ofMinutes(5L)).build();
    private Cache<String, Guild> GUILDS = Caffeine.newBuilder().maximumSize(200L).expireAfterWrite(Duration.ofMinutes(5L)).build();
    private Cache<String, GuildPreview> GUILD_PREVIEWS = Caffeine.newBuilder().maximumSize(10L).expireAfterWrite(Duration.ofMinutes(1L)).build();
    private SingleCache<ApplicationInfo> APPLICATION_INFO;

    public BotCache ignoreNext() {
        this.ignoreNext = true;
        return this;
    }

    public boolean shouldIgnore() {
        if (this.ignoreNext) {
            this.ignoreNext = false;
            return true;
        }
        return false;
    }

    public Guild guild(String id) {
        return (Guild)this.GUILDS.getIfPresent((Object)id);
    }

    public ApplicationInfo applicationInfo() {
        return this.APPLICATION_INFO.shouldUpdate() ? null : this.APPLICATION_INFO.value();
    }

    public void storeApplicationInfo(ApplicationInfo applicationInfo) {
        this.APPLICATION_INFO.value(applicationInfo);
    }

    public void storeGuild(Guild guild) {
        this.GUILDS.put(guild.id(), guild);
    }

    public List<Channel> guildChannels(String guild) {
        return (List)this.GUILD_CHANNELS.getIfPresent((Object)guild);
    }

    public void storeChannels(String guild, List<Channel> channels) {
        this.GUILD_CHANNELS.put(guild, channels);
    }

    public GuildPreview guildPreview(String id) {
        return (GuildPreview)this.GUILD_PREVIEWS.getIfPresent((Object)id);
    }

    public void storeGuildPreview(GuildPreview preview) {
        this.GUILD_PREVIEWS.put(preview.id(), preview);
    }
}

