package com.vyako.smartfactory.main.views.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vyako.smartfactory.main.presenters.BasePresenter;

/**
 * Created by kaushik on 05-Jun-17.
 */

public abstract class BaseAbstractFragment<T> extends Fragment {

    /**
     * Keeps the generic presenter object so can be used in every fragments.
     */
    protected T presenter;
    protected View view = null;
    protected BasePresenter activityPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = getFragmentView();
        initializeInstances();
        initialiseViews();
        setListenerToViews();
        ((BasePresenter)presenter).create();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Override the getFragmentView() and return inflated view.
     *
     * @return
     */
    protected abstract View getFragmentView();

    /**
     * Sets the listeners to the views
     */
    protected void setListenerToViews() {

    }

    /**
     * Initializes views here, make sure to call findViewById with current view object.
     */
    protected void initialiseViews() {

    }

    /**
     * Here we set the generic presenter
     * Override setPresenter in all sub classes and set the presenter
     * Compulsory to Override in each screen
     */
    protected abstract void setPresenter();


    /**
     * initialize the instances required for the View & presenter.
     * Specially initialize the presenter here.
     */
    public void initializeInstances() {
        setPresenter();
    }

    /** Sets the activity presenter to the fragment so that it can be passsed to current fragment presenter.
     * @param activityPresenter
     */
    public void setActivityPresenter(BasePresenter activityPresenter){
              this.activityPresenter = activityPresenter;
    }
}
