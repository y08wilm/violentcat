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

public class EmbedMedia {
    @Expose
    private String url;
    @Expose
    @SerializedName(value="proxy_url")
    private String proxyUrl;
    @Expose
    private int width;
    @Expose
    private int height;

    public EmbedMedia width(int _width) {
        this.width = _width;
        return this;
    }

    public EmbedMedia height(int _height) {
        this.height = _height;
        return this;
    }

    public EmbedMedia proxyUrl(String _proxyUrl) {
        this.proxyUrl = _proxyUrl;
        return this;
    }

    public EmbedMedia url(String _url) {
        this.url = _url;
        return this;
    }
}

