/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.Expose
 */
package pisi.unitedmeows.violentcat.shared.holders.shared.message.embed;

import com.google.gson.annotations.Expose;

public enum EmbedType {
    RICH("rich"),
    IMAGE("image"),
    VIDEO("video"),
    GIFV("gifv"),
    ARTICLE("article"),
    LINK("link");

    @Expose
    private String code;

    private EmbedType(String _code) {
        this.code = _code;
    }

    public String code() {
        return this.code;
    }
}

