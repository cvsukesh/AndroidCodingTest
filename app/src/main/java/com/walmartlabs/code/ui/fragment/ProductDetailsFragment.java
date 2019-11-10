package com.walmartlabs.code.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.walmartlabs.code.R;
import com.walmartlabs.code.model.ProductItem;
import com.walmartlabs.code.network.utils.NetworkConstants;
import com.walmartlabs.code.ui.WLApplication;
import com.walmartlabs.code.ui.view.WLItemTextView;
import com.walmartlabs.code.ui.view.GlideImageDownloader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class ProductDetailsFragment extends Fragment {

    private ProductItem productItem;

    public void setProductItem(ProductItem productItem) {
        this.productItem = productItem;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_detail_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        WLItemTextView productTitle = view.findViewById(R.id.product_title);
        productTitle.setText(productItem.getProductName());

        RatingBar ratingBar = view.findViewById(R.id.product_rating_bar);
        ratingBar.setRating(productItem.getReviewRating());

        WLItemTextView productRatingCount = view.findViewById(R.id.product_rating_count);
        productRatingCount.setText(String.valueOf(productItem.getReviewCount()));

        ImageView imageView = view.findViewById(R.id.product_image);
        String imageUrl = NetworkConstants.BASE_URL + productItem.getProductImage();
        GlideImageDownloader.loadImage(getActivity(), imageUrl, imageView);

        WLItemTextView price = view.findViewById(R.id.product_prizes);
        price.setText(productItem.getPrice());

        WLItemTextView stocking = view.findViewById(R.id.product_stock);
        stocking.setText(productItem.isInStock() ? "In Stock" : "Out of Stock");
        stocking.setTextColor(productItem.isInStock() ? ContextCompat.getColor(WLApplication.getAppContext(), R.color.green) : Color.RED);

        WLItemTextView description = view.findViewById(R.id.product_description);
        description.setText(!TextUtils.isEmpty(productItem.getShortDescription()) ?
                Html.fromHtml(productItem.getShortDescription()) : "");
    }
}
