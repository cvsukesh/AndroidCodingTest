package com.walmartlabs.code.utils;

import okhttp3.MediaType;

public interface Constants {

    String BASE_URL = "https://mobile-tha-server.firebaseapp.com";

    String PRODUCT_LIST = "/walmartproducts/";

    MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    int SUCCESS = 200;

    String POSITION = "Position";
}
