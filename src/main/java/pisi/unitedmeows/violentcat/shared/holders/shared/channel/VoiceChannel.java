/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.violentcat.shared.holders.shared.channel;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import pisi.unitedmeows.violentcat.shared.action.Action;
import pisi.unitedmeows.violentcat.shared.holders.shared.channel.Channel;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.Message;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.embed.Embed;

public class VoiceChannel
extends Channel {
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
}

