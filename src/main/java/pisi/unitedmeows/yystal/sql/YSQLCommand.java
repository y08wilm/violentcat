/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.sql;

import java.util.Date;
import pisi.unitedmeows.yystal.clazz.HookClass;
import pisi.unitedmeows.yystal.hook.YString;

public class YSQLCommand
extends HookClass<String> {
    public YSQLCommand(String input) {
        this.hooked = input;
    }

    public YSQLCommand putString(String data) {
        this.putRaw(String.format("'%s'", data));
        return this;
    }

    public YSQLCommand putInt(int data) {
        return this.putRaw(data);
    }

    public YSQLCommand putDouble(double data) {
        return this.putRaw(data);
    }

    public YSQLCommand putFloat(float data) {
        return this.putRaw(Float.valueOf(data));
    }

    public YSQLCommand putBool(boolean bool) {
        return this.putRaw(bool ? 1 : 0);
    }

    public YSQLCommand putDate(Date date) {
        return this;
    }

    public YSQLCommand put(Object data) {
        if (data instanceof String) {
            return this.putString((String)data);
        }
        if (data instanceof Integer) {
            return this.putInt((Integer)data);
        }
        if (data instanceof Boolean) {
            return this.putBool((Boolean)data);
        }
        return this.putRaw(data);
    }

    public YSQLCommand putRaw(Object data) {
        this.hooked = new YString((String)this.hooked).replaceFirst('^', String.valueOf(data)).toString();
        return this;
    }

    @Override
    public String getHooked() {
        return (String)super.getHooked();
    }
}

