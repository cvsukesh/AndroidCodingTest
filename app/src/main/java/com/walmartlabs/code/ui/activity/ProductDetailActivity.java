package com.walmartlabs.code.ui.activity;

import android.content.Intent;;

import com.walmartlabs.code.R;
import com.walmartlabs.code.utils.Constants;
import com.walmartlabs.code.productdata.ProductDataRepository;
import com.walmartlabs.code.ui.adapter.ProductDetailViewAdapter;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

public class ProductDetailActivity extends BaseActivity {

    private static final String TAG = "ProductDetailActivity";

    @Override
    public void initView() {
        Intent intent = getIntent();
        int position = intent.getIntExtra(Constants.POSITION, 0);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.detail_action_title));

        ProductDetailViewAdapter productDetailViewAdapter = new ProductDetailViewAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.product_view_pager);
        viewPager.setAdapter(productDetailViewAdapter);
        viewPager.setCurrentItem(position);
    }

    @Override
    public int getScreenLayout() {
        return R.layout.product_detail_layout_activity;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Clearing items
        ProductDataRepository.getInstance().setProductItemPagedList(null);
    }
}
