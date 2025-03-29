/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.exception.impl;

import pisi.unitedmeows.yystal.exception.YEx;

public class YexIO
extends YEx {
    private final String message;

    public YexIO(String _message, Object ... args) {
        this.message = String.format(_message, args);
    }

    public YexIO() {
        this("Received IO Exception", new Object[0]);
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

