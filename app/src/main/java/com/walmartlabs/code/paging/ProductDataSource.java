package com.walmartlabs.code.paging;

import android.util.Log;

import com.walmartlabs.code.model.ProductItem;
import com.walmartlabs.code.model.ProductDataResponse;
import com.walmartlabs.code.network.utils.JsonParsingListener;
import com.walmartlabs.code.productdata.ProductDataRepository;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

/**
 * This class will fetch the data from the network layer with the help of the page number
 *
 * PageKeyedDataSource will fetch the data base on the page number
 */
public class ProductDataSource extends PageKeyedDataSource<Integer, ProductItem> {

    private static final String TAG = "ProductDataSource_SUKKU";

    private static final int FIRST_PAGE = 1;

    public static final int PAGE_SIZE = 30;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, ProductItem> callback) {
        Log.d(TAG, "loadInitial: params loading the initial data.");
        ProductDataRepository.getInstance().executeProductApi(FIRST_PAGE, PAGE_SIZE, new JsonParsingListener() {
            @Override
            public void onParseSuccess(ProductDataResponse productDataResponse) {
                ProductDataRepository.getInstance().setTotalProducts(productDataResponse.getTotalProducts());
                callback.onResult(productDataResponse.getProducts(), null, FIRST_PAGE + 1);
            }

            @Override
            public void onParseFailure(String errorCode) {}
        });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, ProductItem> callback) {
        Log.d(TAG, "loadBefore: load previous data.");
        ProductDataRepository.getInstance().executeProductApi(params.key, PAGE_SIZE, new JsonParsingListener() {
            @Override
            public void onParseSuccess(ProductDataResponse productDataResponse) {

                Integer key = (params.key > 1) ? params.key - 1 : null;

                if (productDataResponse != null) {
                    callback.onResult(productDataResponse.getProducts(), key);
                }
            }

            @Override
            public void onParseFailure(String errorCode) {}
        });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, ProductItem> callback) {
        Log.d(TAG, "loadAfter: load the next available data");
        ProductDataRepository.getInstance().executeProductApi(params.key, PAGE_SIZE, new JsonParsingListener() {
            @Override
            public void onParseSuccess(ProductDataResponse productDataResponse) {
                if (productDataResponse != null) {
                    int totalProducts = productDataResponse.getProducts().size() + ProductDataRepository.getInstance().getTotalAccumulatedProductSize();
                    ProductDataRepository.getInstance().setTotalAccumulatedProductSize(totalProducts);
                    Integer key = hasMore(productDataResponse.getTotalProducts()) ? params.key + 1 : null;
                    Log.d(TAG, "onParseSuccess: get the size of the products = " + productDataResponse.getProducts().size());
                    callback.onResult(productDataResponse.getProducts(), key);
                }
            }

            @Override
            public void onParseFailure(String errorCode) {}
        });
    }

    /**
     * This will check the data, if it has more products to fetch.
     * @param totalProducts -- will be provided by the api.
     * @return checks if we need to call the next page.
     */
    private boolean hasMore(int totalProducts) {
        Log.d(TAG, "hasMore: total products " + totalProducts);
        return ProductDataRepository.getInstance().getTotalAccumulatedProductSize() < totalProducts;
    }
}
