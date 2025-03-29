/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.web.client;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import pisi.unitedmeows.yystal.YYStal;
import pisi.unitedmeows.yystal.clazz.event;
import pisi.unitedmeows.yystal.clazz.out;
import pisi.unitedmeows.yystal.exception.YExManager;
import pisi.unitedmeows.yystal.exception.impl.YexBrokenUrlException;
import pisi.unitedmeows.yystal.utils.YRandom;
import pisi.unitedmeows.yystal.web.events.WCDownloadFinished;
import pisi.unitedmeows.yystal.web.events.WCDownloadProgress;

public class YSimpleWebClient {
    private int timeout = 10000;
    private Map<String, String> headers = YSimpleWebClient.createDefaultHeaders();
    protected static final String DEFAULT_USER_AGENT = YSimpleWebClient.randomUserAgent();
    private Map<String, List<String>> responseHeaders = new HashMap<String, List<String>>();
    public event<WCDownloadProgress> downloadProgressEvent = new event();
    public event<WCDownloadFinished> downloadFinishedEvent = new event();

    public String downloadString(String url) {
        try {
            return this.downloadString(new URL(url));
        }
        catch (MalformedURLException e) {
            return null;
        }
    }

    public byte[] downloadBytes(String url) {
        try {
            return this.downloadBytes(new URL(url));
        }
        catch (MalformedURLException e) {
            return null;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public byte[] downloadBytes(URL url) {
        try {
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setReadTimeout(this.timeout);
            connection.setConnectTimeout(this.timeout);
            this.headers.forEach(connection::addRequestProperty);
            connection.connect();
            out<URL> newUrl = YYStal.out();
            if (YSimpleWebClient.redirectCheck(connection, newUrl)) {
                return this.downloadBytes(newUrl.get());
            }
            int contentLength = connection.getContentLength();
            this.downloadProgressEvent.fire(0, 0, contentLength);
            byte[] data = new byte[4096];
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();){
                int count;
                while ((count = connection.getInputStream().read(data)) != -1) {
                    outputStream.write(data, 0, count);
                    double percent = (double)outputStream.size() * 100.0 / (double)contentLength;
                    this.downloadProgressEvent.fire(percent, outputStream.size(), contentLength);
                }
                this.responseHeaders = connection.getHeaderFields();
                byte[] result = outputStream.toByteArray();
                this.downloadFinishedEvent.fire(new Object[0]);
                byte[] byArray = result;
                return byArray;
            }
        }
        catch (Exception e) {
            return null;
        }
    }

    public boolean downloadFile(String url, File file) {
        try {
            return this.downloadFile(new URL(url), file);
        }
        catch (MalformedURLException e) {
            return false;
        }
    }

    public boolean downloadFile(URL url, File file) {
        try {
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setReadTimeout(this.timeout);
            connection.setConnectTimeout(this.timeout);
            this.headers.forEach(connection::addRequestProperty);
            connection.connect();
            out<URL> newUrl = YYStal.out();
            if (YSimpleWebClient.redirectCheck(connection, newUrl)) {
                return this.downloadFile(newUrl.get(), file);
            }
            byte[] data = new byte[4096];
            int contentLength = connection.getContentLength();
            int totalDownloaded = 0;
            this.downloadProgressEvent.fire(0, 0, contentLength);
            try (FileOutputStream outputStream = new FileOutputStream(file);){
                int count;
                while ((count = connection.getInputStream().read(data)) != -1) {
                    outputStream.write(data, 0, count);
                    this.downloadProgressEvent.fire((totalDownloaded += count) * 100 / contentLength, totalDownloaded, contentLength);
                    outputStream.flush();
                }
                outputStream.flush();
                this.downloadFinishedEvent.fire(new Object[0]);
                this.responseHeaders = connection.getHeaderFields();
            }
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public String downloadString(URL url) {
        try {
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setReadTimeout(this.timeout);
            connection.setConnectTimeout(this.timeout);
            this.headers.forEach(connection::addRequestProperty);
            connection.connect();
            out<URL> newUrl = YYStal.out();
            if (YSimpleWebClient.redirectCheck(connection, newUrl)) {
                return this.downloadString(newUrl.get());
            }
            this.responseHeaders = connection.getHeaderFields();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));){
                String string = reader.lines().collect(Collectors.joining("\n"));
                return string;
            }
        }
        catch (Exception e) {
            return null;
        }
    }

