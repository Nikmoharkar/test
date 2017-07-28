package com.vyako.smartfactory.main.views.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.vyako.smartfactory.main.R;
import com.vyako.smartfactory.main.contracts.SplashScreenContract;
import com.vyako.smartfactory.main.presenters.SplashActivityPresenterImpl;
import com.vyako.smartfactory.main.views.fragments.BaseAbstractFragment;


/**
 * Created by hp on 31-05-2017.
 */

public class SplashActivity extends BaseAbstractActivity<SplashActivityPresenterImpl> implements SplashScreenContract.IView {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.create();
        presenter.dispatch(SplashScreenContract.IPermissionIds.FINISH_SPLASH_SCREEN, false);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.main_activity_splash_screen, null);
        return view;
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
    }

    @Override
    public void setPresenter() {
        presenter = new SplashActivityPresenterImpl(this, this);
    }


    @Override
    public void setData() {

    }

    @Override
    public void setAnimation() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        hideToolbar();
        lockDrawer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        showToolbar();
        releaseDrawerLock();
    }

    @Override
    public void setRootFragment(BaseAbstractFragment rootFragment) {

    }

    @Override
    public void addFragment(@NonNull BaseAbstractFragment fragment, @Nullable String tag) {

    }

    @Override
    public void removeTopFragment(@Nullable String tag) {

    }
}
