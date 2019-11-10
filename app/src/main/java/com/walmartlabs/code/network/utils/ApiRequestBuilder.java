package com.walmartlabs.code.network.utils;

import java.util.LinkedHashMap;

import okhttp3.MediaType;

public class ApiRequestBuilder {

    private String url;

    private MediaType mediaType;

    private LinkedHashMap<String, String> headersMap;

    private MethodType methodType;

    private NetworkListener networkListener;

    public MethodType getMethodType() {
        return methodType;
    }

    public void setMethodType(MethodType methodType) {
        this.methodType = methodType;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public LinkedHashMap<String, String> getHeadersMap() {
        return headersMap;
    }

    public void setHeadersMap(LinkedHashMap<String, String> headersMap) {
        this.headersMap = headersMap;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public NetworkListener getNetworkListener() {
        return networkListener;
    }

    public void setNetworkListener(NetworkListener networkListener) {
        this.networkListener = networkListener;
    }
}
