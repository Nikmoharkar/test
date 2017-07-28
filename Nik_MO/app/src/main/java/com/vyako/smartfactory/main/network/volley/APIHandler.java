package com.vyako.smartfactory.main.network.volley;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.vyako.smartfactory.main.R;
import com.vyako.smartfactory.main.app.application.MyApplication;
import com.vyako.smartfactory.main.network.constants.NetworkConstants;
import com.vyako.smartfactory.main.network.listeners.IDialogCallback;
import com.vyako.smartfactory.main.network.responsedos.CommonResponse;
import com.vyako.smartfactory.main.network.utils.Utils;

import java.util.Map;

/**
 * Created by sid on 07/08/2016.
 */
public class APIHandler implements Response.Listener<Object>, Response.ErrorListener, IDialogCallback {
    private static final int MY_SOCKET_TIMEOUT_MS = 10000;
    private Context context;
    private int requestId;
    private int methodType;
    private boolean showLoading = false;
    private String loadingText;
    private String url;
    private ProgressDialog pDialog = null;
    private APICallback apiCallback = null;
    private String requestBody = null;
    private Map<String, String> headers = null;
    private boolean showToastOnResponse = true;
    private boolean isCanceledOnTouchOutside = false;
    private int tag;

    public APIHandler(APICallback apiCallback, int requestId, int methodType, String url, String requestBody, Map<String, String> headers) {
        this.context = MyApplication.getInstance().getApplicationContext();
        this.apiCallback = apiCallback;
        this.requestId = requestId;
        this.methodType = methodType;
        this.url = url;
        this.showLoading = showLoading;
        this.isCanceledOnTouchOutside = isCanceledOnTouchOutside;
        this.loadingText = loadingText;
        this.requestBody = requestBody;
        this.headers = headers;
    }


    /**
     * Request API to get response for respective request.
     */
    public void requestAPI() {
        //check if internet connect found or not
        if (!Utils.checkInternetConnection(context)) {
            String networkErrorBody = Utils.getNetworkErrorBody(context);
            apiCallback.onAPIFailureResponse(requestId, context.getString(R.string.no_internet_txt));
        } else {
            sendRequest();
        }
    }

    /**
     * Send request if internet connection available.
     */
    private void sendRequest() {
        System.out.println("[API] request url = " + url);
        System.out.println("[API] request body = " + requestBody);
        System.out.println("[API] request Auth_Token: = " + headers.get(NetworkConstants.Headers.X_AUTH_TOKEN));

        GenericRequest genericRequest = new GenericRequest(methodType, url, requestBody, this, this, headers);
        genericRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyApplication.getInstance().addToRequestQueue(genericRequest, requestId);
    }

    @Override
    public void onResponse(Object response) {
        if (response != null) {
            System.out.println("[API] response body volly = " + response.toString());
            if (apiCallback != null) {
                handelResponse(requestId, response.toString());
            }
        } else {
            if (apiCallback != null) {
                apiCallback.onAPIFailureResponse(requestId, "Error in response");
            }
            System.out.println("[API] response fail volly = " + "Error in response");
        }

    }

    /**
     * Handel The API response
     *
     * @param requestId
     * @param response
     */
    private void handelResponse(int requestId, String response) {
        Gson gson = new Gson();
        CommonResponse commonResponse = gson.fromJson(response, CommonResponse.class);
        if (commonResponse != null) {
            String message = commonResponse.getMessage();
            int status_code = commonResponse.getStatus_code();

            switch (status_code) {
                case NetworkConstants.ResponseCode.SESSION_EXPIRE:
                    apiCallback.onAPIFailureResponse(requestId, message);
                    break;

                case NetworkConstants.ResponseCode.INVALID_MACHINE:
                    apiCallback.onAPIFailureResponse(requestId, message);

                    break;
                //Pass the response forward if noo need to handel response here
                default:
                    apiCallback.onAPISuccessResponse(requestId, status_code, message, response);
                    break;
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        System.out.println("[API] response body volly = " + error);
        String errorMessage = context.getResources().getString(R.string.unknownErrorMsg);
        try {
            //sets the default message to the error string variable.
            if (error != null) {
                if (error instanceof TimeoutError) {
                    errorMessage = context.getString(R.string.timeOutTxt);
                } else if (error instanceof ServerError) {
                    errorMessage = context.getString(R.string.serverError);
                } else if (error instanceof NoConnectionError) {
                    errorMessage = context.getString(R.string.unknownErrorMsg);
                }
            }
            System.out.println("[API] response body volly = " + errorMessage);

        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        } finally {
            if (apiCallback != null) {
                apiCallback.onAPIFailureResponse(requestId, errorMessage);
            }
        }
    }

    /**
     * Check for error msg from volly
     *
     * @param error
     * @param defaultMessage
     * @return
     */
    private String getMessage(String error, String defaultMessage) {
        String finalMsg = null;
        if (error == null || error.isEmpty()) {
            finalMsg = defaultMessage;
        } else {
            finalMsg = error;
        }

        return finalMsg;
    }

    public boolean isShowToastOnResponse() {
        return showToastOnResponse;
    }

    public void setShowToastOnResponse(boolean showToastOnResponse) {
        this.showToastOnResponse = showToastOnResponse;
    }

    @Override
    public void onPositiveButtonPress(int requestCode) {

        switch (requestCode) {
//            case RequestCode.INVALID_MACHINE:
            case NetworkConstants.ResponseCode.SESSION_EXPIRE:
                //TODO call logout logic
//                LocalModel.getInstance().logoutApp(true);
                break;
        }

    }

    @Override
    public void onNegativeButtonPress(int requestCode) {

    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }
}
