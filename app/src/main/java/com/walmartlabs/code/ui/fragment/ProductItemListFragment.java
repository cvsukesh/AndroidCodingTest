package com.walmartlabs.code.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.walmartlabs.code.R;
import com.walmartlabs.code.model.ProductItem;
import com.walmartlabs.code.ui.view.WLItemTextView;
import com.walmartlabs.code.utils.DeviceUtils;
import com.walmartlabs.code.viewmodel.ProductViewModel;
import com.walmartlabs.code.ui.activity.HomeActivity;
import com.walmartlabs.code.ui.adapter.ProductItemPageAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Class will display items in the list
 */
public class ProductItemListFragment extends Fragment {

    private static final String TAG = "ProductItemListFragment";

    private RecyclerView mRecyclerView = null;

    private ProductItemPageAdapter mProductItemPageAdapter;

    private ProductViewModel mProductDataViewModel;

    private ProgressBar mProgressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_list_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        setActionBar(view);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mProgressBar = view.findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    /**
     * Init Action bar
     */
    private void setActionBar(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        WLItemTextView actionTitle = toolbar.findViewById(R.id.action_title);
        actionTitle.setText(R.string.list_action_title);
        ImageView imageView = toolbar.findViewById(R.id.action_image);
        actionTitle.setText(getResources().getText(R.string.list_action_title));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() != null) {
                    getActivity().finish();
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), DeviceUtils.isXLargeDevice() ? 3 : 1));
        mRecyclerView.setHasFixedSize(true);
        mProductItemPageAdapter = new ProductItemPageAdapter((HomeActivity) getActivity());
        mRecyclerView.setAdapter(mProductItemPageAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mProductDataViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        mProductDataViewModel.getPagedListLiveData().observe(this, observer);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mProductDataViewModel != null) {
            mProductDataViewModel.getPagedListLiveData().removeObserver(observer);
        }
    }

    /**
     * Observer will observe the changes and notifies changes to the adapter.
     */
    private Observer<PagedList<ProductItem>> observer = new Observer<PagedList<ProductItem>>() {
        @Override
        public void onChanged(PagedList<ProductItem> productData) {
            if (mProductItemPageAdapter != null) {
                Log.d(TAG, "onChanged: Submitting the list to product pager adapter. " + productData.size());
                mProductItemPageAdapter.submitList(productData);
                mProgressBar.setVisibility(View.GONE);
            }
        }
    };
}
