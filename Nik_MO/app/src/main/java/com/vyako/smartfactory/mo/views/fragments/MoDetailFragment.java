package com.vyako.smartfactory.mo.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vyako.smartfactory.main.R;
import com.vyako.smartfactory.main.views.fragments.BaseAbstractFragment;
import com.vyako.smartfactory.mo.contracts.MoDetailFragmentContract;
import com.vyako.smartfactory.mo.presenters.MoDetailFragmentPresenterImpl;
import com.vyako.smartfactory.mo.presenters.MoActivityPresenterImpl;

/**
 * Created by ranjeetd on 6/9/2017.
 */

public class MoDetailFragment extends BaseAbstractFragment<MoDetailFragmentPresenterImpl> implements MoDetailFragmentContract.IView {

    private Object data;
    private TextView moDetails;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("OnCreate MO DETail Fragment");
        presenter.dispatch(MoDetailFragmentContract.IPermissionIds.GET_MO_DETAILS, false);
//        ((MoDetailFragmentPresenterImpl)presenter).requestToGetMo();
    }

    @Override
    protected View getFragmentView() {
        return getActivity().getLayoutInflater().inflate(R.layout.main_fragment_mo_detail_screen, null);
    }

    @Override
    public final void setData(Object object) {
        if (object != null && object instanceof String) {
            moDetails.setText((String) object);
        }
    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();
        moDetails = (TextView) view.findViewById(R.id.moDetails);
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setPresenter() {
        presenter = new MoDetailFragmentPresenterImpl(getContext(), this, (MoActivityPresenterImpl) activityPresenter);
    }


    @Override
    public void setArguments(Object args) {

    }

    @Override
    public void finish() {
        getActivity().onBackPressed();
    }
}
