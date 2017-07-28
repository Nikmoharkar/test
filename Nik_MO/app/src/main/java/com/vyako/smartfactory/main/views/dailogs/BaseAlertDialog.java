package com.vyako.smartfactory.main.views.dailogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.vyako.smartfactory.main.app.application.MyApplication;
import com.vyako.smartfactory.main.app.models.LocalModel;
import com.vyako.smartfactory.main.presenters.IBasePresenter;
import com.vyako.smartfactory.main.views.baseviews.IBaseView;
import com.vyako.smartfactory.main.views.listeners.IOnDialogEventsCallbacks;


/**
 * Created by kaushik on 02-Jan-17.
 */

public abstract class BaseAlertDialog implements IBaseView, View.OnClickListener {

    /**
     * Keeps the generic presenter object so can be used in every dialogs.
     */
    protected IBasePresenter presenter;

    private Context context;
    private AlertDialog alertDialog;
    private View parentView;
    protected Activity activity;
    protected IOnDialogEventsCallbacks onDialogEventsCallbacks;
    protected int identifier;

    BaseAlertDialog(Context context) {
        this.context = context;
        this.activity = (Activity) context;
        onCreate(0);
    }

    BaseAlertDialog(Context context, int identifier) {
        this.context = context;
        this.activity = (Activity) context;
        onCreate(0);
        this.identifier = identifier;
    }

    public void setOnDialogEventsCallbacks(IOnDialogEventsCallbacks onDialogEventsCallbacks) {
        this.onDialogEventsCallbacks = onDialogEventsCallbacks;
    }

    public void onCreate(int layoutResId) {
        LayoutInflater factory = LayoutInflater.from(context);
        parentView = factory.inflate(layoutResId, null);
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        alertDialog.setView(parentView);
        initViews(parentView);
        registerEvents(parentView);
        initializeInstances();
    }

    /**
     * initialize the instances required for the View & presenter.
     * Specially initialize the presenter here.
     */
    @Override
    public void initializeInstances() {
        setPresenter();
    }

    /**
     * sets the dialog cancelable parameters
     *
     * @param isCancalabel
     */
    public void setCancelable(boolean isCancalabel) {
        try {
            alertDialog.setCanceledOnTouchOutside(isCancalabel);
            alertDialog.setCancelable(isCancalabel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Show dialog
     */
    public void show() {
        Log.d("Logs Context", "Sensor Dialog: Current Activity Hash Code: " + context + " App: " + MyApplication.getInstance());

        if (alertDialog != null) {
            //updates UI before showing dialog.
            setInfoInUI(parentView);
            alertDialog.show();
            LocalModel.getInstance().setDialogShowing(true);
        }
    }

    public void updateContext(Context context) {
        this.context = context;
        this.activity = (Activity) context;
//        create(0);
    }

    /**
     * @return Whether the dialog is currently showing.
     */
    public boolean isShowing() {
        if (alertDialog != null) {
            return alertDialog.isShowing();
        }
        return false;
    }

    /**
     * stop dialog
     */
    public void dismiss() {
        if (alertDialog != null) {
            alertDialog.dismiss();
            LocalModel.getInstance().setDialogShowing(false);
        }
    }

//    public abstract ViewI setViews(LayoutInflater layoutInflater);

    public abstract void initViews(View view);

    public abstract void registerEvents(View view);

    protected abstract void setInfoInUI(View parentView);

    public abstract void onClickEvent(View actionView);

    public abstract void setData(Object data);

    /**
     * Here we set the generic presenter
     * Override setPresenter in all sub classes and set the presenter
     * Compulsory to Override in each screen
     */
    protected abstract void setPresenter();

    @Override
    public void onClick(View view) {
        onClickEvent(view);
    }

}
