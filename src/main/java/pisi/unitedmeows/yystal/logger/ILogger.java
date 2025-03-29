/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.logger;

public interface ILogger {
    public void warn(String var1);

    public void info(String var1);

    public void print(String var1);

    public void fatal(String var1);

    public void debug(String var1);

    public void warn(String var1, Object ... var2);

    public void info(String var1, Object ... var2);

    public void print(String var1, Object ... var2);

    public void fatal(String var1, Object ... var2);

    public void debug(String var1, Object ... var2);

    public void log(Enum<?> var1, String var2);
}

