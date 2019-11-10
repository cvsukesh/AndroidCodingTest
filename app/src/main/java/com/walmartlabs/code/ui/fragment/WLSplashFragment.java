package com.walmartlabs.code.ui.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.walmartlabs.code.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class WLSplashFragment extends Fragment {

    private static final String RESOURCE_PATH = "android.resource://";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_splash_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        VideoView videoView = view.findViewById(R.id.videoView);
        String uri = RESOURCE_PATH + getActivity().getPackageName() + "/" + R.raw.walmart_logo;
        videoView.setMediaController(null);
        videoView.setVideoPath(uri);
        videoView.start();
        videoView.setOnCompletionListener(onCompletionListener);
    }

    private final MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            launchProductListFragment();
        }
    };

    private void launchProductListFragment() {
        ProductItemListFragment productItemListFragment = new ProductItemListFragment();

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.layout_container, productItemListFragment).commit();
    }
}
