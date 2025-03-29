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

public class MessageReference {
    @Expose
    @SerializedName(value="message_id")
    private String messageId;
    @Expose
    @SerializedName(value="channel_id")
    private String channelId;
    @Expose
    @SerializedName(value="fail_if_not_exists")
    private boolean failOnError = false;

    public String messageId() {
        return this.messageId;
    }

    public String channelId() {
        return this.channelId;
    }

    public boolean isFailOnError() {
        return this.failOnError;
    }

    public MessageReference messageId(String _messageId) {
        this.messageId = _messageId;
        return this;
    }

    public MessageReference channelId(String _channelId) {
        this.channelId = _channelId;
        return this;
    }

    public MessageReference failOnError(boolean _failOnError) {
        this.failOnError = _failOnError;
        return this;
    }
}

