package com.walmartlabs.code.paging;

import com.walmartlabs.code.model.ProductItem;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

/**
 * Class will create the data source from the network layer.
 */
public class ProductDataSourceFactory extends DataSource.Factory {

    // will set the value and dispatch the value to all the active observers.
    private MutableLiveData<PageKeyedDataSource<Integer, ProductItem>> itemDataSource = new MutableLiveData<>();

    @NonNull
    @Override
    public DataSource create() {
        ProductDataSource productDataSource = new ProductDataSource();
        itemDataSource.postValue(productDataSource);
        return productDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, ProductItem>> getItemDataSource() {
        return itemDataSource;
    }

}
