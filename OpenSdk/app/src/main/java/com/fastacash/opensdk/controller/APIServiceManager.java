package com.fastacash.opensdk.controller;

import android.app.Activity;

import com.fastacash.opensdk.callbacks.CallbackImpl;
import com.fastacash.opensdk.constants.ServiceAPIConstants;
import com.fastacash.opensdk.daos.RequestModel;
import com.fastacash.opensdk.utils.APIUtils;

import java.util.HashMap;

import retrofit.Callback;

/**
 * Created by nikhil on 10/21/2015.
 */
public class APIServiceManager {

    private static APIServiceManager apiServiceManager;

    private APIServiceManager() {

    }

    public static APIServiceManager getInstance() {
        if (apiServiceManager == null) {
            apiServiceManager = new APIServiceManager();
        }
        return apiServiceManager;
    }

    /**
     * Creates the link with provided parameters by requesting to the server.
     */
    public void createLink(HashMap<String, Object> requestData, Callback<HashMap<String, Object>> callback) {
        RequestModel requestModel = new RequestModel();
        requestModel.setMethodType(ServiceAPIConstants.POST);
        requestModel.setReqType(ServiceAPIConstants.CREATE_LINK);
        //here sets the body params.
        if (requestData != null) {

            if (requestData.containsKey("url")) {
                requestModel.addBodyParam("url", requestData.get("url"));
            }
            if (requestData.containsKey("link_purpose")) {
                requestModel.addBodyParam("link_purpose", requestData.get("link_purpose"));
            }
            if (requestData.containsKey("max_clicks")) {
                requestModel.addBodyParam("max_clicks", requestData.get("max_clicks"));
            }
            if (requestData.containsKey("expires_in_days")) {
                requestModel.addBodyParam("expires_in_days", requestData.get("expires_in_days"));
            }
            if (requestData.containsKey("social_channel")) {
                requestModel.addBodyParam("social_channel", APIConfig.getInstance().getSocialChannel());
            }
            if (requestData.containsKey("desired_url")) {
                requestModel.addBodyParam("desired_url", requestData.get("desired_url"));
            }
            if (requestData.containsKey("PIN")) {
                requestModel.addBodyParam("PIN", requestData.get("PIN"));
            }
            if (requestData.containsKey("error_msg")) {
                requestModel.addBodyParam("error_msg", requestData.get("error_msg"));
            }
        }
        callService(requestModel, callback);
    }

    /**
     * Update the link with provided parameters by requesting to the server.
     */

    public void updateLink(Activity context, HashMap<String, Object> requestData, Callback<HashMap<String, Object>> callback) {
        APIConfig.getInstance().setContext(context);

        RequestModel requestModel = new RequestModel();
        requestModel.setMethodType(ServiceAPIConstants.PUT);
        requestModel.setReqType(ServiceAPIConstants.UPDATE_LINK);

        if (requestData != null) {
            if (requestData.containsKey("linkCode")) {
                requestModel.addPathParam("linkCode", (String) requestData.get("linkCode"));

            }
            if (requestData.containsKey("PIN")) {
                requestModel.addBodyParam("PIN", requestData.get("PIN"));
            }
            if (requestData.containsKey("url")) {
                requestModel.addBodyParam("url", requestData.get("url"));
            }
            if (requestData.containsKey("link_purpose")) {
                requestModel.addBodyParam("link_purpose", requestData.get("link_purpose"));
            }
            if (requestData.containsKey("max_clicks")) {
                requestModel.addBodyParam("max_clicks", requestData.get("max_clicks"));
            }
            if (requestData.containsKey("expires_in_days")) {
                requestModel.addBodyParam("expires_in_days", requestData.get("expires_in_days"));
            }
            if (requestData.containsKey("social_channel")) {
                requestModel.addBodyParam("social_channel", requestData.get("social_channel"));
            }
            if (requestData.containsKey("desired_url")) {
                requestModel.addBodyParam("desired_url", requestData.get("desired_url"));
            }
            if (requestData.containsKey("error_msg")) {
                requestModel.addBodyParam("error_msg", requestData.get("error_msg"));
            }
        }
        callService(requestModel, callback);
    }

    /**
     * Fetches the link with provided parameters by requesting to the server.
     */
    public void fetchLink(Activity context, HashMap<String, Object> requestData, Callback<HashMap<String, Object>> callback) {
        APIConfig.getInstance().setContext(context);
        RequestModel requestModel = new RequestModel();
        requestModel.setMethodType(ServiceAPIConstants.GET);
        requestModel.setReqType(ServiceAPIConstants.GET_LINK);

        if (requestData != null) {
            if (requestData.containsKey("linkCode")) {
                requestModel.addPathParam("linkCode", (String) requestData.get("linkCode"));

            }
            if (requestData.containsKey("PIN")) {
                requestModel.addBodyParam("PIN", requestData.get("PIN"));
            }
        }

        callService(requestModel, callback);
    }

    /**
     * Method to responsible for call service.
     */
    private void callService(RequestModel requestModel, Callback<HashMap<String, Object>> callback) {
        //as here no path param requires to construct Url.
        String path = APIUtils.getPath(requestModel.getReqType(), requestModel.getPathParams());
        APIServices.FastacashService fastacashService = APIServices.getFastacashService(requestModel.getReqType());
        switch (requestModel.getMethodType()) {
            case ServiceAPIConstants.GET:
                fastacashService.getData(path, requestModel.getBodyParams(), callback);
                break;
            case ServiceAPIConstants.POST:
                fastacashService.postData(path, requestModel.getBodyParams(), callback);
                break;
            case ServiceAPIConstants.PUT:
                fastacashService.putData(path, requestModel.getBodyParams(), callback);
                break;
            case ServiceAPIConstants.DELETE:
                break;
        }

    }
}
