/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.Expose
 *  com.google.gson.annotations.SerializedName
 */
package pisi.unitedmeows.violentcat.bot.events;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import pisi.unitedmeows.eventapi.event.Event;

public class MessageDeleteEvent
extends Event {
    @Expose
    private String id;
    @Expose
    @SerializedName(value="channel_id")
    private String channelId;

    public MessageDeleteEvent(String _id, String _channelId) {
        this.id = _id;
        this.channelId = _channelId;
    }

    public String id() {
        return this.id;
    }

    public String channelId() {
        return this.channelId;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("MessageDeleteEvent{");
        sb.append("id='").append(this.id).append('\'');
        sb.append(", channelId='").append(this.channelId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

