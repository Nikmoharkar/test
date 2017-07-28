package com.vyako.smartfactory.main.network.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.Request;
import com.vyako.smartfactory.main.app.constants.AppConstants;
import com.vyako.smartfactory.main.network.constants.NetworkConstants;
import com.vyako.smartfactory.main.network.listeners.APIResponseManagerCallback;
import com.vyako.smartfactory.main.network.utils.Utils;
import com.vyako.smartfactory.main.network.volley.APIHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kaushik on 08-Jun-17.
 */

public class UserApiModel extends BaseApiModel {

    public UserApiModel(@NonNull Context context, @NonNull APIResponseManagerCallback responseManagerCallback) {
        super();
        this.responseManagerCallback = responseManagerCallback;
    }

    /**
     * Method which return the Login Header
     *
     * @param base64Auth
     * @return
     */
    private Map<String, String> getLoginHeader(@NonNull String base64Auth) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put(NetworkConstants.Headers.BASIC_AUTH_TOKEN, base64Auth);
        return headers;
    }

    /**
     * Method which post shift production detail into server
     *
     * @param requestId
     */
    public void postLogin(@NetworkConstants.RequestCode int requestId, @NonNull String credentials) {
        String base64Auth = getBase64Auth(credentials);
        apiHandler = new APIHandler(this, requestId, Request.Method.POST,
                NetworkConstants.URL.URL_USER_LOGIN, null, getLoginHeader(base64Auth));

        apiHandler.requestAPI();
    }

    /**
     * Method which convert the login credentials into Base64Auth
     *
     * @param text
     * @return
     */
    private String getBase64Auth(@NonNull String text) {
        String basicAuth = null;
        basicAuth = "Basic " + Utils.encodeBase64(text);
        Log.d(AppConstants.LOG_CAT, "base64Auth--" + basicAuth);
        return basicAuth;
    }

    @Override
    public void onAPISuccessResponse(int requestId, int status, String message, String responseString) {
        super.onAPISuccessResponse(requestId, status, message, responseString);
        Log.d(AppConstants.LOG_CAT, "response -- " + responseString);

        if (responseManagerCallback != null) {
            responseManagerCallback.onSuccessResponse(requestId, status, message, responseString, null);
        }
    }

    @Override
    public void onAPIFailureResponse(int requestId, String errorString) {
        super.onAPIFailureResponse(requestId, errorString);
        Log.d(AppConstants.LOG_CAT, "response error-- " + errorString);

        if (responseManagerCallback != null) {
            responseManagerCallback.onFailureResponse(requestId, errorString);
        }
    }
}
