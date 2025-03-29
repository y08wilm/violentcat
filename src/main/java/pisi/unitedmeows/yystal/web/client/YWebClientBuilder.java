/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.web.client;

import pisi.unitedmeows.yystal.clazz.HookClass;
import pisi.unitedmeows.yystal.web.client.YWebClient;

public class YWebClientBuilder
extends HookClass<YWebClient> {
    public YWebClientBuilder() {
        this.hooked = new YWebClient();
    }

    public static YWebClient build() {
        return new YWebClient();
    }
}

