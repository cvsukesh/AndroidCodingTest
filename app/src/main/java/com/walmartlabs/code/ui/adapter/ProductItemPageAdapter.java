package com.walmartlabs.code.ui.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.walmartlabs.code.R;
import com.walmartlabs.code.model.ProductItem;
import com.walmartlabs.code.network.utils.NetworkConstants;
import com.walmartlabs.code.productdata.ProductDataRepository;
import com.walmartlabs.code.ui.activity.HomeActivity;
import com.walmartlabs.code.ui.activity.ProductDetailActivity;
import com.walmartlabs.code.ui.view.GlideImageDownloader;

import java.text.DecimalFormat;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

/**
 * This class will display the items in the recycler view.
 */
public class ProductItemPageAdapter extends PagedListAdapter<ProductItem, ProductItemViewHolder> implements RecyclerClickListener {

    private HomeActivity mContext;

    public ProductItemPageAdapter(HomeActivity context) {
        super(DIFF_CALLBACK);
        mContext = context;
    }

    @NonNull
    @Override
    public ProductItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.product_item, parent, false);
        return new ProductItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductItemViewHolder holder, int position) {

        holder.setOnRecyclerClickListener(this);
        ProductItem productItem = getItem(position);

        if (productItem == null) {
            return;
        }

        String imageUrl = NetworkConstants.BASE_URL + productItem.getProductImage();
        GlideImageDownloader.loadImage(mContext, imageUrl, holder.mProductImage);

        holder.mProductTitle.setText(productItem.getProductName());
        holder.mProductPrize.setText(productItem.getPrice());
        holder.mProductReview.setText(getFormattedReview(productItem.getReviewRating()));
        holder.mProductRatingCount.setText(String.valueOf(productItem.getReviewCount()));
        holder.mRatingBar.setRating(productItem.getReviewRating());
    }

    /**
     *  This diff call back will determine two items or two lists same or not.
     */
    private static DiffUtil.ItemCallback<ProductItem> DIFF_CALLBACK = new DiffUtil.ItemCallback<ProductItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull ProductItem oldItem, @NonNull ProductItem newItem) {
            return oldItem.getProductId().equals(newItem.getProductId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull ProductItem oldItem, @NonNull ProductItem newItem) {
            return oldItem.equals(newItem);
        }
    };

    private String getFormattedReview(float review) {
        return String.valueOf(Float.parseFloat(new DecimalFormat("##.#").format(review)));
    }

    @Override
    public void onItemClickListener(int position, View view) {
        if (mContext != null) {
            ProductDataRepository.getInstance().setProductItemPagedList(getCurrentList());
            Intent intent = new Intent(mContext, ProductDetailActivity.class);
            intent.putExtra("POSITION", position);
            mContext.startActivity(intent);
        }
    }
}
