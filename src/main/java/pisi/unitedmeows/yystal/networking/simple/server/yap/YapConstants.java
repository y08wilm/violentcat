/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.networking.simple.server.yap;

import java.nio.charset.StandardCharsets;

public enum YapConstants {
    ASK_INTRODUCTION(";;YAP_ASK_INTRODUCTION".getBytes(StandardCharsets.UTF_8));

    private byte[] data;

    private YapConstants(byte[] _data) {
        this.data = _data;
    }

    public byte[] data() {
        return this.data;
    }
}

