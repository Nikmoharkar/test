package com.vyako.smartfactory.mo.presenters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vyako.smartfactory.main.presenters.BasePresenter;
import com.vyako.smartfactory.main.views.baseviews.IBaseActivityView;
import com.vyako.smartfactory.main.views.baseviews.IBaseContract;
import com.vyako.smartfactory.main.views.baseviews.IBaseView;
import com.vyako.smartfactory.mo.contracts.MoScreenContract;
import com.vyako.smartfactory.mo.views.fragments.MoDetailFragment;
import com.vyako.smartfactory.mo.views.fragments.MoListFragment;

/**
 * Created by ranjeetd on 6/8/2017.
 */

public class MoActivityPresenterImpl extends BasePresenter implements MoScreenContract.IPresenter {


    public MoActivityPresenterImpl(@NonNull Context context, @NonNull IBaseView view) {
        super(context, view);
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

    }

    /**
     * Sets the root fragment to the activity.
     */
    @Override
    public void launchRootFragment() {
        MoListFragment moListFragment = new MoListFragment();
        //must be sent via this method
        moListFragment.setActivityPresenter(this);
        ((IBaseActivityView) view).setRootFragment(moListFragment);
    }


    @Override
    public void launchMoDetailFragment(Object args) {
        System.out.println("Activity Presenter: launchMoDetailFragment");
        MoDetailFragment moDetailFragment = new MoDetailFragment();
        moDetailFragment.setActivityPresenter(this);
        moDetailFragment.setArguments(args);
        ((IBaseActivityView) view).addFragment(moDetailFragment, null);
    }
}
