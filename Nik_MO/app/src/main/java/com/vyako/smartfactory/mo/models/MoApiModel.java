package com.vyako.smartfactory.mo.models;

import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.Request;
import com.vyako.smartfactory.main.network.constants.NetworkConstants;
import com.vyako.smartfactory.main.network.listeners.APIResponseManagerCallback;
import com.vyako.smartfactory.main.network.models.BaseApiModel;
import com.vyako.smartfactory.main.network.volley.APIHandler;

import org.json.JSONObject;

/**
 * Created by ranjeetd on 6/9/2017.
 */

public class MoApiModel extends BaseApiModel {

    private String logCat;

    public MoApiModel(@NonNull APIResponseManagerCallback responseManagerCallback) {
        super();
        this.responseManagerCallback = responseManagerCallback;
    }


    /**
     * Method which get the list of MOs
     *
     * @param requestId
     */
    public void requestForMoList(int requestId, boolean isShowLoading, JSONObject requestBody) {
        //   int currentMachineId = LocalModel.getInstance().getCurrentMachineId();
        apiHandler = new APIHandler(this, requestId, Request.Method.POST, NetworkConstants.URL.URL_FILTERED_MO_LIST_PAGINATION, requestBody.toString(), getHeader());
        apiHandler.requestAPI();

    }

    /**
     * Method which get the list of MOs
     *
     * @param requestId
     */
    public void requestForMoDetails(int requestId, boolean isShowLoading, int moId, JSONObject requestBody) {
        //   int currentMachineId = LocalModel.getInstance().getCurrentMachineId();
        String reqBodyStr = null;
        if (requestBody != null) {
            reqBodyStr = requestBody.toString();
        }
        apiHandler = new APIHandler(this, requestId, Request.Method.GET, NetworkConstants.URL.GET_MO + moId, reqBodyStr, getHeader());
        apiHandler.requestAPI();

    }

    @Override
    public void onAPISuccessResponse(int requestId, int status, String message, String responseString) {
        super.onAPISuccessResponse(requestId, status, message, responseString);
        Log.d(logCat, "response--" + responseString);

        if (responseManagerCallback != null) {
            responseManagerCallback.onSuccessResponse(requestId, status, message, responseString, null);
        }
    }


    @Override
    public void onAPIFailureResponse(int requestId, String errorString) {
        super.onAPIFailureResponse(requestId, errorString);
        Log.d(logCat, "error--" + errorString);

        if (responseManagerCallback != null) {
            responseManagerCallback.onFailureResponse(requestId, errorString);
        }
    }
}

