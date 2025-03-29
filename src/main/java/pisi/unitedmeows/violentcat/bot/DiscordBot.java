/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonObject
 */
package pisi.unitedmeows.violentcat.bot;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import pisi.unitedmeows.eventapi.system.BasicEventSystem;
import pisi.unitedmeows.violentcat.bot.DiscordBotBuilder;
import pisi.unitedmeows.violentcat.bot.action.BotActionPool;
import pisi.unitedmeows.violentcat.bot.gateway.BotGateway;
import pisi.unitedmeows.violentcat.bot.gateway.BotNetworkHandler;
import pisi.unitedmeows.violentcat.shared.ClientType;
import pisi.unitedmeows.violentcat.shared.DiscordClient;
import pisi.unitedmeows.violentcat.shared.action.Action;
import pisi.unitedmeows.violentcat.shared.action.ActionPool;
import pisi.unitedmeows.violentcat.shared.action.Ratelimits;
import pisi.unitedmeows.violentcat.shared.cache.BotCache;
import pisi.unitedmeows.violentcat.shared.holders.Availability;
import pisi.unitedmeows.violentcat.shared.holders.Presence;
import pisi.unitedmeows.violentcat.shared.holders.Status;
import pisi.unitedmeows.violentcat.shared.holders.bot.ApplicationInfo;
import pisi.unitedmeows.violentcat.shared.holders.shared.channel.Channel;
import pisi.unitedmeows.violentcat.shared.holders.shared.channel.ChannelBuilder;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.ChannelType;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.DetailedGuild;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.Guild;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.GuildPreview;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.Message;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.embed.Embed;
import pisi.unitedmeows.violentcat.shared.packet.impl.client.VIdentifyPacket;
import pisi.unitedmeows.violentcat.shared.packet.impl.client.VPresenceUpdatePacket;
import pisi.unitedmeows.violentcat.utils.DiscordHelper;
import pisi.unitedmeows.violentcat.utils.GsonWrap;
import pisi.unitedmeows.violentcat.utils.Jsons;
import pisi.unitedmeows.yystal.web.client.YSimpleWebClient;
import pisi.unitedmeows.yystal.web.client.YWebClient;
import pisi.unitedmeows.yystal.web.client.YWebClientBuilder;
import pisi.unitedmeows.yystal.web.client.YWebResponse;

