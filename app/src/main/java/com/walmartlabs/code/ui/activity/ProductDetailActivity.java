package com.walmartlabs.code.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;;

import com.walmartlabs.code.R;
import com.walmartlabs.code.ui.view.WLItemTextView;
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

        setActionBar();

        ProductDetailViewAdapter productDetailViewAdapter = new ProductDetailViewAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.product_view_pager);
        viewPager.setAdapter(productDetailViewAdapter);
        viewPager.setCurrentItem(position);
    }

    /**
     * Init Action bar
     */
    private void setActionBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        WLItemTextView actionTitle = toolbar.findViewById(R.id.action_title);
        ImageView imageView = toolbar.findViewById(R.id.action_image);
        imageView.setImageResource(R.drawable.baseline_arrow_back_white);
        actionTitle.setText(getResources().getText(R.string.list_action_title));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
