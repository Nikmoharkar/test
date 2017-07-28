package com.vyako.smartfactory.main.presenters;

import android.content.Context;
import android.support.annotation.Nullable;

import com.vyako.smartfactory.main.views.baseviews.IBaseActivityView;
import com.vyako.smartfactory.main.views.baseviews.IBaseContract;
import com.vyako.smartfactory.main.contracts.HomeScreenContract;

import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by hp on 31-05-2017.
 */

public class HomeActivityPresenterImpl extends BasePresenter implements HomeScreenContract.IPresenter {

    public HomeActivityPresenterImpl(@NotNull Context context, @NotNull HomeScreenContract.IView homeView) {
        super(context, homeView);
        this.context = context;
        this.view = homeView;
    }

    @Override
    public void create() {
        super.create();
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {

    }


    @Override
    public void onPermissionAccess(@IBaseContract.PermissionIds int permissionId, @Nullable Object data) {
        switch (permissionId) {

        }
    }

    /**
     * Sets the root fragment to the activity.
     */
    @Override
    public void launchRootFragment() {
        ((IBaseActivityView) view).setRootFragment(null);
    }

}
