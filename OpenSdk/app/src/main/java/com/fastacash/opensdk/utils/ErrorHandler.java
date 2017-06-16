package com.fastacash.opensdk.utils;

import android.content.Context;

import com.fastacash.Constant;
import com.fastacash.opensdk.R;

/**
 * Created by nikhil on 11/4/2015.
 */
public class ErrorHandler {
    /**
     * Type of the error
     */
    private String type;
    /**
     * Message for the error
     */
    private String message;
    private Context context;

    public ErrorHandler() {

    }

    public ErrorHandler(Context context) {
        this.context = context;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Show the respective error according to type
     */
    public void showError() {
        if (type != null) {
            if (type.equals(Constant.ERROR_TYPE_CLIENT_VALIDATION_SIDE)) {
                Utility.showAlertDialog(context, message, context.getResources().getString(R.string.okCapsText));
            } else if (type.equals(Constant.ERROR_TYPE_NETWORK)) {
                Utility.showAlertDialog(context, message, context.getResources().getString(R.string.okCapsText));
            } else if (type.equals(Constant.ERROR_TYPE_SERVER_SIDE)) {
                Utility.showAlertDialog(context, message, context.getResources().getString(R.string.okCapsText));
            }
        }
    }
}
