package com.walmartlabs.code.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.walmartlabs.code.R;
import com.walmartlabs.code.model.ProductItem;
import com.walmartlabs.code.ui.activity.ItemAboutActivity;
import com.walmartlabs.code.utils.Constants;
import com.walmartlabs.code.ui.WLApplication;
import com.walmartlabs.code.ui.view.WLItemTextView;
import com.walmartlabs.code.ui.view.GlideImageDownloader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

/**
 * Product detail screen displaying in a full screen
 */
public class ProductDetailsFragment extends Fragment {

    private ProductItem productItem;

    public void setProductItem(ProductItem productItem) {
        this.productItem = productItem;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        WLItemTextView productTitle = view.findViewById(R.id.product_title);
        productTitle.setText(productItem.getProductName());

        RatingBar ratingBar = view.findViewById(R.id.product_rating_bar);
        ratingBar.setRating(productItem.getReviewRating());

        WLItemTextView productReview = view.findViewById(R.id.product_review_value);
        productReview.setText(String.valueOf(productItem.getReviewRating()));

        WLItemTextView productRatingCount = view.findViewById(R.id.product_rating_count);
        productRatingCount.setText(String.valueOf(productItem.getReviewCount()));

        ImageView imageView = view.findViewById(R.id.product_image);
        String imageUrl = Constants.BASE_URL + productItem.getProductImage();
        GlideImageDownloader.loadImage(getActivity(), imageUrl, imageView);

        WLItemTextView price = view.findViewById(R.id.product_prizes);
        price.setText(productItem.getPrice());

        WLItemTextView stocking = view.findViewById(R.id.product_stock);
        stocking.setText(productItem.isInStock() ? "In Stock" : "Out of Stock");
        stocking.setTextColor(productItem.isInStock() ? ContextCompat.getColor(WLApplication.getAppContext(), R.color.green) : Color.RED);

        WLItemTextView description = view.findViewById(R.id.product_description);
        description.setText(!TextUtils.isEmpty(productItem.getShortDescription()) ?
                Html.fromHtml(productItem.getShortDescription()) : "");

        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ItemAboutActivity.class);
                if (!TextUtils.isEmpty(productItem.getLongDescription())) {
                    intent.putExtra(Constants.PRODUCT_DESCRIPTION, productItem.getLongDescription());
                    startActivity(intent);
                } else {
                    Toast.makeText(WLApplication.getAppContext(), "Description not available", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
