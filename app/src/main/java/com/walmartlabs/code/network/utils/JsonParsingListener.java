package com.walmartlabs.code.network.utils;

import com.walmartlabs.code.model.ProductDataResponse;

public interface JsonParsingListener {
    void onParseSuccess(ProductDataResponse productDataResponse);
    void onParseFailure(String errorCode);
}
