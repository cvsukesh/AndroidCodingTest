package com.walmartlabs.code.viewmodel;

import com.walmartlabs.code.model.ProductItem;
import com.walmartlabs.code.paging.ProductDataSource;
import com.walmartlabs.code.paging.ProductDataSourceFactory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

public class ProductViewModel extends ViewModel {

    private LiveData<PagedList<ProductItem>> pagedListLiveData;
    private LiveData<PageKeyedDataSource<Integer, ProductItem>> dataSourceLiveData;

    public ProductViewModel() {
        ProductDataSourceFactory productDataSourceFactory = new ProductDataSourceFactory();
        dataSourceLiveData = productDataSourceFactory.getItemDataSource();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setPageSize(ProductDataSource.PAGE_SIZE)
                .build();

        pagedListLiveData = (new LivePagedListBuilder(productDataSourceFactory, config)).build();

    }

    public LiveData<PagedList<ProductItem>> getPagedListLiveData() {
        return pagedListLiveData;
    }

    public LiveData<PageKeyedDataSource<Integer, ProductItem>> getDataSourceLiveData() {
        return dataSourceLiveData;
    }
}
