package com.vyako.smartfactory.mo.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.vyako.smartfactory.main.R;

import com.vyako.smartfactory.main.views.fragments.BaseAbstractFragment;
import com.vyako.smartfactory.mo.contracts.MoListFragmentContract;
import com.vyako.smartfactory.mo.presenters.MoListFragmentPresenterImpl;

import com.vyako.smartfactory.mo.presenters.MoActivityPresenterImpl;
import com.vyako.smartfactory.mo.dos.MOListDO;
import com.vyako.smartfactory.mo.views.adapters.MoListAdapter;

/**
 * Created by ranjeetd on 6/9/2017.
 */

public class MoListFragment extends BaseAbstractFragment<MoListFragmentPresenterImpl> implements MoListFragmentContract.IView, View.OnClickListener {

    private FloatingActionButton createMoFloatingButton;
    private RecyclerView moListRecyclerView;
    private MoListAdapter moListAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("<< create");
        setAdapterToRecyclerView();
        sendRequestToGetMoList();
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    @Override
    public void setArguments(Object args) {
    }

    @Override
    public void finish() {

    }

    @Override
    protected View getFragmentView() {
        return getActivity().getLayoutInflater().inflate(R.layout.main_fragment_mo_list_screen, null);
    }

    /**
     * Method that set Layout Manager Dynamically for RecyclerView.
     */
    private void setLayoutManagerDynamically() {
        layoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        moListRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void initialiseViews() {
        System.out.println("<< initialiseViews");
        super.initialiseViews();

        moListRecyclerView = (RecyclerView) view.findViewById(R.id.mo_list_recycler_view);
        createMoFloatingButton = (FloatingActionButton) view.findViewById(R.id.create_mo_floating_button);
        moListRecyclerView.setHasFixedSize(true);
        setLayoutManagerDynamically();
    }


    @Override
    protected void setListenerToViews() {
        System.out.println("<< setListenerToViews");
        super.setListenerToViews();
        createMoFloatingButton.setOnClickListener(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        System.out.println("<< onCreateView");
        super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    private void sendRequestToGetMoList() {
        presenter.dispatch(MoListFragmentContract.IPermissionIds.MO_LIST_PERMISSION, false);
        // MoManager.getInstance().sendRequestToGet(this);

    }

    /***
     * method that set the moListAdapter on recycler View.
     */
    private void setAdapterToRecyclerView() {
        System.out.println("<< setAdapterToRecyclerView");
        //   System.out.println("<< moStatusList" + moStatusList);
        moListAdapter = new MoListAdapter(this, null, presenter);
        moListRecyclerView.setAdapter(moListAdapter);
    }

    @Override
    public void setPresenter() {
        presenter = new MoListFragmentPresenterImpl(getContext(), this, (MoActivityPresenterImpl) activityPresenter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.create_mo_floating_button:
                presenter.dispatch(MoListFragmentContract.IPermissionIds.CREATE_NEW_MO, false);
                break;

        }

    }

    @Override
    public void setData() {

    }

    @Override
    public void refreshMOListAdapter(MOListDO moListDO) {
        if (moListDO != null) {
            moListAdapter.refreshList(moListDO.getManufacturing_orders());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("MOlist: Destroy");
    }
}
