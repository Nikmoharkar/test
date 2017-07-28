package com.vyako.smartfactory.main.models.managers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vyako.smartfactory.main.app.constants.AppConstants;
import com.vyako.smartfactory.main.app.controllers.ApplicationController;
import com.vyako.smartfactory.main.contracts.LoginScreenContract;
import com.vyako.smartfactory.main.network.constants.NetworkConstants;
import com.vyako.smartfactory.main.network.listeners.APIResponseClientCallback;
import com.vyako.smartfactory.main.network.listeners.APIResponseManagerCallback;

/**
 * Created by kaushik on 01-Jun-17.
 * User manager help to manage all the operation related to the user managements
 */

public class UserManager implements LoginScreenContract.IDataManager, APIResponseManagerCallback {


    /**
     * private instance of UserManager for singleton Design Pattern
     */
    private static UserManager instance;

    private APIResponseClientCallback apiResponseClientCallback;

    /**
     * Constructor
     */
    private UserManager() {
    }

    /**
     * Get Single instance of UserManager
     *
     * @return UserManager single instance
     */
    public static UserManager getInstance() {
        if (instance == null) {
            // creating new instance of application controller
            instance = new UserManager();
        }
        return instance;
    }


    @Override
    public void handleLoginProcess(@NonNull String loginCredentials, @NonNull APIResponseClientCallback
            apiResponseClientCallback) {
        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_HOME_SCREEN);
//        this.apiResponseClientCallback = apiResponseClientCallback;
        //   UserApiModel userApiModel = new UserApiModel(ApplicationController.getInstance().getContext(), this);
        // userApiModel.postLogin(NetworkConstants.RequestCode.GET_USER_LOGOUT, loginCredentials);
    }


    /**
     * @param responseString
     * @param requestId
     */
    private void onLoginSuccess(String responseString, int requestId) {
        //todo handle response
    }

    @Override
    public void onSuccessResponse(@NetworkConstants.RequestCode int requestId, int statusCode, String message, String response, @Nullable Object object) {
        switch (requestId) {
            case NetworkConstants.RequestCode.GET_USER_LOGOUT:
                onLoginSuccess(response, requestId);
                break;

            default:
                throw new IllegalStateException("Invalid Request code id");
        }

    }

    @Override
    public void onFailureResponse(@NetworkConstants.RequestCode int requestId, @NonNull String errorString) {
        switch (requestId) {
            case NetworkConstants.RequestCode.GET_USER_LOGOUT:
                onLoginFailure(errorString, requestId);
                break;

            default:
                throw new IllegalStateException("Invalid Request code id");
        }
    }

    /**
     * @param errorString
     * @param requestId
     */
    private void onLoginFailure(String errorString, int requestId) {
        //todo handle response
    }
}
