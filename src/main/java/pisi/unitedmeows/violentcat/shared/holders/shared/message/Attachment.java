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

public class Attachment {
    @Expose
    private String url;
    @Expose
    private int size;
    @Expose
    @SerializedName(value="proxy_url")
    private String proxyUrl;
    @Expose
    private String id;
    @Expose
    @SerializedName(value="filename")
    private String fileName;
    @Expose
    @SerializedName(value="content_type")
    private String contentType;

    public String url() {
        return this.url;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Attachment{");
        sb.append("url='").append(this.url).append('\'');
        sb.append(", size=").append(this.size);
        sb.append(", proxyUrl='").append(this.proxyUrl).append('\'');
        sb.append(", id='").append(this.id).append('\'');
        sb.append(", fileName='").append(this.fileName).append('\'');
        sb.append(", contentType='").append(this.contentType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

