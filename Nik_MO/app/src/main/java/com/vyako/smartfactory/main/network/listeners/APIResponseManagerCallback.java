package com.vyako.smartfactory.main.network.listeners;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vyako.smartfactory.main.network.constants.NetworkConstants;

/**
 * Created by nikhilm on 26-Jun-17.
 */

public interface APIResponseManagerCallback {

    void onSuccessResponse(@NetworkConstants.RequestCode int requestId, int statusCode, String message, String response,
                           @Nullable Object object);

    void onFailureResponse(@NetworkConstants.RequestCode int requestId, @NonNull String errorString);

}

