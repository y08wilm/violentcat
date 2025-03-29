/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.exception.impl.sql;

import pisi.unitedmeows.yystal.exception.YEx;

public class YexSqlError
extends YEx {
    private String message;

    public YexSqlError(String _message) {
        this.message = _message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

