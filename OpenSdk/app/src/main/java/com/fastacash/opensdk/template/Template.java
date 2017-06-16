package com.fastacash.opensdk.template;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;

import com.fastacash.opensdk.controller.APIConfig;

import java.util.HashMap;
import java.util.zip.Inflater;

/**
 * Created by nikhil on 10/26/2015.
 */
public class Template implements View.OnClickListener, View.OnLongClickListener, TextWatcher {
    /**
     * Resource Id of the xml file.
     */
    private int resId;
    protected View templateView;
    protected HashMap<String, Object> data;
    protected ProgressDialog progressDialog;
    protected AlertDialog.Builder alertDialog;

    /**
     * Initializes the view by inflating from provided xml file.
     *
     * @param resId
     */
    protected void init(int resId) {
        LayoutInflater inflater = (LayoutInflater) APIConfig.getInstance().getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        templateView = inflater.inflate(resId, null);
        initViews();
        initAlertDialog();
        setOnClickListeners();
        setOnLongClickListeners();
        setOnTextChangeListner();
    }


    /**
     * Initializes the views here.
     */
    protected void initViews() {

    }

    /**
     * Lets set the onCLickListener to required views.
     */
    protected void setOnClickListeners() {

    }

    /**
     * Lets set the onLongCLickListener to required views.
     */
    protected void setOnLongClickListeners() {

    }

    /**
     * Lets set the Text change listner to required views.
     */
    protected void setOnTextChangeListner() {

    }

    protected void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(APIConfig.getInstance().getContext());
        }
        progressDialog.setMessage("Please wait...");
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    private void initAlertDialog() {
        alertDialog = new AlertDialog.Builder(APIConfig.getInstance().getContext())
                .setTitle("Error!")
                .setMessage("")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                });
    }

    protected void showAlertDialog(String message) {
        alertDialog.setMessage(message).show();
    }

    @Override
    public void onClick(View view) {

    }


    @Override
    public boolean onLongClick(View view) {
        return false;
    }

    public View getTemplateView() {
        return templateView;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
