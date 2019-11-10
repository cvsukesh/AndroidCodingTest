package com.walmartlabs.code.network.framework;

import android.os.AsyncTask;
import android.os.Message;
import android.widget.Toast;

import com.google.gson.Gson;
import com.walmartlabs.code.model.ProductDataResponse;
import com.walmartlabs.code.network.utils.JsonParsingListener;
import com.walmartlabs.code.ui.WLApplication;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Task will create from string to json.
 */
public class ParseJsonTask extends AsyncTask<String, Void, Message> {

    private JsonParsingListener mJsonParsingListener;

    private Type mResponseType;

    private List<ParseJsonTask> mParseJsonTask = new CopyOnWriteArrayList<>();

    public ParseJsonTask(Type responseType, JsonParsingListener jsonParsingListener) {
        mResponseType = responseType;
        mJsonParsingListener = jsonParsingListener;
        mParseJsonTask.add(this);
    }

    @Override
    protected Message doInBackground(String... response) {
        Object responseData;
        Message message = Message.obtain();
        Gson gson = new Gson();
        responseData = gson.fromJson(response[0], mResponseType);
        message.obj = responseData;
        return message;
    }

    @Override
    protected void onPostExecute(Message message) {
        super.onPostExecute(message);
        mParseJsonTask.remove(this);

        Object object = message.obj;
        if (object instanceof ProductDataResponse) {
            ProductDataResponse productDataResponse = (ProductDataResponse) object;
            mJsonParsingListener.onParseSuccess(productDataResponse);
        } else {
            mJsonParsingListener.onParseFailure("");
            Toast.makeText(WLApplication.getAppContext(), "Parsing error ", Toast.LENGTH_SHORT).show();
        }
    }
}
