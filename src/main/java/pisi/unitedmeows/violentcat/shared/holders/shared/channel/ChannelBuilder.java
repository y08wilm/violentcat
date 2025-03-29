/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.annotations.Expose
 */
package pisi.unitedmeows.violentcat.shared.holders.shared.channel;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.ChannelType;

public class ChannelBuilder {
    @Expose
    private String name;
    @Expose
    private int type = -1;
    @Expose
    private String topic;
    @Expose
    private int userLimit = -1;
    @Expose
    private int rateLimitPerUser;
    @Expose
    private int position;
    @Expose
    private String parentId;
    @Expose
    private boolean nsfw;

    protected ChannelBuilder() {
    }

    public static ChannelBuilder create() {
        return new ChannelBuilder();
    }

    public ChannelBuilder type(ChannelType _type) {
        this.type = _type.id();
        return this;
    }

    public ChannelBuilder name(String _name) {
        this.name = _name;
        return this;
    }

    public ChannelBuilder topic(String _topic) {
        this.topic = _topic;
        return this;
    }

    public ChannelBuilder userLimit(int _userLimit) {
        this.userLimit = _userLimit;
        return this;
    }

    public ChannelBuilder rateLimitPerUser(int _rateLimitPerUser) {
        this.rateLimitPerUser = _rateLimitPerUser;
        return this;
    }

    public ChannelBuilder position(int _position) {
        this.position = _position;
        return this;
    }

    public ChannelBuilder parentId(String _parentId) {
        this.parentId = _parentId;
        return this;
    }

    public ChannelBuilder nsfw(boolean _nsfw) {
        this.nsfw = _nsfw;
        return this;
    }

    public String toString() {
        JsonObject json = new JsonObject();
        json.addProperty("name", this.name);
        if (this.type != -1) {
            json.addProperty("type", (Number)this.type);
        }
        if (this.topic != null && !this.topic.isEmpty()) {
            json.addProperty("topic", this.topic);
        }
        if (this.userLimit != -1) {
            json.addProperty("user_limit", (Number)this.userLimit);
        }
        if (this.rateLimitPerUser != -1) {
            json.addProperty("rate_limit_per_user", (Number)this.rateLimitPerUser);
        }
        if (this.position != -1) {
            json.addProperty("position", (Number)this.position);
        }
        if (this.parentId != null && !this.parentId.isEmpty()) {
            json.addProperty("parent_id", this.parentId);
        }
        if (this.nsfw) {
            json.addProperty("nsfw", Boolean.valueOf(true));
        }
        return json.toString();
    }
}

