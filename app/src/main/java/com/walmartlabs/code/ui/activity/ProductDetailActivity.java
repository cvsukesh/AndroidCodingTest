package com.walmartlabs.code.ui.activity;

import android.content.Intent;;

import com.walmartlabs.code.R;
import com.walmartlabs.code.ui.adapter.ProductDetailViewAdapter;
import com.walmartlabs.code.viewmodel.ProductViewModel;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

public class ProductDetailActivity extends BaseActivity {

    private static final String TAG = "ProductDetailActivity";

    private ProductDetailViewAdapter mProductDetailViewAdapter;

    @Override
    public void initView() {
        Intent intent = getIntent();
        int position = intent.getIntExtra("POSITION", 0);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.detail_action_title));

        mProductDetailViewAdapter = new ProductDetailViewAdapter(getSupportFragmentManager());
        ViewPager mViewPager = findViewById(R.id.product_view_pager);
        mViewPager.setAdapter(mProductDetailViewAdapter);
        mViewPager.setCurrentItem(position);
    }

    @Override
    public int getScreenLayout() {
        return R.layout.product_detail_layout_activity;
    }
}
