package com.vyako.smartfactory.main.presenters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.vyako.smartfactory.main.app.constants.AppConstants;
import com.vyako.smartfactory.main.views.baseviews.IBaseContract;
import com.vyako.smartfactory.main.contracts.LoginScreenContract;
import com.vyako.smartfactory.main.models.managers.UserManager;
import com.vyako.smartfactory.main.network.constants.NetworkConstants;
import com.vyako.smartfactory.main.network.listeners.APIResponseClientCallback;

/**
 * Created by kaushik on 01-Jun-17.
 */
public class LoginActivityPresenterImpl extends BasePresenter implements LoginScreenContract
        .IPresenter, APIResponseClientCallback {

    /**
     * Instantiates a new Login screen presenter.
     *
     * @param context the context
     * @param view    the view
     */
    public LoginActivityPresenterImpl(Context context, LoginScreenContract.IView view) {
        super(context, view);
    }

    public void test() {

    }

    @Override
    public void create() {
        super.create();
    }

    @Override
    public void stop() {

    }


    @Override
    public void onPermissionAccess(@IBaseContract.PermissionIds int permissionId, @Nullable Object data) {

        switch (permissionId) {
            case LoginScreenContract.IPermissionIds.LOGIN:
                Log.d(AppConstants.LOG_CAT, "Permission Access : " + permissionId);
                prepareForLogin();
                break;
        }

    }


    @Override
    public void prepareForLogin() {
        Log.d(AppConstants.LOG_CAT, "perform Login : ");
        String loginCredentials = ((LoginScreenContract.IView)view).getLoginCredentials();
        Log.d(AppConstants.LOG_CAT, "LoginCredentials : " + loginCredentials);
        //handle all login process
        UserManager.getInstance().handleLoginProcess(loginCredentials, this);
    }


    @Override
    public void onSuccessResponse(@NetworkConstants.RequestCode int requestId, int statusCode, String message, @Nullable Object object) {

    }

    @Override
    public void onFailureResponse(@NetworkConstants.RequestCode int requestId, @NonNull String errorString) {

    }
}
