package com.haoyue.auxiliary;

import android.util.Log;
import android.webkit.CookieManager;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by chen1 on 2018/1/15.
 * TO DO:将系统级Cookie(比如`new URL(...).openConnection()`的Cookie) 同步到 WebView
 */

public class WebkitCookieHandler extends CookieHandler {

    private static final String TAG = WebkitCookieHandler.class.getSimpleName();
    private CookieManager wcm;
    public WebkitCookieHandler() {
        this.wcm = CookieManager.getInstance();
    }
    @Override
    public void put(URI uri, Map<String, List<String>> headers) throws IOException {
        if ((uri == null) || (headers == null)) {
            return;
        }
        String url = uri.toString();
        for (String headerKey : headers.keySet()) {
            if ((headerKey == null) || !(headerKey.equalsIgnoreCase("set-cookie2") || headerKey.equalsIgnoreCase("set-cookie"))) {
                continue;
            }
            for (String headerValue : headers.get(headerKey)) {
                Log.e(TAG, headerKey + ": " + headerValue);
                this.wcm.setCookie(url, headerValue);
            }
        }
    }
    @Override
    public Map<String, List<String>> get(URI uri, Map<String, List<String>> headers) throws IOException {
        if ((uri == null) || (headers == null)) {
            throw new IllegalArgumentException("Argument is null");
        }
        String url = uri.toString();
        String cookie = this.wcm.getCookie(url);
        Log.e(TAG, "cookie: " + cookie);
        if (cookie != null) {
            return Collections.singletonMap("Cookie", Arrays.asList(cookie));
        } else {
            return Collections.emptyMap();
        }
    }
}
