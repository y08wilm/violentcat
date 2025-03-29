/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.web.client;

import java.util.HashMap;
import java.util.Map;
import pisi.unitedmeows.yystal.clazz.event;
import pisi.unitedmeows.yystal.web.client.YSimpleWebClient;
import pisi.unitedmeows.yystal.web.client.YWebClientBuilder;
import pisi.unitedmeows.yystal.web.client.YWebClientGetter;
import pisi.unitedmeows.yystal.web.client.YWebClientPoster;
import pisi.unitedmeows.yystal.web.events.WCDownloadFinished;
import pisi.unitedmeows.yystal.web.events.WCDownloadProgress;

public class YWebClient {
    public event<WCDownloadProgress> downloadProgressEvent = new event();
    public event<WCDownloadFinished> downloadFinishedEvent = new event();
    protected Map<String, String> headers = YSimpleWebClient.createDefaultHeaders();
    protected static final String DEFAULT_USER_AGENT = YSimpleWebClient.randomUserAgent();
    protected Map<String, String> cookies = new HashMap<String, String>();
    protected int timeout = 10000;

    protected YWebClient() {
    }

    public void newSession() {
        this.headers = YSimpleWebClient.createDefaultHeaders();
        this.clearCookies();
    }

    public static YWebClientBuilder builder() {
        return new YWebClientBuilder();
    }

    public YWebClientGetter get(String url) {
        return new YWebClientGetter(url, this);
    }

    public YWebClientPoster post(String url) {
        return new YWebClientPoster(url, this);
    }

    public int timeout() {
        return this.timeout;
    }

    public YWebClient timeout(int _timeout) {
        this.timeout = _timeout;
        return this;
    }

    public void cookie(String key, String value) {
        this.cookies.put(key, value);
    }

    public void clearCookies() {
        this.cookies.clear();
    }

    public String cookie(String key) {
        return this.cookies.getOrDefault(key, null);
    }

    public void header(String name, String value) {
        this.headers.put(name, value);
    }

    public void userAgent(String value) {
        this.header("User-Agent", value);
    }

    public String userAgent() {
        return this.headers.getOrDefault("User-Agent", null);
    }

    public Map<String, String> headers() {
        return this.headers;
    }
}

