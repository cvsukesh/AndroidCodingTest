package com.walmartlabs.code.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.walmartlabs.code.ui.WLApplication;

public class DeviceUtils {

    private static final double SCREEN_SIZE_FOR_X_LARGE = 9.00;

    /**
     * Return True if device is XLarge device, Helps to load the device specific resources
     * Used to lock orientation only.
     *
     * @return: true if the device the application is running is a tablet device.
     */
    public static boolean isXLargeDevice() {
        Context context = WLApplication.getAppContext();
        if (context == null) {
            return false;
        }
        return ((context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE) || isDeviceXLarge();
    }

    /*
     * To check if Device size more then 10 inch or equal.
     */
    private static double getDeviceSize() {
        Context context = WLApplication.getAppContext();
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        assert windowManager != null;
        windowManager.getDefaultDisplay().getMetrics(dm);
        double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
        double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
        return Math.sqrt(x + y);
    }

    private static boolean isDeviceXLarge() {
        return getDeviceSize() >= SCREEN_SIZE_FOR_X_LARGE;
    }
}
