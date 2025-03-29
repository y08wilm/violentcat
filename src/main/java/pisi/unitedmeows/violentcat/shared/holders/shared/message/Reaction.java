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
import pisi.unitedmeows.violentcat.shared.holders.bot.ClientOwned;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.Emoji;
import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;

public class Reaction
extends ClientOwned {
    @Expose
    @SerializedName(value="user_id")
    private String id;
    @Expose
    private int type;
    @Expose
    @SerializedName(value="message_id")
    private String messageId;
    @Expose
    @SerializedName(value="message_author_id")
    private String messageAuthorId;
    @Expose
    private Emoji emoji;
    @Expose
    @SerializedName(value="channel_id")
    private String channelId;

    @Deprecated
    @OnlyLibCalls
    public Reaction() {
    }

    public String id() {
        return this.id;
    }

    public String messageId() {
        return this.messageId;
    }

    public String messageAuthorId() {
        return this.messageAuthorId();
    }

    public Emoji emoji() {
        return this.emoji;
    }

    public String channelId() {
        return this.channelId;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Reaction{");
        sb.append("id='").append(this.id).append('\'');
        sb.append(", type=").append(this.type);
        sb.append(", messageId='").append(this.messageId).append('\'');
        sb.append(", messageAuthorId='").append(this.messageAuthorId).append('\'');
        sb.append(", emoji=").append(this.emoji);
        sb.append(", channelId='").append(this.channelId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

