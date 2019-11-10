package com.walmartlabs.code.network.utils;

import okhttp3.MediaType;

public interface NetworkConstants {

    String BASE_URL = "https://mobile-tha-server.firebaseapp.com";

    String PRODUCT_LIST = "/walmartproducts/";

    MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    int SUCCESS = 200;
}
