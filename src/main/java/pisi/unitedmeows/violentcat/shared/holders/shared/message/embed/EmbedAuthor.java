/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.Expose
 *  com.google.gson.annotations.SerializedName
 */
package pisi.unitedmeows.violentcat.shared.holders.shared.message.embed;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmbedAuthor {
    @Expose
    private String name;
    @Expose
    private String url;
    @Expose
    @SerializedName(value="icon_url")
    private String iconUrl;
    @Expose
    @SerializedName(value="proxy_icon_url")
    private String proxyIconUrl;

    public EmbedAuthor name(String _name) {
        this.name = _name;
        return this;
    }

    public EmbedAuthor url(String _url) {
        this.url = _url;
        return this;
    }

    public EmbedAuthor iconUrl(String _iconUrl) {
        this.iconUrl = _iconUrl;
        return this;
    }

    public EmbedAuthor proxyIconUrl(String _proxyIconUrl) {
        this.proxyIconUrl = _proxyIconUrl;
        return this;
    }
}

