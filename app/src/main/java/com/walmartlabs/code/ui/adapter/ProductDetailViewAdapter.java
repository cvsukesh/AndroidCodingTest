package com.walmartlabs.code.ui.adapter;

import com.walmartlabs.code.model.ProductItem;
import com.walmartlabs.code.ui.fragment.ProductDetailsFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * Class will display items in a view pager.
 */
public class ProductDetailViewAdapter extends ProductDetailPagerAdapter<ProductItem> {

    public ProductDetailViewAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment createFragment(int position) {
        ProductDetailsFragment productDetailsFragment = new ProductDetailsFragment();
        productDetailsFragment.setProductItem(getValue(position));
        return productDetailsFragment;
    }
}
