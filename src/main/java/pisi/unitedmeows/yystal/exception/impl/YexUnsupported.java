/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.exception.impl;

import pisi.unitedmeows.yystal.exception.YEx;

public class YexUnsupported
extends YEx {
    private String message;

    public YexUnsupported(String _message) {
        this.message = _message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

