package com.vyako.smartfactory.mo.presenters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vyako.smartfactory.main.R;
import com.vyako.smartfactory.main.presenters.BasePresenter;
import com.vyako.smartfactory.main.views.baseviews.IBaseContract;
import com.vyako.smartfactory.main.views.baseviews.IBaseView;
import com.vyako.smartfactory.mo.models.managers.MoManager;
import com.vyako.smartfactory.mo.contracts.MoDetailFragmentContract;
import com.vyako.smartfactory.main.network.constants.NetworkConstants;
import com.vyako.smartfactory.main.network.listeners.APIResponseClientCallback;

import static com.vyako.smartfactory.mo.contracts.MoDetailFragmentContract.IPermissionIds.GET_MO_DETAILS;

/**
 * Created by ranjeetd on 6/12/2017.
 */

public class MoDetailFragmentPresenterImpl extends BasePresenter implements MoDetailFragmentContract.IPresenter, APIResponseClientCallback {

    public MoDetailFragmentPresenterImpl(@NonNull Context context, @NonNull IBaseView view, @NonNull MoActivityPresenterImpl activityPresenter) {
        super(context, view, activityPresenter);
    }

    @Override
    public void stop() {

    }


    @Override
    public void onPermissionAccess(@IBaseContract.PermissionIds int permissionId, @Nullable Object data) {
        switch (permissionId) {
            case GET_MO_DETAILS:
                requestToGetMo();
        }
    }

    @Override
    public void requestToGetMo() {
        showProgressDialog(context.getString(R.string.loading_MO_details), context.getString(R.string.pleaseWaitMsg), false, NetworkConstants.RequestCode.GET_MO);
        MoManager moManager = MoManager.getInstance();
        moManager.sendRequestToGetMoDetails(14, this);
    }

    @Override
    public void onBackButtonPressed() {
        ((MoDetailFragmentContract.IView) view).finish();
    }

    /**
     * Calls when the MO details api response gets called.
     */
    private void onMODetailsResponse(@NetworkConstants.RequestCode int requestId, int statusCode, String message, @Nullable Object object) {
        dismissProgressDialog(NetworkConstants.RequestCode.GET_MO);
        ((MoDetailFragmentContract.IView) view).setData(object);

    }

    @Override
    public void onSuccessResponse(@NetworkConstants.RequestCode int requestId, int statusCode, String message, @Nullable Object object) {

        switch (requestId) {
            case NetworkConstants.RequestCode.GET_MO:
                onMODetailsResponse(requestId, statusCode, message, object);

        }
    }

    @Override
    public void onFailureResponse(@NetworkConstants.RequestCode int requestId, @NonNull String errorString) {
        dismissProgressDialog(NetworkConstants.RequestCode.GET_MO);
    }
}
