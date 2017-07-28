package com.vyako.smartfactory.main.network.volley;

/**
 * Created by sid on 07/08/2016.
 */
public interface APICallback {
    public void onAPISuccessResponse(int requestId, int status, String message, String responseString);

    public void onAPIFailureResponse(int requestId, String errorString);
}
