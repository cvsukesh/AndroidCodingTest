package com.walmartlabs.code.network.utils;

import com.walmartlabs.code.network.framework.NetworkError;

public interface NetworkListener {

    void onSuccess(NetworkResponse networkResponse);

    void onFailure(NetworkError networkError);
}
