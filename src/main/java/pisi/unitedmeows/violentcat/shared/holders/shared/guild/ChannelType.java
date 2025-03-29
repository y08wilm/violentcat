/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.violentcat.shared.holders.shared.guild;

import pisi.unitedmeows.violentcat.shared.holders.shared.channel.CategoryChannel;
import pisi.unitedmeows.violentcat.shared.holders.shared.channel.Channel;
import pisi.unitedmeows.violentcat.shared.holders.shared.channel.TextChannel;
import pisi.unitedmeows.violentcat.shared.holders.shared.channel.VoiceChannel;

public enum ChannelType {
    TEXT(0, TextChannel.class),
    DM(1),
    VOICE(2, VoiceChannel.class),
    GROUP(3),
    CATEGORY(4, CategoryChannel.class),
    ANNOUNCEMENT(5),
    ANNOUNCEMENT_THREAD(10),
    PUBLIC_THREAD(11),
    PRIVATE_THREAD(12),
    STAGE_VOICE(13),
    DIRECTORY(14),
    FORUM(15);

    int id;
    Class<? extends Channel> type;

    private ChannelType(int _id, Class<? extends Channel> _type) {
        this.id = _id;
        this.type = _type;
    }

    private ChannelType(int _id) {
        this(_id, null);
    }

    public static ChannelType from(int id) {
        for (ChannelType type : ChannelType.values()) {
            if (type.id != id) continue;
            return type;
        }
        return null;
    }

    public Class<? extends Channel> type() {
        return this.type;
    }

    public int id() {
        return this.id;
    }
}

