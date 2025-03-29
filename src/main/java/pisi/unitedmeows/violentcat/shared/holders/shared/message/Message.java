/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.Expose
 *  com.google.gson.annotations.SerializedName
 */
package pisi.unitedmeows.violentcat.shared.holders.shared.message;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pisi.unitedmeows.violentcat.shared.action.Action;
import pisi.unitedmeows.violentcat.shared.holders.bot.ClientOwned;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.Attachment;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.Author;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.MessageReference;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.component.Component;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.embed.Embed;
import pisi.unitedmeows.violentcat.shared.holders.shared.user.BasicUser;
import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;

public class Message
extends ClientOwned {
    @Expose
    private String id;
    @Expose
    private int type;
    @Expose
    @SerializedName(value="channel_id")
    private String channelId;
    @Expose
    private Author author;
    @Expose
    public String content;
    @Expose
    private List<Attachment> attachments;
    @Expose
    private List<Embed> embeds = new ArrayList<Embed>();
    @Expose
    private List<BasicUser> mentions;
    @Expose
    @SerializedName(value="mention_roles")
    private String[] mentionRoles;
    @Expose
    private boolean pinned;
    @Expose
    private boolean tts;
    @Expose
    @SerializedName(value="mention_everyone")
    private boolean mentionEveryone;
    @Expose
    private int flags;
    @Expose
    @SerializedName(value="message_reference")
    private MessageReference referenceMessage;
    @Expose
    private List<Component> components = new ArrayList<Component>();

    @Deprecated
    @OnlyLibCalls
    public Message() {
    }

    public Message(String _content) {
        this.content = _content;
    }

    public static Message create(String content) {
        return new Message(content);
    }

    public static Message create() {
        return new Message();
    }

    public Action<Boolean> delete() {
        if (this.owner().isBot()) {
            this.botInstance().deleteMessage(this.channelId(), this.id);
        }
        return null;
    }

    public Action<Message> reply(String content) {
        if (this.owner().isBot()) {
            return this.botInstance().reply(this.channelId, this.id, new Message(content));
        }
        return null;
    }

    public Action<Message> reply(Message message) {
        if (this.owner().isBot()) {
            return this.botInstance().reply(this.channelId, this.id, message);
        }
        return null;
    }

    public Action<Message> reply(Message message, Embed ... embeds) {
        if (this.owner().isBot()) {
            return this.botInstance().send(this.channelId, message.reference(this.id), Arrays.stream(embeds).iterator());
        }
        return null;
    }

    public Action<Message> reply(String content, Embed ... embeds) {
        if (this.owner().isBot()) {
            return this.botInstance().send(this.channelId, new Message(content).reference(this.id), Arrays.stream(embeds).iterator());
        }
        return null;
    }

    public Action<Message> reply(Embed ... embeds) {
        if (this.owner().isBot()) {
            return this.botInstance().send(this.channelId, new Message("").reference(this.id), Arrays.stream(embeds).iterator());
        }
        return null;
    }

    public Message add(Component component) {
        this.components.add(component);
        return this;
    }

    public List<Component> components() {
        return this.components;
    }

    public String id() {
        return this.id;
    }

    public int type() {
        return this.type;
    }

    public String channelId() {
        return this.channelId;
    }

    public Author author() {
        return this.author;
    }

    public List<Embed> embeds() {
        return this.embeds;
    }

    public List<BasicUser> mentions() {
        return this.mentions;
    }

    public String[] mentionRoles() {
        return this.mentionRoles;
    }

    public String content() {
        return this.content;
    }

    public boolean isPinned() {
        return this.pinned;
    }

    public boolean isTts() {
        return this.tts;
    }

    public boolean isMentionEveryone() {
        return this.mentionEveryone;
    }

    public int flags() {
        return this.flags;
    }

    public MessageReference reference() {
        return this.referenceMessage;
    }

    public Message type(int _type) {
        this.type = _type;
        return this;
    }

    public Message content(String _content) {
        this.content = _content;
        return this;
    }

    public Message embeds(List<Embed> _embeds) {
        this.embeds = _embeds;
        return this;
    }

    public Message tts(boolean _tts) {
        this.tts = _tts;
        return this;
    }

    public Message flags(int _flags) {
        this.flags = _flags;
        return this;
    }

    public Message reference(String messageId, String channelId) {
        this.referenceMessage = new MessageReference();
        this.referenceMessage.messageId(messageId);
        this.referenceMessage.channelId(channelId);
        return this;
    }

    public Message reference(String messageId) {
        this.referenceMessage = new MessageReference();
        this.referenceMessage.messageId(messageId);
        return this;
    }

    public Message reference(MessageReference messageReference) {
        this.referenceMessage = messageReference;
        return this;
    }

    public List<Attachment> attachments() {
        return this.attachments;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Message{");
        sb.append("id='").append(this.id).append('\'');
        sb.append(", type=").append(this.type);
        sb.append(", channelId='").append(this.channelId).append('\'');
        sb.append(", author=").append(this.author);
        sb.append(", content='").append(this.content).append('\'');
        sb.append(", attachments=").append(this.attachments);
        sb.append(", embeds=").append(this.embeds);
        sb.append(", mentions=").append(this.mentions);
        sb.append(", mentionRoles=").append(Arrays.toString(this.mentionRoles));
        sb.append(", pinned=").append(this.pinned);
        sb.append(", tts=").append(this.tts);
        sb.append(", mentionEveryone=").append(this.mentionEveryone);
        sb.append(", flags=").append(this.flags);
        sb.append(", referenceMessage=").append(this.referenceMessage);
        sb.append('}');
        return sb.toString();
    }
}

