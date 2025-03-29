/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.web.client;

import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import pisi.unitedmeows.yystal.YYStal;
import pisi.unitedmeows.yystal.clazz.out;
import pisi.unitedmeows.yystal.web.client.YSimpleWebClient;
import pisi.unitedmeows.yystal.web.client.YWebClient;
import pisi.unitedmeows.yystal.web.client.YWebClientOperation;
import pisi.unitedmeows.yystal.web.client.YWebResponse;

public class YWebClientGetter
extends YWebClientOperation<YWebClientGetter> {
    public YWebClientGetter(String _url, YWebClient webClient) {
        super(webClient);
        this.instance(this);
        this.url = _url;
    }

    @Override
    public YWebResponse run() {
        URL url = YSimpleWebClient.asUrl(this.fullUrl());
        YWebResponse webResponse = new YWebResponse();
        if (url != null) {
            try {
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setReadTimeout(((YWebClient)this.hooked).timeout);
                connection.setConnectTimeout(((YWebClient)this.hooked).timeout);
                ((YWebClient)this.hooked).headers.forEach(connection::addRequestProperty);
                connection.connect();
                out<URL> newUrl = YYStal.out();
                if (YSimpleWebClient.redirectCheck(connection, newUrl)) {
                    url = newUrl.get();
                    return this.run();
                }
                int contentLength = connection.getContentLength();
                ((YWebClient)this.hooked).downloadProgressEvent.fire(0, 0, contentLength);
                byte[] data = new byte[4096];
                try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();){
                    int count;
                    while ((count = connection.getInputStream().read(data)) != -1) {
                        outputStream.write(data, 0, count);
                        double percent = (double)outputStream.size() * 100.0 / (double)contentLength;
                        ((YWebClient)this.hooked).downloadProgressEvent.fire(percent, outputStream.size(), contentLength);
                    }
                    webResponse.responseHeaders = connection.getHeaderFields();
                    webResponse.initialize(outputStream.toByteArray());
                    webResponse.responseCode = connection.getResponseCode();
                    webResponse.success(true);
                    ((YWebClient)this.hooked).downloadFinishedEvent.fire(new Object[0]);
                }
            }
            catch (Exception e) {
                webResponse.success(false);
            }
        }
        return webResponse;
    }
}

