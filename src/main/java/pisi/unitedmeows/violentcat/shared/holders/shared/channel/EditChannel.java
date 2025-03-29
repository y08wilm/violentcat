/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.annotations.Expose
 *  com.google.gson.annotations.SerializedName
 */
package pisi.unitedmeows.violentcat.shared.holders.shared.channel;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import pisi.unitedmeows.violentcat.shared.DiscordClient;
import pisi.unitedmeows.violentcat.shared.action.Action;
import pisi.unitedmeows.violentcat.shared.action.Ratelimits;
import pisi.unitedmeows.violentcat.shared.holders.shared.channel.Channel;
import pisi.unitedmeows.violentcat.shared.holders.shared.channel.TextChannel;
import pisi.unitedmeows.violentcat.shared.holders.shared.channel.VoiceChannel;
import pisi.unitedmeows.violentcat.shared.holders.shared.channel.etc.VideoQuality;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.ChannelType;
import pisi.unitedmeows.violentcat.utils.DiscordHelper;
import pisi.unitedmeows.violentcat.utils.GsonWrap;
import pisi.unitedmeows.violentcat.utils.Jsons;

public class EditChannel<X extends Channel> {
    private X channel;
    @Expose
    private String name;
    @Expose
    private int position;
    @Expose
    private String topic;
    @Expose
    private boolean nsfw;
    @Expose
    @SerializedName(value="rate_limit_per_user")
    private int rateLimitPerUser = -1;
    @Expose
    private int bitrate = -1;
    @Expose
    @SerializedName(value="user_limit")
    private int userLimit = -1;
    @Expose
    @SerializedName(value="parent_id")
    private String parentId;
    @Expose
    @SerializedName(value="default_auto_archive_duration")
    private int defaultAutoArchiveDuration = -1;
    @Expose
    private int flags;
    @Expose
    @SerializedName(value="video_quality_mode")
    private VideoQuality videoQualityMode;
    private DiscordClient bot;

    public EditChannel(X _channel, DiscordClient _bot) {
        this.channel = _channel;
        this.position = ((Channel)this.channel).position;
        this.bot = _bot;
    }

    public String toString() {
        ChannelType type;
        JsonObject json = new JsonObject();
        if (!((Channel)this.channel).name().equals(this.name)) {
            json.addProperty("name", this.name);
        }
        if (((Channel)this.channel).position != this.position) {
            json.addProperty("position", (Number)this.position);
        }
        if (((Channel)this.channel).flags != this.flags) {
            json.addProperty("flags", (Number)this.flags);
        }
        if (((Channel)this.channel).parentId() != null && this.parentId != null && ((Channel)this.channel).parentId().equals(this.parentId)) {
            json.addProperty("parent_id", this.parentId);
        }
        if ((type = ((Channel)this.channel).type()) == ChannelType.TEXT) {
            TextChannel textChannel = ((Channel)this.channel).asTextChannel();
            if (textChannel.isNsfw() != this.nsfw) {
                json.addProperty("nsfw", Boolean.valueOf(this.nsfw));
            }
        } else if (type == ChannelType.VOICE) {
            VoiceChannel voiceChannel = ((Channel)this.channel).asVoiceChannel();
        }
        return Jsons.BUILDER.toJson((JsonElement)json);
    }

    public Action<X> end() {
        if (this.bot.isBot()) {
            return this.bot.actionPool().rateListener(Ratelimits.GUILD).queue(((Channel)this.channel).guildId(), () -> {
                if (this.bot.isBot()) {
                    String parsed = this.toString();
                    String result = this.bot.simpleWebClient().patchRequest(DiscordHelper.route("/channels/%s", ((Channel)this.channel).id), parsed);
                    JsonObject json = Jsons.parser.parse(result).getAsJsonObject();
                    int type = json.get("type").getAsInt();
                    return GsonWrap.parse(ChannelType.from(type).type(), json).result();
                }
                return null;
            });
        }
        return null;
    }

    public EditChannel name(String _name) {
        this.name = _name;
        return this;
    }

    public EditChannel position(int _position) {
        this.position = _position;
        return this;
    }

    public EditChannel topic(String _topic) {
        this.topic = _topic;
        return this;
    }

    public EditChannel nsfw(boolean _nsfw) {
        this.nsfw = _nsfw;
        return this;
    }

    public EditChannel rateLimitPerUser(int _rateLimitPerUser) {
        this.rateLimitPerUser = _rateLimitPerUser;
        return this;
    }

    public EditChannel bitrate(int _bitrate) {
        this.bitrate = _bitrate;
        return this;
    }

    public EditChannel userLimit(int _userLimit) {
        this.userLimit = _userLimit;
        return this;
    }

    public EditChannel parentId(String _parentId) {
        this.parentId = _parentId;
        return this;
    }

    public EditChannel defaultAutoArchiveDuration(int _defaultAutoArchiveDuration) {
        this.defaultAutoArchiveDuration = _defaultAutoArchiveDuration;
        return this;
    }

    public EditChannel flags(int _flags) {
        this.flags = _flags;
        return this;
    }

    public EditChannel videoQualityMode(VideoQuality _videoQualityMode) {
        this.videoQualityMode = _videoQualityMode;
        return this;
    }
}

