package com.vyako.smartfactory.main.presenters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.vyako.smartfactory.main.R;
import com.vyako.smartfactory.main.app.constants.AppConstants;
import com.vyako.smartfactory.main.app.controllers.ApplicationController;
import com.vyako.smartfactory.main.network.constants.NetworkConstants;
import com.vyako.smartfactory.main.network.listeners.APIResponseClientCallback;
import com.vyako.smartfactory.main.views.baseviews.IBaseContract;
import com.vyako.smartfactory.main.views.baseviews.IBaseView;
import com.vyako.smartfactory.main.views.menus.controllers.MenuController;

import java.util.HashMap;

/**
 * Created by Nikhil on 13-Jul-17.
 * Abstract class for base presenter.
 * here we check for the permission for the particular events
 */

public abstract class BasePresenter implements IBasePresenter, APIResponseClientCallback {

    protected IBaseView view;
    protected Context context;
    /**
     * Activity presenter to be maintained for the fragments, dialogs, whoever is the child part of the Activity.
     * So, can direct communicate with  Activity via this instance.
     */
    protected BasePresenter activityPresenter;
    /**
     * Keeps the instances of the requested Progress dialogs in the map.
     */
    private HashMap<Integer, ProgressDialog> requestedProgDialogs = new HashMap<>();

    public BasePresenter(@NonNull Context context, @NonNull IBaseView view, @NonNull BasePresenter activityPresenter) {
        this.context = context;
        this.view = view;
        this.activityPresenter = activityPresenter;
    }

    public BasePresenter(@NonNull Context context, @NonNull IBaseView view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void start() {
        launchRootFragment();
    }

    /**
     * Here we set the view context
     */
    @Override
    public void create() {
        ApplicationController.getInstance().setContext(context);
    }

    @Override
    public void dispatch(@IBaseContract.PermissionIds int permissionId, boolean isCheckForPermission) {

        dispatch(permissionId, isCheckForPermission, null);
    }

    @Override
    public void dispatch(@IBaseContract.PermissionIds int permissionId, boolean isCheckForPermission, @Nullable Object data) {

        Log.d(AppConstants.LOG_CAT, "dispatched from the Base Presenter" + permissionId);
        //shows the progress dialog.
        showProgressDialog(null, context.getString(R.string.checking_permission), false, permissionId);

        boolean isGranted = false;

        if (isCheckForPermission) {
            if (MenuController.getInstance().isPermissionGranted(permissionId)) {
                isGranted = true;
            }
        } else {
            isGranted = true;
        }

        //calls to navigate response.
        onPermissionCheckResponse(isGranted, permissionId, data);
    }


    /**
     * Navigates the response based on the permission is Granted value.
     *
     * @param isGranted
     * @param permissionId
     */
    private void onPermissionCheckResponse(boolean isGranted, int permissionId, Object data) {
        //dismisses the progress dialog
        dismissProgressDialog(permissionId);

        if (isGranted) {
            onPermissionAccess(permissionId, data);
        }
        onPermissionDenied();
    }

    /**
     * IF permission is denied show dialog
     */
    @Override
    public void onPermissionDenied() {
        Log.d(AppConstants.LOG_CAT, "Permission Denied");
    }

    @Override
    public void launchRootFragment() {

    }


    @Override
    public void showProgressDialog(String title, String message, boolean cancelOutsideTouch, int identifier) {
        ProgressDialog progressDialog = ProgressDialog.show(context, title, message);
        progressDialog.setCanceledOnTouchOutside(cancelOutsideTouch);
        requestedProgDialogs.put(identifier, progressDialog);
    }

    @Override
    public void dismissProgressDialog(int identifier) {
        if (requestedProgDialogs != null && !requestedProgDialogs.isEmpty()) {
            ProgressDialog progressDialog = requestedProgDialogs.get(identifier);
            if (progressDialog != null) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                    requestedProgDialogs.remove(identifier);
                }
            }
        }
    }

    @Override
    public void onSuccessResponse(@NetworkConstants.RequestCode int requestId, int statusCode, String message, @Nullable Object object) {
        Log.d(AppConstants.LOG_CAT, "BasePresenter onSuccessResponse : " + message);
    }

    @Override
    public void onFailureResponse(@NetworkConstants.RequestCode int requestId, @NonNull String errorString) {
        Log.d(AppConstants.LOG_CAT, "BasePresenter onFailureResponse : " + errorString);
        //TODO handle the response failure here
    }

    @Override
    public void showAlertDialogWithPositiveButton(@NonNull String dialogTitle, @NonNull String dialogMessage, @NonNull final int identifier, boolean isOutsideCancelable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(dialogTitle);
        builder.setMessage(dialogMessage);

        builder.setPositiveButton(context.getString(R.string.ok_txt),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                        onPositiveButtonClick(identifier);
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(isOutsideCancelable);
        dialog.show();
    }

    /**
     * Get called when we click on positive button
     * Added logic here or override it on base and add logic there
     *
     * @param request_id
     */
    protected void onPositiveButtonClick(int request_id) {
        switch (request_id) {
//            case REQ_LOGOUT:
//                break;

        }
    }
}
