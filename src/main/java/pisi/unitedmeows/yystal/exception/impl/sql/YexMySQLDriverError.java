/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.exception.impl.sql;

import pisi.unitedmeows.yystal.exception.YEx;

public class YexMySQLDriverError
extends YEx {
    private String message;

    public YexMySQLDriverError(String error) {
        this.message = error;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

