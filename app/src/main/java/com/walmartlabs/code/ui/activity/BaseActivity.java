package com.walmartlabs.code.ui.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.walmartlabs.code.R;
import com.walmartlabs.code.productdata.ProductDataRepository;
import com.walmartlabs.code.utils.DeviceUtils;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

public abstract class BaseActivity extends FragmentActivity {

    public abstract void initView();

    public abstract int getScreenLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        if (!DeviceUtils.isXLargeDevice()) {
            /*
             * For Smart Phones and Large screen devices (7" or 8")
             * Orientation should be locked, We can not do it in Manifest Same /
             * Activity / is used for Both Smart and Tablets.
             **/
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        FrameLayout frameLayout = findViewById(R.id.parent_layout);
        View view = getLayoutInflater().inflate(getScreenLayout(), null);
        frameLayout.addView(view);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProductDataRepository.getInstance().setProductItemPagedList(null);
    }
}
