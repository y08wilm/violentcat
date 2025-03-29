/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.exception.impl;

import pisi.unitedmeows.yystal.exception.YEx;

public class YexBrokenUrlException
extends YEx {
    private String url;

    public YexBrokenUrlException(String _url) {
        this.url = _url;
    }

    public String url() {
        return this.url;
    }

    @Override
    public String getMessage() {
        return String.format("Broken url format %s", this.url);
    }
}

