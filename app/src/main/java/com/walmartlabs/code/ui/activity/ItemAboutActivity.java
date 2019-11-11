package com.walmartlabs.code.ui.activity;

import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;

import com.walmartlabs.code.R;
import com.walmartlabs.code.ui.view.WLItemTextView;
import com.walmartlabs.code.utils.Constants;

import androidx.appcompat.widget.Toolbar;

/**
 * Screen displays long description of the product.
 */
public class ItemAboutActivity extends BaseActivity {
    @Override
    public void initView() {
        setActionBar();

        Intent intent = getIntent();
        String description = intent.getStringExtra(Constants.PRODUCT_DESCRIPTION);

        WLItemTextView textView = findViewById(R.id.product_description);
        textView.setText(Html.fromHtml(description));
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
        return R.layout.activity_about;
    }
}