public class DiscordBot
extends DiscordClient {
    protected BotGateway gateway;
    protected BotNetworkHandler networkHandler;
    protected YWebClient client;
    protected YSimpleWebClient simpleWebClient;
    protected BotCache cache;
    protected BasicEventSystem eventSystem;
    protected BotActionPool actionPool;
    protected List<DetailedGuild> guilds = new ArrayList<DetailedGuild>();
    protected String token;
    protected long ratelimited = 0L;

    DiscordBot(DiscordBotBuilder _builder) {
        super(ClientType.BOT);
        this.eventSystem = new BasicEventSystem();
        this.cache = new BotCache();
        this.token = _builder.token();
        try {
            this.client = YWebClientBuilder.build();
            this.client.header("Authorization", String.format("Bot %s", _builder.token()));
            this.client.header("User-Agent", "violentcat");
            this.simpleWebClient = new YSimpleWebClient();
            this.simpleWebClient.setUserAgent("violentcat");
            this.simpleWebClient.header("Authorization", String.format("Bot %s", _builder.token()));
            this.actionPool = new BotActionPool(this);
            this.actionPool.start();
            this.networkHandler = new BotNetworkHandler(this);
            this.gateway = new BotGateway(this, new URI("wss://gateway.discord.gg/?v=10&encoding=json"));
            this.gateway().prepare(new VIdentifyPacket(_builder.token(), _builder.intents(), false, "violentcat", _builder.onMobile() ? "Discord Android" : "violentcat", "violentcat")).queue();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public String getToken() {
        return this.token;
    }

    public Action<Channel> createChannel(String guild, ChannelBuilder channelBuilder) {
        return this.actionPool.rateListener(Ratelimits.GUILD).queue(guild, () -> {
            String result = this.simpleWebClient.postRequest(DiscordHelper.route("/guilds/%s/channels", guild), channelBuilder.toString(), this.getToken());
            if (result == null) {
                return null;
            }
            JsonObject json = (JsonObject)Jsons.parser.parse(result);
            ChannelType type = ChannelType.from(json.get("type").getAsInt());
            Channel channel = (Channel)GsonWrap.parse(type.type(), json).result();
            channel.bind(this);
            channel.__setup(json);
            return channel;
        });
    }

    @Deprecated
    public Action<Message> reply(String channel, String messageId, String content) {
        return this.reply(channel, messageId, new Message(content));
    }

    @Deprecated
    public Action<Message> reply(String channel, String messageId, Message message) {
        return this.send(channel, message.reference(messageId));
    }

    @Deprecated
    public Action<Message> reply(String channel, String messageId, Message message, Embed ... embeds) {
        return this.send(channel, message.reference(messageId), Arrays.stream(embeds).iterator());
    }

    @Deprecated
    public Action<Message> reply(String channel, String messageId, String content, Embed ... embeds) {
        return this.send(channel, new Message(content).reference(messageId), Arrays.stream(embeds).iterator());
    }

    @Deprecated
    public Action<Message> reply(String channel, String messageId, Embed ... embeds) {
        return this.send(channel, new Message("").reference(messageId), Arrays.stream(embeds).iterator());
    }

    public void sleep(long ms) {
        try {
            Thread.sleep(ms);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Action<Message> send(String channel, Message message) {
        if (System.currentTimeMillis() - this.ratelimited < 30000L) {
            try {
                Thread.sleep(200L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            return this.send(channel, message);
        }
        return this.actionPool.rateListener(Ratelimits.MESSAGE_SENT).queue(channel, () -> {
            String link = DiscordHelper.route("/channels/%s/messages", channel);
            String result = this.simpleWebClient.postRequest(link, Jsons.BUILDER.toJson((Object)message), this.getToken());
            if (result == null) {
                this.ratelimited = System.currentTimeMillis();
                Thread thread = new Thread(() -> {
                    System.out.println("resting for 30s..");
                    this.sleep(5000L);
                    System.out.println("resting for 25s..");
                    this.sleep(5000L);
                    System.out.println("resting for 20s..");
                    this.sleep(5000L);
                    System.out.println("resting for 15s..");
                    this.sleep(5000L);
                    System.out.println("resting for 10s..");
                    this.sleep(5000L);
                    System.out.println("resting for 5s..");
                    this.sleep(5000L);
                    System.out.println("resuming..");
                });
                thread.start();
                return this.send(channel, message);
            }
            System.out.println(Jsons.BUILDER.toJson((Object)message));
            GsonWrap wrap = GsonWrap.parse(Message.class, result);
            Message returnMsg = (Message)wrap.result();
            returnMsg.bind(this);
            return returnMsg;
        });
    }

    public Action<Message> send(String channel, String message) {
        if (System.currentTimeMillis() - this.ratelimited < 30000L) {
            try {
                Thread.sleep(200L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            return this.send(channel, message);
        }
        return this.actionPool.rateListener(Ratelimits.MESSAGE_SENT).queue(channel, () -> {
            String link = DiscordHelper.route("/channels/%s/messages", channel);
            String result = this.simpleWebClient.postRequest(link, String.format("{  \"content\": \"%s\", \"tts\": false}", message), this.getToken());
            if (result == null) {
                this.ratelimited = System.currentTimeMillis();
                Thread thread = new Thread(() -> {
                    System.out.println("resting for 30s..");
                    this.sleep(5000L);
                    System.out.println("resting for 25s..");
                    this.sleep(5000L);
                    System.out.println("resting for 20s..");
                    this.sleep(5000L);
                    System.out.println("resting for 15s..");
                    this.sleep(5000L);
                    System.out.println("resting for 10s..");
                    this.sleep(5000L);
                    System.out.println("resting for 5s..");
                    this.sleep(5000L);
                    System.out.println("resuming..");
                });
                thread.start();
                return this.send(channel, message);
            }
            GsonWrap wrap = GsonWrap.parse(Message.class, result);
            Message returnMsg = (Message)wrap.result();
            returnMsg.bind(this);
            return returnMsg;
        });
    }

    public Action<Message> send(String channel, Embed ... embeds) {
        return this.send(channel, "", embeds);
    }

    public Action<Message> send(String channel, String content, List<Embed> embeds) {
        return this.send(channel, content, embeds.iterator());
    }

    public Action<Message> send(String channel, List<Embed> embeds) {
        return this.send(channel, "", embeds);
    }

    public Action<Message> send(String channel, Message message, Iterator<Embed> iterator) {
        return this.actionPool.rateListener(Ratelimits.MESSAGE_SENT).queue(channel, () -> {
            while (iterator.hasNext()) {
                Embed embed = (Embed)iterator.next();
                message.embeds().add(embed);
            }
            String data = Jsons.BUILDER.toJson((Object)message);
            String result = this.simpleWebClient.postRequest(DiscordHelper.route("/channels/%s/messages", channel), data, this.getToken());
            GsonWrap wrap = GsonWrap.parse(Message.class, result);
            Message returnMsg = (Message)wrap.result();
            returnMsg.bind(this);
            return returnMsg;
        });
    }

    public Action<Message> send(String channel, String content, Iterator<Embed> iterator) {
        return this.actionPool.rateListener(Ratelimits.MESSAGE_SENT).queue(channel, () -> {
            StringBuilder embedRaw = new StringBuilder();
            while (iterator.hasNext()) {
                Embed embed = (Embed)iterator.next();
                embedRaw.append(embed.toString());
                if (!iterator.hasNext()) continue;
                embedRaw.append(",");
            }
            String data = String.format("{  \"content\": \"%s\", \"tts\": false, \"embeds\": [%s]}", content, embedRaw.toString());
            String result = this.simpleWebClient.postRequest(DiscordHelper.route("/channels/%s/messages", channel), data, this.getToken());
            GsonWrap wrap = GsonWrap.parse(Message.class, result);
            Message returnMsg = (Message)wrap.result();
            returnMsg.bind(this);
            return returnMsg;
        });
    }

    public Action<Message> send(String channel, String content, Embed ... embeds) {
        return this.send(channel, content, Arrays.stream(embeds).iterator());
    }

    public Action<Boolean> deleteMessage(String channelId, String messageId) {
        throw new UnsupportedOperationException();
    }

    public Action<ApplicationInfo> applicationInfo() {
        if (!cache.shouldIgnore()) {
            ApplicationInfo applicationInfo = cache.applicationInfo();
            if (applicationInfo != null)
                return Action.completedTask(applicationInfo);
        }

        return actionPool.rateListener(Ratelimits.APPLICATION).queue(() -> {
            final YWebResponse response = client.get("https://discord.com/api/v10/oauth2/applications/@me").run();
            final GsonWrap wrap = GsonWrap.parse(ApplicationInfo.class, response.asString());
            final ApplicationInfo applicationInfo = wrap.result();
            applicationInfo.__setup(wrap.data());
            cache.storeApplicationInfo(applicationInfo);
            return applicationInfo;
        });
    }

    public Action<Guild> guild(String id) {
        if (!cache.shouldIgnore()) {
            Guild guild = cache.guild(id);
            if (guild != null)
                return Action.completedTask(guild);

        }
        return actionPool.rateListener(Ratelimits.GUILD).queue(id, () -> {
            final YWebResponse response = client.get(String.format("https://discord.com/api/v10/guilds/%s?with_counts=true", id)).run();
            final GsonWrap wrap = GsonWrap.parse(Guild.class, response.asString());
            final Guild guild = wrap.result();
            guild.__setup(wrap.data());
            guild.bind(this);

            /* store to cache */
            cache.storeGuild(guild);
            return guild;
        });
    }

    public Action<GuildPreview> guildPreview(String id) {
        if (!cache.shouldIgnore()) {
            GuildPreview preview = cache.guildPreview(id);
            if (preview != null)
                return Action.completedTask(preview);
        }

        return actionPool.rateListener(Ratelimits.GUILD).queue(id, () -> {
            final YWebResponse response = client.get(DiscordHelper.route("guilds/%s", id)).run();
            final GsonWrap wrap = GsonWrap.parse(GuildPreview.class, response.asString());
            final GuildPreview preview = wrap.result();
            preview.__setup(wrap.data());
            preview.bind(this);

            /* store to cache */
            cache.storeGuildPreview(preview);
            return preview;
        });
    }

    public Action<List<Channel>> guildChannels(String id) {
        if (!cache.shouldIgnore()) {
            List<Channel> channels = cache.guildChannels(id);
            if (channels != null)
                return Action.completedTask(channels);
        }

        return actionPool.rateListener(Ratelimits.GUILD).queue(id, () -> {

            final YWebResponse response = client.get(String.format("https://discord.com/api/v10/guilds/%s/channels", id)).run();
            final String result = response.asString();
            System.out.println("CHANNEL RESULT");
            System.out.println(result);
            JsonArray array = (JsonArray) Jsons.parser.parse(result);
            List<Channel> channels = new ArrayList<>(array.size());

            for (int i = 0; i < array.size(); i++) {
                JsonObject guildObject = array.get(i).getAsJsonObject();
                int type = Jsons.getInt(guildObject.get("type"));
                ChannelType channelType = ChannelType.from(type);
                if (channelType.type() != null) {
                    try {
                        final Channel channel = GsonWrap.parse(channelType.type(), guildObject).result();
                        channel.bind(this);
                        channel.__setup(guildObject);
                        channels.add(channel);
                    } catch (Exception e) {
                    }

                }
            }

            /* store to cache */
            cache.storeChannels(id, channels);
            return channels;
        });
    }

    public Action<Boolean> channel(String id) {
        return this.actionPool.rateListener(Ratelimits.GUILD).queue(id, () -> {
            YWebResponse response = this.client.get(String.format("https://discord.com/api/v10/channels/%s", id)).run();
            System.out.println(response.asString());
            return true;
        });
    }

    public Action<Boolean> deleteChannel(String channelId) {
        throw new UnsupportedOperationException();
    }

    public Action<Boolean> deleteWebhook(String guildId, String id) {
        return this.actionPool().rateListener(Ratelimits.GUILD).queue(guildId, () -> this.simpleWebClient().deleteRequest(DiscordHelper.route("/webhooks/%s", id)) != null);
    }

    public void login() {
        this.gateway.connect();
    }

    public void presence(Availability availability, Status status, String message, String url) {
        this.gateway().prepare(new VPresenceUpdatePacket(new Presence(availability, status, message, url))).queue();
    }

    public void presence(Availability availability, Status status, String message) {
        this.gateway().prepare(new VPresenceUpdatePacket(new Presence(availability, status, message))).queue();
    }

    public void presence(Availability availability) {
        this.gateway().prepare(new VPresenceUpdatePacket(new Presence(availability, Status.NOTHING, ""))).queue();
    }

    public void presence(Availability availability, Status status) {
        this.gateway().prepare(new VPresenceUpdatePacket(new Presence(availability, status, ""))).queue();
    }

    public List<DetailedGuild> guilds() {
        return this.guilds;
    }

    public BotNetworkHandler networkHandler() {
        return this.networkHandler;
    }

    public BotGateway gateway() {
        return this.gateway;
    }

    public DiscordBot ignoreCache() {
        this.cache.ignoreNext();
        return this;
    }

    @Override
    public YSimpleWebClient simpleWebClient() {
        return this.simpleWebClient;
    }

    @Override
    public YWebClient webClient() {
        return this.client;
    }

    @Override
    public ActionPool<Ratelimits> actionPool() {
        return this.actionPool;
    }

    @Override
    public BasicEventSystem eventSystem() {
        return this.eventSystem;
    }
}

