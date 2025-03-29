/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.exception;

public abstract class YEx {
    public abstract String getMessage();

    public void printMessage() {
        System.err.println(this.getMessage());
    }
}

