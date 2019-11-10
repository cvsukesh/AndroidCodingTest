package com.walmartlabs.code.ui.view;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import com.bumptech.glide.Glide;


public class GlideImageDownloader {
    private static final String TAG = "GlideImageDownloader";

    /**
     * To load a ImageView without fallback title text.
     *
     * @param context          - Should be activity context, so that Image request will be cancelled when Activity/Fragment is stopped/destroyed.
     * @param imageUrl         - Image URL to get Poster
     * @param imageView        - To Show Poster/Thumbnail
     */
    public static void loadImage(Context context, String imageUrl, ImageView imageView) {
        try {
            Glide.with(context)
                    .load(imageUrl)
                    .into(imageView);
        } catch (Exception ex) {
            Log.d(TAG, "Glide-tag not found for Image URL " + imageUrl);
        }
    }
}
