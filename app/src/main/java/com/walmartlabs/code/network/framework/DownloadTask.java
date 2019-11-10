package com.walmartlabs.code.network.framework;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.walmartlabs.code.network.utils.ApiRequestBuilder;
import com.walmartlabs.code.network.utils.MethodType;
import com.walmartlabs.code.utils.Constants;
import com.walmartlabs.code.network.utils.NetworkListener;
import com.walmartlabs.code.network.utils.NetworkResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class DownloadTask extends AsyncTask<Void,Void, Message> {

    private static final String TAG = "DownloadTask";

    private ApiRequestBuilder mApiRequestBuilder;

    private Headers mHeaders;

    private MediaType mMediaType;

    private MethodType mMethodType;

    private NetworkResponse mNetworkResponse;

    private OkHttpClient okHttpClient;

    private static final int TIME_OUT_INTERVAL = 30;

    public DownloadTask(ApiRequestBuilder apiRequestBuilder) {
        mApiRequestBuilder = apiRequestBuilder;

        LinkedHashMap<String, String> headerMap = apiRequestBuilder.getHeadersMap();
        if (headerMap != null && headerMap.size() > 0) {
            mHeaders = Headers.of(headerMap);
        }

        mMediaType = apiRequestBuilder.getMediaType();
        mMethodType = apiRequestBuilder.getMethodType();
        mNetworkResponse = new NetworkResponse();
    }

    /**
     * This method will execute the http get request
     *
     * @return OKhttp Response
     */
    private Response processGetRequest() {
        Response response = null;

        try {
            response = okHttpClient.newCall(httpGetRequest()).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * This method created HTTP get request.
     *
     * @return Request for HTTP client.
     */
    private Request httpGetRequest() {
        Request.Builder builder = new Request.Builder().url(mApiRequestBuilder.getUrl());
        if (mHeaders != null && mHeaders.size() > 0) {
            builder.headers(mHeaders);
        }
        return builder.build();
    }

    @Override
    protected Message doInBackground(Void... voids) {
        Response response = null;
        OkHttpClient.Builder builder = OkHttpClientManager.getInstance().getOkHttpClient().newBuilder();
        builder.readTimeout(TIME_OUT_INTERVAL, TimeUnit.SECONDS);
        builder.writeTimeout(TIME_OUT_INTERVAL, TimeUnit.SECONDS);
        mNetworkResponse.setUrl(mApiRequestBuilder.getUrl());

        okHttpClient = builder.build();

        switch (mMethodType) {
            case GET:
                response = processGetRequest();
                break;
            case PUT:
            case POST:
                break;
        }

        Message message = Message.obtain();

        if (response != null) {
            ResponseBody responseBody = response.body();
            if (mMediaType == Constants.JSON) {
                mNetworkResponse.setStringResponse(
                        responseBody != null ? convertInputStreamToString(responseBody.byteStream()) : "");
            }
            mNetworkResponse.setResponseCode(response.code());

            if (response.isSuccessful()) {
                message.obj = mNetworkResponse;
                message.arg1 = Constants.SUCCESS;
            } else {
                NetworkError networkError = new NetworkError();
                networkError.setErrorCode(String.valueOf(response.code()));
                message.obj = networkError;
            }
            mNetworkResponse.setResponseCode(response.code());
            responseBody.close();
        } else {
            Log.d(TAG, "doInBackground: Failed with null");
            NetworkError networkError = new NetworkError();
            networkError.setErrorCode("123");
            networkError.setMessage("Network Error");
            message.obj = networkError;
        }
        return message;
    }

    @Override
    protected void onPostExecute(Message message) {
        super.onPostExecute(message);
        handler.sendMessage(message);
    }

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            Object response = msg.obj;
            NetworkListener networkListener = mApiRequestBuilder.getNetworkListener();
            if (networkListener == null) {
                return;
            }
            if (response instanceof NetworkError) {
                networkListener.onFailure((NetworkError) response);
            } else if (response instanceof NetworkResponse) {
                networkListener.onSuccess(mNetworkResponse);
            }
        }
    };

    /**
     * Convert input stream into string
     *
     * @param inputStream {@link InputStream}
     * @return Converted String
     */
    private String convertInputStreamToString(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            Log.e(TAG, "Exception = " + e.getMessage());
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                Log.e(TAG, "Exception = " + e.getMessage());
            }
        }
        return sb.toString();
    }
}
