package com.vyako.smartfactory.main.network.listeners;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vyako.smartfactory.main.network.constants.NetworkConstants;

/**
 * Created by kaushik on 09-Nov-16.
 */

public interface APIResponseClientCallback {

//    void onAPIResponse(int requestId, boolean isSuccess, String responseString, String errorString);

    void onSuccessResponse(@NetworkConstants.RequestCode int requestId, int statusCode, String message,
                           @Nullable Object object);

    void onFailureResponse(@NetworkConstants.RequestCode int requestId, @NonNull String errorString);

}
