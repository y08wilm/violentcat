/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.web.client;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import pisi.unitedmeows.yystal.clazz.HookClass;
import pisi.unitedmeows.yystal.exception.YExManager;
import pisi.unitedmeows.yystal.exception.impl.YexUnsupported;
import pisi.unitedmeows.yystal.web.client.YWebClient;
import pisi.unitedmeows.yystal.web.client.YWebResponse;

public abstract class YWebClientOperation<X extends YWebClientOperation>
extends HookClass<YWebClient> {
    protected String url;
    protected String contentType;
    protected X instance;
    protected Map<String, Object> queryMap = new LinkedHashMap<String, Object>();

    public YWebClientOperation(YWebClient client) {
        this.hooked = client;
    }

    public X accept(String _contentType) {
        this.contentType = _contentType;
        return this.instance;
    }

    public X query(String key, Object value) {
        this.queryMap.put(key, value);
        return this.instance;
    }

    protected void clearQuery() {
        this.queryMap.clear();
    }

    protected YWebClientOperation instance(X _instance) {
        this.instance = _instance;
        return this;
    }

    protected static String toUrlEncode(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        map.entrySet().stream().forEach(entry -> {
            try {
                (entry.getValue() == null ? sb.append((String)entry.getKey()) : sb.append((String)entry.getKey()).append('=').append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8.name()))).append('&');
            }
            catch (UnsupportedEncodingException e) {
                YExManager.pop(new YexUnsupported(String.format("Query parameter %s's value not supported", entry)));
            }
        });
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }

    protected String queryAsString() {
        return YWebClientOperation.toUrlEncode(this.queryMap);
    }

    protected String fullUrl() {
        if (!this.queryMap.isEmpty()) {
            return String.format("%s?%s", this.url, this.queryAsString());
        }
        return this.url;
    }

    public abstract YWebResponse run();

    @Override
    protected YWebClient getHooked() {
        return (YWebClient)this.hooked;
    }
}