    public String postRequest(String url, Map<String, String> args, String contentType, String token) {
        try {
            return this.postRequest(new URL(url), args, contentType, token);
        }
        catch (MalformedURLException e) {
            return null;
        }
    }

    public String postRequest(URL url, Map<String, String> args, String token) {
        return this.postRequest(url, args, "text/html", token);
    }

    public String postRequest(String url, Map<String, String> args, String token) {
        try {
            return this.postRequest(new URL(url), args, "text/html", token);
        }
        catch (Exception ex) {
            return null;
        }
    }

    public String postRequest(URL url, Map<String, String> args, String contentType, String token) {
        try {
            StringJoiner stringJoiner = new StringJoiner("&");
            for (Map.Entry<String, String> entry : args.entrySet()) {
                stringJoiner.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "=" + URLEncoder.encode(entry.getValue(), "UTF-8"));
            }
            return this.postRequest(url, stringJoiner.toString(), contentType, token);
        }
        catch (Exception ex) {
            return null;
        }
    }

    public String postRequest(String url, String value, String contentType, String token) {
        try {
            return this.postRequest(new URL(url), value, contentType, token);
        }
        catch (MalformedURLException e) {
            return null;
        }
    }

    public String postRequest(String url, String value, String token) {
        try {
            return this.postRequest(new URL(url), value, "application/json", token);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String postRequest(URL url, String value, String contentType, String token) {
        try {
            URLConnection connection = url.openConnection();
            HttpURLConnection http = (HttpURLConnection)connection;
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            this.headers.forEach(connection::addRequestProperty);
            byte[] out2 = value.getBytes(StandardCharsets.UTF_8);
            int length = out2.length;
            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", contentType);
            http.setRequestProperty("charset", "utf-8");
            http.setRequestProperty("Content-Length", Integer.toString(length));
            if (token != null) {
                http.setRequestProperty("Authorization", token);
            }
            http.setInstanceFollowRedirects(false);
            http.setUseCaches(false);
            http.connect();
            try (OutputStream os = http.getOutputStream();){
                os.write(out2);
            }
            out<URL> newUrl = YYStal.out();
            if (YSimpleWebClient.redirectCheck(http, newUrl)) {
                return this.postRequest(newUrl.get(), value, contentType, token);
            }
            this.responseHeaders = connection.getHeaderFields();
            StringBuilder stringBuilder = new StringBuilder();
            if (http.getResponseCode() == 200) {
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(http.getInputStream()));){
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                }
            } else {
                if (http.getResponseCode() == 204) {
                    return "";
                }
                if (http.getResponseCode() == 400) {
                    return "";
                }
                return null;
            }
            return stringBuilder.toString();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String putRequest(String url, String value, String token) {
        return this.putRequest(url, value, "application/json");
    }

    public String putRequest(String url, String value, String contentType, String token) {
        try {
            return this.putRequest(new URL(url), value, contentType, token);
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }

    public String putRequest(URL url, String value, String token) {
        return this.putRequest(url, value, "application/json", token);
    }

    public String putRequest(URL url, String value, String contentType, String token) {
        try {
            URLConnection connection = url.openConnection();
            HttpURLConnection http = (HttpURLConnection)connection;
            http.setRequestMethod("PUT");
            http.setDoOutput(true);
            this.headers.forEach(connection::addRequestProperty);
            byte[] out2 = value.getBytes(StandardCharsets.UTF_8);
            int length = out2.length;
            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", contentType);
            http.setRequestProperty("charset", "utf-8");
            http.setRequestProperty("Content-Length", Integer.toString(length));
            if (token != null) {
                http.setRequestProperty("Authorization", token);
            }
            http.setInstanceFollowRedirects(false);
            http.setUseCaches(false);
            http.connect();
            try (OutputStream os = http.getOutputStream();){
                os.write(out2);
            }
            out<URL> newUrl = YYStal.out();
            if (YSimpleWebClient.redirectCheck(http, newUrl)) {
                return this.postRequest(newUrl.get(), value, contentType, token);
            }
            this.responseHeaders = connection.getHeaderFields();
            StringBuilder stringBuilder = new StringBuilder();
            if (http.getResponseCode() == 200) {
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(http.getInputStream()));){
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                }
            } else {
                if (http.getResponseCode() == 204) {
                    return "";
                }
                return null;
            }
            return stringBuilder.toString();
        }
        catch (Exception ex) {
            return null;
        }
    }

    public String patchRequest(String url, String value) {
        return this.patchRequest(url, value, "application/json");
    }

    public String patchRequest(String url, String value, String contentType) {
        try {
            return this.patchRequest(new URL(url), value, contentType);
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }

    public String patchRequest(URL url, String value) {
        return this.patchRequest(url, value, "application/json");
    }

    public String patchRequest(URL url, String value, String contentType) {
        try {
            URLConnection connection = url.openConnection();
            HttpURLConnection http = (HttpURLConnection)connection;
            http.setRequestMethod("PATCH");
            http.setDoOutput(true);
            this.headers.forEach(connection::addRequestProperty);
            byte[] out2 = value.getBytes(StandardCharsets.UTF_8);
            int length = out2.length;
            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", contentType);
            http.setRequestProperty("charset", "utf-8");
            http.setRequestProperty("Content-Length", Integer.toString(length));
            http.setInstanceFollowRedirects(false);
            http.setUseCaches(false);
            http.connect();
            try (OutputStream os = http.getOutputStream();){
                os.write(out2);
            }
            out<URL> newUrl = YYStal.out();
            if (YSimpleWebClient.redirectCheck(http, newUrl)) {
                return this.postRequest(newUrl.get(), value, contentType, null);
            }
            this.responseHeaders = connection.getHeaderFields();
            StringBuilder stringBuilder = new StringBuilder();
            if (http.getResponseCode() == 200) {
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(http.getInputStream()));){
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                }
            } else {
                if (http.getResponseCode() == 204) {
                    return "";
                }
                return null;
            }
            return stringBuilder.toString();
        }
        catch (Exception ex) {
            return null;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public String deleteRequest(URL url) {
        try {
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setReadTimeout(this.timeout);
            connection.setConnectTimeout(this.timeout);
            connection.setRequestMethod("DELETE");
            this.headers.forEach(connection::addRequestProperty);
            connection.connect();
            out<URL> newUrl = YYStal.out();
            if (YSimpleWebClient.redirectCheck(connection, newUrl)) {
                return this.downloadString(newUrl.get());
            }
            this.responseHeaders = connection.getHeaderFields();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));){
                String string = reader.lines().collect(Collectors.joining("\n"));
                return string;
            }
        }
        catch (Exception e) {
            return null;
        }
    }

    public String deleteRequest(String url) {
        try {
            return this.deleteRequest(new URL(url));
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }

    public static boolean redirectCheck(HttpURLConnection connection, out<URL> url) {
        block4: {
            try {
                int responseCode = connection.getResponseCode();
                if (responseCode >= 400 || responseCode < 300) break block4;
                String redirectUrl = connection.getHeaderField("Location");
                try {
                    url.set(new URL(redirectUrl));
                }
                catch (Exception ex) {
                    URL newUrl = new URL(connection.getURL().getProtocol() + "://" + connection.getURL().getHost() + redirectUrl);
                    url.set(newUrl);
                }
                return true;
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        return false;
    }

    public Map<String, List<String>> responseHeaders() {
        return this.responseHeaders;
    }

    public void cookie(String cookies) {
        this.headers.put("Cookie", cookies);
    }

    public void appendCookie(String cookie) {
        this.headers.put("Cookie", this.headers.getOrDefault("Cookie", "") + cookie);
    }

    protected static Map<String, String> createDefaultHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("User-Agent", DEFAULT_USER_AGENT);
        return headers;
    }

    public YSimpleWebClient setUserAgent(String userAgent) {
        this.headers.put("User-Agent", userAgent);
        return this;
    }

    protected static String randomUserAgent() {
        String[] userAgents = new String[]{"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_3; rv:95.0) Gecko/20010101 Firefox/95.0", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4667.82 Safari/537.36", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:93.0) Gecko/20110101 Firefox/93.0", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4648.150 Safari/537.36", "Mozilla/5.0 (X11; U; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4655.89 Safari/537.36", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4692.110 Safari/537.36", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4661.55 Safari/537.36", "Mozilla/5.0 (Windows NT 11.0; Win64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4641.165 Safari/537.36", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.174 Safari/537.36"};
        return userAgents[YRandom.BASIC.nextInt(userAgents.length)];
    }

    public YSimpleWebClient setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public YSimpleWebClient header(String header, String value) {
        this.headers.put(header, value);
        return this;
    }

    protected static URL asUrl(String url) {
        try {
            return new URL(url);
        }
        catch (MalformedURLException ex) {
            YExManager.pop(new YexBrokenUrlException(url));
            return null;
        }
    }

    public Map<String, String> headers() {
        return this.headers;
    }
}

