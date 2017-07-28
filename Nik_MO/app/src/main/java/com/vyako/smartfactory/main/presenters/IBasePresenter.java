package com.vyako.smartfactory.main.presenters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vyako.smartfactory.main.views.baseviews.IBaseContract;

/**
 * Created by kaushik on 23-May-17.
 * IBasePresenter interface declare same common  methods here
 */
public interface IBasePresenter {

    /**
     * Method which call in create to set the context.
     * Always call its super method to set the context.
     * Compulsory to Override in each screen.
     */
    void create();

    /**
     * Method which call in start to start any of the fundtionality.
     * Always call its super method to set the context.
     * Compulsory to Override in each screen.
     */
    void start();

    /**
     * Method in which the components are clear at a time of onStop or onDestroy
     */
    void stop();

    /**
     * Method which check the permission for the particular events
     * and give the respective callbacks
     * onPermissionAccess
     * onPermissionDenied
     *
     * @param permissionId
     * @param isCheckForPermission
     */
    void dispatch(@IBaseContract.PermissionIds int permissionId, boolean isCheckForPermission);

    /**
     * Method which check the permission for the particular events
     * and give the respective callbacks
     * onPermissionAccess
     * onPermissionDenied
     *
     * @param permissionId
     * @param isCheckForPermission
     * @param data
     */
    void dispatch(@IBaseContract.PermissionIds int permissionId, boolean isCheckForPermission,
                  @Nullable Object data);

    /**
     * Method in which when particular events has a permission to access
     *
     * @param permissionId
     */
    void onPermissionAccess(@IBaseContract.PermissionIds int permissionId, @Nullable Object data);

    /**
     * Method in which when particular events has a no permission to access
     * Permission denied
     */
    void onPermissionDenied();

    /**
     * Launch root fragment for the activity, override wherever requires only.
     */
    void launchRootFragment();

    /**
     * Shows the progress dialog, in case of hitting server request.
     *
     * @param title
     * @param message
     */
    void showProgressDialog(@NonNull String title, @NonNull String message, boolean
            cancelOutsideTouch, @NonNull int identifier);

    /**
     * Dissmiss progress dialog.
     */
    void dismissProgressDialog(@NonNull int identifier);


    void showAlertDialogWithPositiveButton(@NonNull String dialogTitle, @NonNull String
            dialogMessage,@NonNull int identifier, boolean isOutsideCancelable);
}
