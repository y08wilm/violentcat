/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.parallel;

public class Promise {
    private boolean valid = true;

    public void stop() {
        this.valid = false;
    }

    public boolean isValid() {
        return this.valid;
    }
}

