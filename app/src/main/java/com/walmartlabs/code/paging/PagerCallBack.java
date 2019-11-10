package com.walmartlabs.code.paging;

import android.util.Log;

import com.walmartlabs.code.ui.adapter.ProductDetailPagerAdapter;

import java.util.SortedSet;

import androidx.paging.PagedList;

public class PagerCallBack extends PagedList.Callback {

    private static final String TAG = "PagerCallBack";

    private ProductDetailPagerAdapter mProductDetailPagerAdapter;

    private SortedSet<Integer> positions;

    public PagerCallBack(ProductDetailPagerAdapter productDetailPagerAdapter, SortedSet<Integer> positions) {
        Log.d(TAG, "PagerCallBack: Creating instance");
        mProductDetailPagerAdapter = productDetailPagerAdapter;
        this.positions = positions;
    }

    @Override
    public void onChanged(int position, int count) {
        Log.d(TAG, "onChanged: ");
        analyzeCount(position, count);
    }

    @Override
    public void onInserted(int position, int count) {
        Log.d(TAG, "onInserted: ");
        analyzeCount(position, count);
    }

    @Override
    public void onRemoved(int position, int count) {
        Log.d(TAG, "onRemoved: ");
        analyzeCount(position, count);
    }

    private void analyzeCount(int start, int count) {
        Log.d(TAG, "analyzeCount: Start position " + start + "total count " +count);
        analyzeRange(start, start + count);
        Log.d(TAG, "analyzeCount: Start position " + start + "total count " + (start + count));
    }

    private void analyzeRange(int start, int end) {
        if (start <= positions.last() && positions.first() <= end) {
            Log.d(TAG, "analyzeRange: Analysing notifying data set change");
            if (mProductDetailPagerAdapter != null) {
                mProductDetailPagerAdapter.notifyDataSetChanged();
            }
        }
    }
}
