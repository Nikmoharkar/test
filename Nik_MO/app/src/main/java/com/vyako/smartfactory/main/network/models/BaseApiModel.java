package com.vyako.smartfactory.main.network.models;

import android.content.Context;

import com.vyako.smartfactory.main.app.application.MyApplication;
import com.vyako.smartfactory.main.app.constants.AppConstants;
import com.vyako.smartfactory.main.app.controllers.ApplicationController;
import com.vyako.smartfactory.main.app.preferences.Preferences;
import com.vyako.smartfactory.main.network.constants.NetworkConstants;
import com.vyako.smartfactory.main.network.listeners.APIResponseClientCallback;
import com.vyako.smartfactory.main.network.listeners.APIResponseManagerCallback;
import com.vyako.smartfactory.main.network.volley.APICallback;
import com.vyako.smartfactory.main.network.volley.APIHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kaushik on 09-Nov-16.
 */

public abstract class BaseApiModel implements APICallback {

    protected APIResponseManagerCallback responseManagerCallback;
    protected APIHandler apiHandler;
    protected Preferences preferences;
    //    protected String x_auth_token = null;
    private final String AUTH_TOKEN = "mSwKEp1Ovjr86BnTkNNQMxOkYX/uoMTz7QqH/xqBJO0=";
    /**
     * Requests queue to keeps pending requests.
     */
    private ArrayList<APIHandler> requestsQueue;

    /**
     * Maintains's the state of queue process
     */
    private boolean isQueueInProgress = false;

    public BaseApiModel() {
        preferences = new Preferences(MyApplication.getInstance().getApplicationContext());

        //initializes the requests
        requestsQueue = new ArrayList<>();
    }

    @Override
    public void onAPISuccessResponse(int requestId, int status, String message, String responseString) {

    }

    @Override
    public void onAPIFailureResponse(int requestId, String errorString) {
    }

    protected Map<String, String> getHeader() {
        HashMap<String, String> headers = new HashMap<>();
        String x_auth_token = preferences.getString(AppConstants.PREFERENCES.KEY_PREF_X_AUTH_TOKEN, null);
//        headers.put(NetworkConstants.Headers.X_AUTH_TOKEN, x_auth_token);
        headers.put(NetworkConstants.Headers.X_AUTH_TOKEN, AUTH_TOKEN);

        return headers;
    }

    /**
     * Set's {@link APIHandler} to Request Queue
     *
     * @param apiHandler
     */
    protected void addAPIHandlerToQueue(APIHandler apiHandler) {
        if (requestsQueue != null) {
            requestsQueue.add(apiHandler);
        }
    }

    /**
     * Fetch the Request Queue
     *
     * @return
     */
    private ArrayList<APIHandler> getAPIHandlerToQueue() {
        return requestsQueue;
    }

    /**
     * Execute the Request Queue
     */
    protected synchronized void execute() {
        if (requestsQueue != null) {
            if (!isQueueInProgress || !requestsQueue.isEmpty()) {
                //Process the request from queue
                APIHandler apiHandler = requestsQueue.get(0);
                if (apiHandler != null) {
                    //Before making hit to server set isQueueInProgress to true
                    isQueueInProgress = true;
                    apiHandler.requestAPI();
                } else {
                    isQueueInProgress = false;
                }
            } else {
                isQueueInProgress = false;
            }
        } else {
            isQueueInProgress = false;
        }
    }

    /**
     * On Success of Queue Handle Requested API
     */
    protected void onSuccessOperateQueue() {
        if (requestsQueue != null && !requestsQueue.isEmpty()) {
            //Remove the reference of request wht get executed successfully
            requestsQueue.remove(0);
            //go to request next in queue.
            execute();
        }
    }

    /**
     * On Failure of Queue Manage Failed Request API
     */
    protected void onFaliureOperateQueue() {
        if (requestsQueue != null && !requestsQueue.isEmpty()) {
            APIHandler apiHandler = requestsQueue.get(0);
            if (apiHandler != null) {
                //Process the request from queue
                apiHandler.requestAPI();
            }
        }
    }
//    protected Map<String, String> getLoginHeader() {
//        HashMap<String, String> headers = new HashMap<>();
//        //todo for Base64 for username and password
//        headers.put(RequestUrlConstants.Headers.BASIC_AUTH_TOKEN, )
//        return headers;
//    }
}
