/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package pisi.unitedmeows.violentcat.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import pisi.unitedmeows.violentcat.shared.holders.shared.etc.FileAttachment;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.embed.Embed;
import pisi.unitedmeows.violentcat.utils.Jsons;

public class WebhookMessage {
    protected String content;
    protected String username;
    protected String avatarUrl;
    protected boolean tts;
    protected List<Embed> embeds = new ArrayList<Embed>();
    protected List<FileAttachment> files = new ArrayList<FileAttachment>();

    protected WebhookMessage() {
    }

    public static WebhookMessage create() {
        return new WebhookMessage();
    }

    public String toString() {
        JsonObject json = new JsonObject();
        json.addProperty("content", this.content);
        if (this.username != null) {
            json.addProperty("username", this.username);
        }
        if (this.avatarUrl != null) {
            json.addProperty("avatar_url", this.avatarUrl);
        }
        if (this.tts) {
            json.addProperty("tts", Boolean.valueOf(true));
        }
        if (!this.embeds.isEmpty()) {
            JsonArray embedArray = new JsonArray();
            for (Embed embed : this.embeds) {
                embedArray.add((JsonElement)embed.toObject());
            }
            json.add("embeds", (JsonElement)embedArray);
        }
        if (!this.files.isEmpty()) {
            JsonArray fileArray = new JsonArray();
            int i = 0;
            for (FileAttachment attachment : this.files) {
                fileArray.add(attachment.toString(i++));
            }
            json.add("attachments", (JsonElement)fileArray);
        }
        return Jsons.BUILDER.toJson((JsonElement)json);
    }

    public List<Embed> embeds() {
        return this.embeds;
    }

    public List<FileAttachment> files() {
        return this.files;
    }

    public WebhookMessage addFile(FileAttachment attachment) {
        this.files.add(attachment);
        return this;
    }

    public WebhookMessage addEmbed(Embed embed) {
        this.embeds.add(embed);
        return this;
    }

    public String content() {
        return this.content;
    }

    public WebhookMessage content(String _content) {
        this.content = _content;
        return this;
    }

    public String username() {
        return this.username;
    }

    public WebhookMessage username(String _username) {
        this.username = _username;
        return this;
    }

    public String avatarUrl() {
        return this.avatarUrl;
    }

    public WebhookMessage avatarUrl(String _avatarUrl) {
        this.avatarUrl = _avatarUrl;
        return this;
    }

    public boolean isTts() {
        return this.tts;
    }

    public WebhookMessage tts(boolean _tts) {
        this.tts = _tts;
        return this;
    }
}

