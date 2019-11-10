package com.walmartlabs.code.ui.activity;

import androidx.fragment.app.FragmentManager;

import com.walmartlabs.code.R;
import com.walmartlabs.code.ui.fragment.WLSplashFragment;

public class HomeActivity extends BaseActivity {

    @Override
    public void initView() {
        launchSplashScreen();
    }

    @Override
    public int getScreenLayout() {
        return R.layout.activity_main;
    }

    private void launchSplashScreen() {
        WLSplashFragment wlSplashFragment = new WLSplashFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.layout_container, wlSplashFragment).commit();
    }
}
