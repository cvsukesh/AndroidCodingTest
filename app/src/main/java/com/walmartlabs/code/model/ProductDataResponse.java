package com.walmartlabs.code.model;

import java.util.List;

/**
 *  Network data model
 */
public class ProductDataResponse {

    private int totalProducts;

    private int pageNumber;

    private int pageSize;

    private int statusCode;

    private List<ProductItem> products;

    public int getTotalProducts() {
        return totalProducts;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<ProductItem> getProducts() {
        return products;
    }

}
