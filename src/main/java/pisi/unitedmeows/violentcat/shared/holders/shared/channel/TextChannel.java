/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.Expose
 *  com.google.gson.annotations.SerializedName
 */
package pisi.unitedmeows.violentcat.shared.holders.shared.channel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import pisi.unitedmeows.violentcat.shared.action.Action;
import pisi.unitedmeows.violentcat.shared.holders.shared.channel.Channel;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.Message;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.embed.Embed;

public class TextChannel
extends Channel {
    @Expose
    @SerializedName(value="last_message_id")
    private String lastMessageId;
    @Expose
    private String topic;
    @Expose
    @SerializedName(value="last_pin_timestamp")
    private String last_pin_time;
    @Expose
    @SerializedName(value="rate_limit_per_user")
    private int rateLimitPerUser;
    @Expose
    private boolean nsfw;

    @Deprecated
    public Action<Message> reply(String messageId, String content) {
        if (this.owner().isBot()) {
            return this.botInstance().reply(this.id, messageId, new Message(content));
        }
        return null;
    }

    @Deprecated
    public Action<Message> reply(String messageId, Message message) {
        if (this.owner().isBot()) {
            return this.botInstance().reply(this.id, messageId, message);
        }
        return null;
    }

    @Deprecated
    public Action<Message> reply(String messageId, Message message, Embed ... embeds) {
        if (this.owner().isBot()) {
            return this.botInstance().send(this.id, message.reference(messageId), Arrays.stream(embeds).iterator());
        }
        return null;
    }

    @Deprecated
    public Action<Message> reply(String messageId, String content, Embed ... embeds) {
        if (this.owner().isBot()) {
            return this.botInstance().send(this.id, new Message(content).reference(messageId), Arrays.stream(embeds).iterator());
        }
        return null;
    }

    @Deprecated
    public Action<Message> reply(String messageId, Embed ... embeds) {
        if (this.owner().isBot()) {
            return this.botInstance().send(this.id, new Message("").reference(messageId), Arrays.stream(embeds).iterator());
        }
        return null;
    }

    public Action<Message> send(Message message) {
        if (this.owner().isBot()) {
            return this.botInstance().send(this.id, message);
        }
        return null;
    }

    public Action<Message> send(String message) {
        return this.botInstance().send(this.id, message);
    }

    public Action<Message> send(Embed ... embeds) {
        if (this.owner().isBot()) {
            return this.botInstance().send("", embeds);
        }
        return null;
    }

    public Action<Message> send(String content, List<Embed> embeds) {
        if (this.owner().isBot()) {
            return this.botInstance().send(this.id, content, embeds);
        }
        return null;
    }

    public Action<Message> send(List<Embed> embeds) {
        if (this.owner().isBot()) {
            return this.botInstance().send(this.id, embeds);
        }
        return null;
    }

    public Action<Message> send(Message message, Iterator<Embed> iterator) {
        if (this.owner().isBot()) {
            return this.botInstance().send(this.id, message, iterator);
        }
        return null;
    }

    public Action<Message> send(String content, Iterator<Embed> iterator) {
        if (this.owner().isBot()) {
            return this.botInstance().send(this.id, content, iterator);
        }
        return null;
    }

    public Action<Message> send(String content, Embed ... embeds) {
        return this.send(content, Arrays.stream(embeds).iterator());
    }

    public String id() {
        return this.id();
    }

    public String lastMessageId() {
        return this.lastMessageId;
    }

    @Override
    public String name() {
        return this.name;
    }

    public int position() {
        return this.position;
    }

    public int flags() {
        return this.flags;
    }

    public String topic() {
        return this.topic;
    }

    public String last_pin_time() {
        return this.last_pin_time;
    }

    public int rateLimitPerUser() {
        return this.rateLimitPerUser;
    }

    public boolean isNsfw() {
        return this.nsfw;
    }
}

