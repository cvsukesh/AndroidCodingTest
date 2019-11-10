package com.walmartlabs.code.network.framework;

import okhttp3.OkHttpClient;

public class OkHttpClientManager {

    /*
     * A Shared OKHttp instance, required for network calls.
     */
    private OkHttpClient okHttpClient = new OkHttpClient();

    private static OkHttpClientManager okHttpClientManager = null;

    static OkHttpClientManager getInstance() {
        if (okHttpClientManager == null) {
            okHttpClientManager = new OkHttpClientManager();
        }
        return okHttpClientManager;
    }

    OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }


}
