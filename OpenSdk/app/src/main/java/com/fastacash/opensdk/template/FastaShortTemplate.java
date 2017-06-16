package com.fastacash.opensdk.template;

import android.content.Context;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fastacash.Constant;
import com.fastacash.opensdk.R;
import com.fastacash.opensdk.constants.ServiceAPIConstants;
import com.fastacash.opensdk.controller.APIConfig;
import com.fastacash.opensdk.controller.APIServiceManager;
import com.fastacash.opensdk.utils.ErrorHandler;
import com.fastacash.opensdk.utils.Utility;
import com.google.gson.internal.LinkedTreeMap;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by nikhil on 10/26/2015.
 */
 public class FastaShortTemplate extends Template {
    private Button shortenButton, copyButton;
    private EditText urlEdittext, fastaLinkEdittext;
    private RelativeLayout shortlinkRelativeLayout;
    private TextView customUrlTextView;

    public FastaShortTemplate() {
        init(R.layout.fasta_short);
    }

    @Override
    protected void init(int resId) {
        super.init(resId);
    }

    @Override
    protected void initViews() {
        super.initViews();
        shortenButton = (Button) templateView.findViewById(R.id.shortenButton);
        urlEdittext = (EditText) templateView.findViewById(R.id.urlEdittext);
        fastaLinkEdittext = (EditText) templateView.findViewById(R.id.fastaLinkEdittext);
        shortlinkRelativeLayout = (RelativeLayout) templateView.findViewById(R.id.shortlinkRelativeLayout);
        customUrlTextView = (TextView) templateView.findViewById(R.id.customUrlTextView);
        copyButton = (Button) templateView.findViewById(R.id.copyButton);
    }

    @Override
    protected void setOnClickListeners() {
        super.setOnClickListeners();
        shortenButton.setOnClickListener(this);
        copyButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int i = view.getId();
        if (i == R.id.shortenButton) {
            onShortenButtonClick();

        } else if (i == R.id.copyButton) {
            Utility.copyToClipboard(customUrlTextView.getText().toString(), APIConfig.getInstance().getContext());

        }
    }

    /**
     * Updates the fields of config from respective textfields.
     */
    private void updateAPiConfig() {
        String tempValue = fastaLinkEdittext.getText().toString();

        if (tempValue != null && !tempValue.isEmpty()) {
            APIConfig.getInstance().setCustomUrl(tempValue);
        }

        tempValue = urlEdittext.getText().toString();
        if (tempValue != null && !tempValue.isEmpty()) {
            APIConfig.getInstance().setUrl(tempValue);
        }


    }

    /**
     * this method get called when we click on shorten button
     */
    private void onShortenButtonClick() {
        Context context = APIConfig.getInstance().getContext();
        if (isValid()) {
            urlEdittext.setError(null);
            updateAPiConfig();
            showProgressDialog();

            HashMap<String, Object> requestDetails = new HashMap<>();
            requestDetails.put("url", APIConfig.getInstance().getUrl());
            requestDetails.put("link_purpose", ServiceAPIConstants.LINK_PURPOSE_FAST_A_SHORT);
            reqToCreateLink(requestDetails);
            if (Utility.isInternetConnectionAvaliable(context)) {
                reqToCreateLink(requestDetails);
            } else {
                progressDialog.dismiss();
                ErrorHandler errorHandler = new ErrorHandler(context);
                errorHandler.setType(Constant.ERROR_TYPE_NETWORK);
                errorHandler.setMessage(context.getResources().getString(R.string.network_not_available));
                errorHandler.showError();
            }
        } else {
            shortenButton.setEnabled(false);
            shortenButton.setFocusable(false);
        }

    }

    @Override
    protected void setOnTextChangeListner() {
        super.setOnTextChangeListner();
        urlEdittext.addTextChangedListener(this);
        fastaLinkEdittext.addTextChangedListener(this);
    }

    @Override
    public void afterTextChanged(Editable editable) {
        super.afterTextChanged(editable);
        shortenButton.setEnabled(true);
        shortenButton.setFocusable(true);
    }

    /**
     * It checks that all fields are valid or not
     *
     * @return
     */
    private boolean isValid() {
        Context context = APIConfig.getInstance().getContext();
        ErrorHandler errorHandler = null;
        if (context != null) {
            errorHandler = new ErrorHandler(context);
        }
        String errorMesg = "";
        if ((errorMesg = Utility.isValidUrl(urlEdittext.getText().toString(), context)) != null) {
            if (errorHandler != null) {
                errorHandler.setType(Constant.ERROR_TYPE_CLIENT_VALIDATION_SIDE);
                errorHandler.setMessage(errorMesg);
                errorHandler.showError();
//                Utility.showAlertDialog(context, context.getResources().getString(R.string.invalidUrlTect), context.getResources().getString(R.string.okCapsText));
            }
            return false;
        }
        return true;
    }

    /**
     * Requesting Server to create link.
     */
    private void reqToCreateLink(HashMap<String, Object> requestDetails) {
        APIServiceManager.getInstance().

                createLink(requestDetails, new Callback<HashMap<String, Object>>() {
                            @Override
                            public void success(HashMap<String, Object> responseData, Response response) {
                                progressDialog.dismiss();
                                if (responseData != null && responseData.containsKey("link")) {
                                    System.out.println("Link: " + responseData.get("link"));
                                    LinkedTreeMap<String, Object> linkData = (LinkedTreeMap<String, Object>) responseData.get("link");
                                    if (linkData != null && linkData.containsKey("code")) {
                                        String shortenUrl = getShortenUrl(APIConfig.getInstance().getCustomUrl(), (String) linkData.get("code"));
                                        customUrlTextView.setText(shortenUrl);
                                        shortlinkRelativeLayout.setVisibility(View.VISIBLE);

                                    }
                                }
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                progressDialog.dismiss();
                                showAlertDialog(error.getMessage());
                            }
                        }

                );
    }

    /**
     * Will apply the logic to f8orm the shorten Url.
     *
     * @param customizeUrl
     * @param linkCode
     * @return
     */
    private static String getShortenUrl(String customizeUrl, String linkCode) {
        String shortenUrl = ServiceAPIConstants.FAST_A_LINK + customizeUrl + linkCode;
        return shortenUrl;
    }
}

