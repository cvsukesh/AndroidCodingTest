package com.walmartlabs.code.ui.adapter;

import android.view.ViewGroup;

import com.walmartlabs.code.model.ProductItem;
import com.walmartlabs.code.paging.PagerCallBack;
import com.walmartlabs.code.productdata.ProductDataRepository;

import java.util.SortedSet;
import java.util.TreeSet;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.paging.PagedList;

/**
 * Adapter to display views in a fragment.
 * @param <T>
 */
public abstract class ProductDetailPagerAdapter<T> extends FragmentStatePagerAdapter {

    protected PagedList<ProductItem> mPagedList = ProductDataRepository.getInstance().getProductItemPagedList();

    public abstract Fragment createFragment(int position);

    public ProductDetailPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    public ProductItem getValue(int position) {
        return mPagedList != null ? mPagedList.get(position) : null;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (mPagedList != null) {
            mPagedList.loadAround(position);
        }
        return createFragment(position);
    }

    @Override
    public int getCount() {
        return mPagedList != null ? mPagedList.size() : 0;
    }

}
