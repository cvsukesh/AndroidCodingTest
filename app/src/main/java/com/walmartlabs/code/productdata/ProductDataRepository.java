package com.walmartlabs.code.productdata;

import android.widget.Toast;

import com.walmartlabs.code.model.ProductItem;
import com.walmartlabs.code.model.ProductDataResponse;
import com.walmartlabs.code.network.framework.DownloadTask;
import com.walmartlabs.code.network.framework.NetworkError;
import com.walmartlabs.code.network.framework.ParseJsonTask;
import com.walmartlabs.code.network.utils.ApiRequestBuilder;
import com.walmartlabs.code.network.utils.JsonParsingListener;
import com.walmartlabs.code.network.utils.MethodType;
import com.walmartlabs.code.network.utils.NetworkConstants;
import com.walmartlabs.code.network.utils.NetworkListener;
import com.walmartlabs.code.ui.WLApplication;

import androidx.paging.PagedList;

public class ProductDataRepository implements NetworkListener {

    private static ProductDataRepository mInstance;

    private int totalAccumulatedProductSize;

    public static ProductDataRepository getInstance() {
        if (mInstance == null) {
            mInstance = new ProductDataRepository();
        }
        return mInstance;
    }

    private JsonParsingListener mJsonParsingListener;

    private PagedList<ProductItem> productItemPagedList;

    private int currentPage = 1;

    public void executeProductApi(int pageNumber, int pageSize, JsonParsingListener jsonParsingListener) {
        ApiRequestBuilder apiRequestBuilder = createProductListAPI(pageNumber, pageSize);
        new DownloadTask(apiRequestBuilder).execute();
        mJsonParsingListener = jsonParsingListener;
    }

    @Override
    public void onSuccess(com.walmartlabs.code.network.utils.NetworkResponse networkResponse) {
        if (networkResponse == null) {
            return;
        }
        if (networkResponse.getResponseCode() == NetworkConstants.SUCCESS) {
            new ParseJsonTask(ProductDataResponse.class, mJsonParsingListener).execute(networkResponse.getStringResponse());
        } else {
            Toast.makeText(WLApplication.getAppContext(), "Some thing went wrong. Please try again later. " + networkResponse.getResponseCode(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(NetworkError networkError) {
        Toast.makeText(WLApplication.getAppContext(), "Some thing went wrong. Please try again later. ", Toast.LENGTH_SHORT).show();
    }

    private ApiRequestBuilder createProductListAPI(int pageNumber, int pageSize) {
        ApiRequestBuilder apiRequestBuilder = new ApiRequestBuilder();
        String url = NetworkConstants.BASE_URL + NetworkConstants.PRODUCT_LIST + pageNumber + "/" + pageSize;
        apiRequestBuilder.setUrl(url);
        apiRequestBuilder.setMediaType(NetworkConstants.JSON);
        apiRequestBuilder.setMethodType(MethodType.GET);
        apiRequestBuilder.setNetworkListener(this);
        return apiRequestBuilder;
    }

    public int getTotalAccumulatedProductSize() {
        return totalAccumulatedProductSize;
    }

    public void setTotalAccumulatedProductSize(int totalAccumulatedProductSize) {
        this.totalAccumulatedProductSize = totalAccumulatedProductSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public PagedList<ProductItem> getProductItemPagedList() {
        return productItemPagedList;
    }

    public void setProductItemPagedList(PagedList<ProductItem> productItemPagedList) {
        this.productItemPagedList = productItemPagedList;
    }
}
