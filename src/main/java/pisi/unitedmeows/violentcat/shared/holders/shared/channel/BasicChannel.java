/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.Expose
 *  com.google.gson.annotations.SerializedName
 */
package pisi.unitedmeows.violentcat.shared.holders.shared.channel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BasicChannel {
    @Expose
    protected int version;
    @Expose
    protected int type;
    @Expose
    protected int position;
    @Expose
    protected String name;
    @Expose
    protected String id;
    @Expose
    protected int flags;
    @Expose
    protected boolean nsfw;
    @Expose
    @SerializedName(value="last_message_id")
    protected String lastMessageId;
    @Expose
    @SerializedName(value="parent_id")
    protected String parentId;

    public String toString() {
        StringBuilder sb = new StringBuilder("BasicChannel{");
        sb.append("version=").append(this.version);
        sb.append(", type=").append(this.type);
        sb.append(", position=").append(this.position);
        sb.append(", name='").append(this.name).append('\'');
        sb.append(", id='").append(this.id).append('\'');
        sb.append(", flags=").append(this.flags);
        sb.append(", nsfw=").append(this.nsfw);
        sb.append(", lastMessageId='").append(this.lastMessageId).append('\'');
        sb.append(", parentId='").append(this.parentId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

