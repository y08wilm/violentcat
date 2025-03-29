/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.exception.impl;

import pisi.unitedmeows.yystal.exception.YEx;

public class YexNullPtr
extends YEx {
    private final String message;

    public YexNullPtr(String _message) {
        this.message = _message;
    }

    public YexNullPtr() {
        this("Received Null Exception");
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

