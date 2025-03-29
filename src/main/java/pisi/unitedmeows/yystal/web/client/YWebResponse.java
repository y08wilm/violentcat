/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.web.client;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YWebResponse {
    private long took;
    protected Map<String, List<String>> responseHeaders = new HashMap<String, List<String>>();
    private byte[] raw;
    private String stringValue;
    private boolean success;
    protected int responseCode = -1;

    public Map<String, List<String>> responseHeaders() {
        return this.responseHeaders;
    }

    protected void initialize(byte[] data) {
        this.raw = data;
    }

    public String asString() {
        if (this.raw == null || this.raw.length == 0) {
            return "";
        }
        if (this.stringValue == null) {
            this.stringValue = new String(this.raw, StandardCharsets.UTF_8);
        }
        return this.stringValue;
    }

    protected YWebResponse success(boolean _success) {
        this.success = _success;
        return this;
    }

    public boolean isSuccess() {
        return this.success && (this.responseCode >= 200 && this.responseCode <= 299 || this.responseCode == 302);
    }

    public int responseCode() {
        return this.responseCode;
    }

    public byte[] raw() {
        return this.raw;
    }

    public long took() {
        return this.took;
    }
}

