package com.vyako.smartfactory.mo.presenters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

import com.vyako.smartfactory.main.R;
import com.vyako.smartfactory.main.app.constants.AppConstants;
import com.vyako.smartfactory.main.network.constants.NetworkConstants;
import com.vyako.smartfactory.main.network.listeners.APIResponseClientCallback;
import com.vyako.smartfactory.main.presenters.BasePresenter;
import com.vyako.smartfactory.main.views.baseviews.IBaseContract;
import com.vyako.smartfactory.mo.contracts.MoListFragmentContract;
import com.vyako.smartfactory.mo.dos.MOListDO;
import com.vyako.smartfactory.mo.models.managers.MoManager;
import com.vyako.smartfactory.mo.views.fragments.MoDetailFragment;

import org.json.JSONException;

/**
 * Created by ranjeetd on 6/9/2017.
 */

public class MoListFragmentPresenterImpl extends BasePresenter implements MoListFragmentContract.IPresenter, APIResponseClientCallback {
    protected FragmentTransaction fragmentTransaction;

    public MoListFragmentPresenterImpl(@NonNull Context context, @NonNull MoListFragmentContract.IView view, @NonNull MoActivityPresenterImpl activityPresenter) {
        super(context, view, activityPresenter);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void onPermissionAccess(@IBaseContract.PermissionIds int permissionId, @Nullable Object data) {
        switch (permissionId) {
            case MoListFragmentContract.IPermissionIds.MO_LIST_PERMISSION:
                Log.d(AppConstants.LOG_CAT, "Permission Access : " + permissionId);
                prepareToGetMoList();
                break;
            case MoListFragmentContract.IPermissionIds.LAUNCH_MO_DETAIL_FRAGMENT_SCREEN:
                Log.d(AppConstants.LOG_CAT, "Permission Access : " + permissionId);
                MoDetailFragment moDetailFragment = new MoDetailFragment();
                launchFragment(moDetailFragment);
                break;
            case MoListFragmentContract.IPermissionIds.CREATE_NEW_MO:
                createNewMo();
                break;
            case MoListFragmentContract.IPermissionIds.EDIT_MO:
                editMo();
                break;
            case MoListFragmentContract.IPermissionIds.DELETE_MO:
                deleteMo();
                break;
            case MoListFragmentContract.IPermissionIds.CHANGE_ZONE_OF_MO:
                changeZoneOfMo();
                break;
            default:
                throw new IllegalStateException("Invalid Request code id");

        }
    }

    @Override
    public void prepareToGetMoList() {
        try {
            showProgressDialog(context.getString(R.string.fetching_mo_list), context.getString(R.string.pleaseWaitMsg), false, NetworkConstants.RequestCode.GET_MO_LIST);
            MoManager.getInstance().sendRequestToGetMoList(this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createNewMo() {
        Toast.makeText(context, "Comming Soon!!!!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteMo() {

    }

    @Override
    public void editMo() {

    }

    @Override
    public void changeZoneOfMo() {

    }

    @Override
    public void launchFragment(Fragment fragment) {

        /// fragmentTransaction =(MoActivity.this).getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.activity_container_layout, fragment);
//        fragmentTransaction.commit();
    }

    @Override
    public void launchMODetails(Object args) {
        System.out.println("Launch MO details");
        //needs to launch the Mo details screen, so asking activity presenter to do so.
        ((MoActivityPresenterImpl) activityPresenter).launchMoDetailFragment(args);
    }


    @Override
    public void onSuccessResponse(@NetworkConstants.RequestCode int requestId, int statusCode, String message, @Nullable Object object) {
        super.onSuccessResponse(requestId, statusCode, message, object);
        System.out.println("Client CallBack Success: requestId = [" + requestId + "], statusCode = [" + statusCode + "], message = [" + message + "], object = [" + object + "]");
        if (object instanceof MOListDO) {
            MOListDO molistDO = (MOListDO) object;
            ((MoListFragmentContract.IView) view).refreshMOListAdapter(molistDO);
        }

        //dismissing the progress dialog
        dismissProgressDialog(NetworkConstants.RequestCode.GET_MO_LIST);
    }

    @Override
    public void onFailureResponse(@NetworkConstants.RequestCode int requestId,
                                  @NonNull String errorString) {
        super.onFailureResponse(requestId, errorString);
        System.out.println("Client CallBack Failure: requestId = [" + requestId + "], errorString = [" + errorString + "]");
    }

    @Override
    public void stop() {

    }
}
