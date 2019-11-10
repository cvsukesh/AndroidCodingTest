package com.walmartlabs.code.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.walmartlabs.code.R;
import com.walmartlabs.code.ui.view.WLItemTextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView mProductImage;
    WLItemTextView mProductTitle;
    WLItemTextView mProductPrize;
    WLItemTextView mProductReview;
    WLItemTextView mProductRatingCount;
    RatingBar mRatingBar;

    private RecyclerClickListener recyclerClickListener;

    public ProductItemViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        mProductImage = itemView.findViewById(R.id.product_image);
        mProductTitle = itemView.findViewById(R.id.product_title);
        mProductPrize = itemView.findViewById(R.id.product_prizes);
        mProductReview = itemView.findViewById(R.id.product_review_value);
        mProductRatingCount = itemView.findViewById(R.id.product_rating_count);
        mRatingBar = itemView.findViewById(R.id.product_rating_bar);
    }

    public void setOnRecyclerClickListener(RecyclerClickListener recyclerClickListener) {
        this.recyclerClickListener = recyclerClickListener;
    }


    @Override
    public void onClick(View view) {
        if (this.recyclerClickListener != null) {
            recyclerClickListener.onItemClickListener(getAdapterPosition(), view);
        }
    }
}
