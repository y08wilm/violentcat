/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.web.client;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import pisi.unitedmeows.yystal.YYStal;
import pisi.unitedmeows.yystal.clazz.out;
import pisi.unitedmeows.yystal.web.client.YSimpleWebClient;
import pisi.unitedmeows.yystal.web.client.YWebClient;
import pisi.unitedmeows.yystal.web.client.YWebClientOperation;
import pisi.unitedmeows.yystal.web.client.YWebResponse;

public class YWebClientPoster
extends YWebClientOperation<YWebClientPoster> {
    private Map<String, String> arguments;
    private Map<String, byte[]> smallFiles;
    private Map<String, File> files;
    private Map<String, Consumer<InputStream>> virtualFiles;
    String boundary = UUID.randomUUID().toString();
    byte[] boundaryBytes = ("--" + this.boundary + "\r\n").getBytes(StandardCharsets.UTF_8);
    byte[] finishBoundaryBytes = ("--" + this.boundary + "--").getBytes(StandardCharsets.UTF_8);

    public YWebClientPoster attachFiles(String name, Consumer<InputStream> stream) {
        this.virtualFiles.put(name, stream);
        return this;
    }

    public YWebClientPoster attachFile(String name, File file) {
        this.files.put(name, file);
        return this;
    }

    @Deprecated
    public YWebClientPoster attachFile(String name, byte[] bytes) {
        this.smallFiles.put(name, bytes);
        return this;
    }

    public YWebClientPoster(String _url, YWebClient webClient) {
        super(webClient);
        this.contentType = "application/json";
        this.instance(this);
        this.url = _url;
        this.arguments = new LinkedHashMap<String, String>();
        this.smallFiles = new LinkedHashMap<String, byte[]>();
        this.virtualFiles = new LinkedHashMap<String, Consumer<InputStream>>();
        this.files = new LinkedHashMap<String, File>();
    }

    public YWebClientPoster put(String key, String value) {
        this.arguments.put(key, value);
        return this;
    }

    @Override
    public YWebResponse run() {
        YWebResponse webResponse;
        block35: {
            URL url = YSimpleWebClient.asUrl(this.fullUrl());
            webResponse = new YWebResponse();
            if (url != null) {
                try {
                    int totalFileCount = this.smallFiles.size() + this.virtualFiles.size() + this.files.size();
                    URLConnection connection = url.openConnection();
                    HttpURLConnection http = (HttpURLConnection)connection;
                    http.setRequestMethod("POST");
                    http.setDoOutput(true);
                    ((YWebClient)this.hooked).headers.forEach(connection::addRequestProperty);
                    http.setChunkedStreamingMode(0);
                    http.setRequestProperty("Content-Type", "multipart/form-data; charset=UTF-8; boundary=" + this.boundary);
                    http.setRequestProperty("charset", "utf-8");
                    http.setInstanceFollowRedirects(false);
                    http.setUseCaches(false);
                    http.connect();
                    try (OutputStream os = http.getOutputStream();){
                        Iterator<Map.Entry<String, String>> iterator = this.arguments.entrySet().iterator();
                        os.write(this.boundaryBytes);
                        while (iterator.hasNext()) {
                            Map.Entry<String, String> entry = iterator.next();
                            this.sendField(os, entry.getKey(), entry.getValue());
                            if (!iterator.hasNext()) continue;
                            os.write(this.boundaryBytes);
                        }
                        boolean currentFileIndex = false;
                        for (Map.Entry<String, byte[]> smallFile : this.smallFiles.entrySet()) {
                            this.sendFileStart(os, smallFile.getKey());
                            os.write(smallFile.getValue());
                            this.sendFileEnd(os);
                        }
                        os.write(this.finishBoundaryBytes);
                    }
                    out<URL> newUrl = YYStal.out();
                    if (YSimpleWebClient.redirectCheck(http, newUrl)) {
                        Map<String, Object> queryMap = this.queryMap;
                        this.queryMap = new LinkedHashMap<String, Object>();
                        this.url = newUrl.get().toString();
                        YWebResponse response = this.run();
                        this.queryMap = queryMap;
                        return response;
                    }
                    webResponse.responseHeaders = connection.getHeaderFields();
                    int contentLength = connection.getContentLength();
                    if (http.getResponseCode() == 200) {
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
                            webResponse.responseCode = http.getResponseCode();
                            webResponse.success(true);
                            ((YWebClient)this.hooked).downloadFinishedEvent.fire(new Object[0]);
                            break block35;
                        }
                    }
                    if (http.getResponseCode() == 204) {
                        webResponse.success(false);
                    } else {
                        webResponse.success(false);
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    webResponse.success(false);
                }
            }
        }
        return webResponse;
    }

    private void sendFileStart(OutputStream out2, String name) throws IOException {
        String o = "Content-Disposition: form-data; name=\"" + URLEncoder.encode(name, "UTF-8") + "\"; filename=\"" + URLEncoder.encode(name, "UTF-8") + "\"\r\n\r\n";
        out2.write(o.getBytes(StandardCharsets.UTF_8));
    }

    private void sendFileEnd(OutputStream out2) throws IOException {
        out2.write("\r\n".getBytes(StandardCharsets.UTF_8));
    }

    private void sendField(OutputStream out2, String name, String field) throws IOException {
        String o = "Content-Disposition: form-data; name=\"" + URLEncoder.encode(name, "UTF-8") + "\"\r\n\r\n";
        out2.write(o.getBytes(StandardCharsets.UTF_8));
        out2.write(URLEncoder.encode(field, "UTF-8").getBytes(StandardCharsets.UTF_8));
        out2.write("\r\n".getBytes(StandardCharsets.UTF_8));
    }
}

