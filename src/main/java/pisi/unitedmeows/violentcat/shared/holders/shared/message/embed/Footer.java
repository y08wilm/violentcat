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

public class Footer {
    @Expose
    private String text;
    @Expose
    @SerializedName(value="icon_url")
    private String iconUrl;
    @Expose
    @SerializedName(value="proxy_icon_url")
    private String proxyIconUrl;

    public Footer text(String _text) {
        this.text = _text;
        return this;
    }

    public Footer iconUrl(String _iconUrl) {
        this.iconUrl = _iconUrl;
        return this;
    }
}

