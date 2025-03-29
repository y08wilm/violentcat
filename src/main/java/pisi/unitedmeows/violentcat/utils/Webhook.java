/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  org.apache.http.HttpEntity
 *  org.apache.http.client.methods.CloseableHttpResponse
 *  org.apache.http.client.methods.HttpPost
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.entity.ContentType
 *  org.apache.http.entity.StringEntity
 *  org.apache.http.entity.mime.MultipartEntityBuilder
 *  org.apache.http.impl.client.CloseableHttpClient
 *  org.apache.http.impl.client.HttpClients
 */
package pisi.unitedmeows.violentcat.utils;

import com.google.gson.JsonObject;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import pisi.unitedmeows.violentcat.bot.DiscordBot;
import pisi.unitedmeows.violentcat.shared.DiscordClient;
import pisi.unitedmeows.violentcat.shared.action.Action;
import pisi.unitedmeows.violentcat.shared.holders.shared.etc.FileAttachment;
import pisi.unitedmeows.violentcat.utils.DiscordHelper;
import pisi.unitedmeows.violentcat.utils.Jsons;
import pisi.unitedmeows.violentcat.utils.WebhookMessage;
import pisi.unitedmeows.yystal.utils.Pair;
import pisi.unitedmeows.yystal.web.client.YSimpleWebClient;

public class Webhook {
    protected String id;
    protected String url;
    protected String guildId;
    protected String channelId;
    protected String name;
    protected String avatar;
    protected String applicationId;
    protected boolean initialized = false;

    public static Webhook of(String _url) {
        return new Webhook(_url);
    }

    protected Webhook(String _url) {
        this.url = _url;
        if (!this.url.startsWith("https")) {
            this.url = "https://" + this.url;
        }
    }

    public Webhook info() {
        Pair<String, String> info = Webhook.webhookInfo(this.url);
        YSimpleWebClient simpleWebClient = new YSimpleWebClient();
        String result = simpleWebClient.downloadString(DiscordHelper.route("/webhooks/%s/%s", info.item1(), info.item2()));
        JsonObject json = Jsons.parser.parse(result).getAsJsonObject();
        this.id = Jsons.getString(json.get("name"));
        this.name = Jsons.getString(json.get("name"));
        this.avatar = Jsons.getString(json.get("avatar"));
        this.channelId = Jsons.getString(json.get("channel_id"));
        this.guildId = Jsons.getString(json.get("guild_id"));
        this.applicationId = Jsons.getString(json.get("application_id"));
        this.initialized = true;
        return this;
    }

    public String id() {
        if (!this.initialized) {
            this.info();
        }
        return this.id;
    }

    public String url() {
        if (!this.initialized) {
            this.info();
        }
        return this.url;
    }

    public String guildId() {
        if (!this.initialized) {
            this.info();
        }
        return this.guildId;
    }

    public String channelId() {
        if (!this.initialized) {
            this.info();
        }
        return this.channelId;
    }

    public String name() {
        if (!this.initialized) {
            this.info();
        }
        return this.name;
    }

    public String avatar() {
        if (!this.initialized) {
            this.info();
        }
        return this.avatar;
    }

    public String applicationId() {
        if (!this.initialized) {
            this.info();
        }
        return this.applicationId;
    }

    public Action<Boolean> delete(DiscordClient client) {
        if (client.isBot()) {
            return ((DiscordBot)client).deleteWebhook(this.guildId(), this.id());
        }
        return null;
    }

    public void send(WebhookMessage message) {
        try {
            HttpPost post = new HttpPost(this.url);
            post.setHeader("Accept", "application/json");
            if (!message.files().isEmpty()) {
                MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                int count = 0;
                for (FileAttachment attachment : message.files()) {
                    builder.addBinaryBody("file[" + count++ + "]", attachment.file(), ContentType.DEFAULT_BINARY, attachment.name());
                }
                post.setEntity(builder.build());
            } else {
                post.setHeader("Content-type", "application/json");
                post.setEntity((HttpEntity)new StringEntity(message.toString()));
            }
            System.out.println(message.toString());
            try (CloseableHttpClient client = HttpClients.createDefault();){
                CloseableHttpResponse response = client.execute((HttpUriRequest)post);
                int responseCode = response.getStatusLine().getStatusCode();
                System.out.println(responseCode);
            }
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    public void send(String content) {
        this.send(WebhookMessage.create().content(content));
    }

    private static Pair<String, String> webhookInfo(String _url) {
        String[] split = _url.split("\\/");
        return new Pair<String, String>(split[split.length - 2], split[split.length - 1]);
    }
}

