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
import pisi.unitedmeows.violentcat.shared.ClientType;
import pisi.unitedmeows.violentcat.shared.action.Action;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.Guild;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.GuildPreview;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.Message;
import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;

public class GuildMessage
extends Message {
    @Expose
    @SerializedName(value="guild_id")
    private String guildId;

    @Deprecated
    @OnlyLibCalls
    public GuildMessage() {
    }

    public GuildMessage(String message) {
        super(message);
    }

    public Action<Guild> guild() {
        if (this.owner().type() == ClientType.BOT) {
            return this.botInstance().guild(this.guildId);
        }
        return null;
    }

    public Action<GuildPreview> guildPreview() {
        if (this.owner().type() == ClientType.BOT) {
            return this.botInstance().guildPreview(this.guildId);
        }
        return null;
    }
}

