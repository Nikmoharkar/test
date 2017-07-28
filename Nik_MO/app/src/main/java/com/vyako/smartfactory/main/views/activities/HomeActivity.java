package com.vyako.smartfactory.main.views.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.vyako.smartfactory.main.R;
import com.vyako.smartfactory.main.app.constants.AppConstants;
import com.vyako.smartfactory.main.app.controllers.ApplicationController;
import com.vyako.smartfactory.main.contracts.HomeScreenContract;
import com.vyako.smartfactory.main.presenters.HomeActivityPresenterImpl;
import com.vyako.smartfactory.main.views.adapters.GridAdapter;
import com.vyako.smartfactory.main.views.fragments.BaseAbstractFragment;


public class HomeActivity extends BaseAbstractActivity<HomeActivityPresenterImpl> implements HomeScreenContract
        .IView, View.OnClickListener {
    private GridView gridView;
    private GridAdapter gridAdapter;
    private Button launchMoModuleActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeInstances();
        presenter.create();
        setAdapterToGridView();
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        gridView = (GridView) findViewById(R.id.gridView);
        launchMoModuleActivityButton = (Button) findViewById(R.id.launch_mo_module_activity_Button);
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        launchMoModuleActivityButton.setOnClickListener(this);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.main_activity_home_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new HomeActivityPresenterImpl(this, this);

    }


    @Override
    public void setAdapterToGridView() {
        String[] strings = {"MO", "Machines", "Process"};
        if (gridAdapter == null) {
            gridAdapter = new GridAdapter(this, strings);
            gridView.setAdapter(gridAdapter);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.launch_mo_module_activity_Button:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MO_MODULE_SCREEN);
                break;
        }
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
        //if any fragment is set to this activity then only call removetop fragment.
//        removeTopFragment();
    }
}
