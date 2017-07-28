package com.vyako.smartfactory.mo.views.activity;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;

import com.vyako.smartfactory.main.views.activities.BaseAbstractActivity;
import com.vyako.smartfactory.main.views.fragments.BaseAbstractFragment;
import com.vyako.smartfactory.mo.contracts.MoScreenContract;
import com.vyako.smartfactory.mo.presenters.MoActivityPresenterImpl;
import com.vyako.smartfactory.mo.views.fragments.MoDetailFragment;
import com.vyako.smartfactory.mo.views.fragments.MoListFragment;


/**
 * @author nikhilm
 */
public class MoActivity extends BaseAbstractActivity<MoActivityPresenterImpl> implements MoScreenContract.IView {
    private static final String TAG__FRAG_MO_DETAILS = "tag_frag_mo_details";
    private MoListFragment moListFragment;
    private MoDetailFragment moDetailFragment;
    protected FrameLayout fragmentContainerLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
    }

    @Override
    public void setPresenter() {
        presenter = new MoActivityPresenterImpl(this, this);
    }

    @Override
    public void setRootFragment(BaseAbstractFragment rootFragment) {
        replaceFragment(rootFragment);
    }

    @Override
    public void addFragment(@NonNull BaseAbstractFragment fragment, @Nullable String tag) {
        addFragmentToStack(fragment);
    }

    @Override
    public void removeTopFragment(@Nullable String tag) {
        removeTopFragment();
    }

    @Override
    public void setData() {

    }


    @Override
    protected View getView() {
        return null;
    }


    /**
     * Removes the top fragment from the stack.
     */
    public void removeTopFragment() {
        onBackPressed();
    }


}
